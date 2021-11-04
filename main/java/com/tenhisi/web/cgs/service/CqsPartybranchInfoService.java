/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.framework.utils.FileUploadUtils;
import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import com.tenhisi.web.cgs.mapper.CqsPartybranchInfoMapper;
import com.tenhisi.web.cgs.entity.CqsPartybranchChildEntity;
import com.tenhisi.common.core.page.Query;
import com.tenhisi.common.core.utils.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.utils.ToolUtil;
import com.tenhisi.framework.utils.Constant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

/**
 * 支部信息Service接口
 * @author: Shane
 * @date 2021-08-16 15:20:18
 */
@Service
public class CqsPartybranchInfoService extends ServiceImpl<CqsPartybranchInfoMapper,CqsPartybranchInfoEntity> {

	@Resource
	private CqsPartybranchInfoMapper cqsPartybranchInfoMapper;

	@Autowired
	private CqsPartybranchChildService cqsPartybranchChildService;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsPartybranchInfoEntity> queryWrapper = new QueryWrapper<CqsPartybranchInfoEntity>();
		String compId = (String) params.get("compId");
        queryWrapper.eq(ToolUtil.isNotEmpty(compId), "comp_id", compId);
		String branchName = (String) params.get("branchName");
        queryWrapper.like(ToolUtil.isNotEmpty(branchName), "branch_name", branchName);
		String principal = (String) params.get("principal");
        queryWrapper.like(ToolUtil.isNotEmpty(principal), "principal", principal);
		String branchPhone = (String) params.get("branchPhone");
        queryWrapper.eq(ToolUtil.isNotEmpty(branchPhone), "branch_phone", branchPhone);
		String branchAddress = (String) params.get("branchAddress");
        queryWrapper.like(ToolUtil.isNotEmpty(branchAddress), "branch_address", branchAddress);
		Page<CqsPartybranchInfoEntity> page = cqsPartybranchInfoMapper.selectPage(new Query<CqsPartybranchInfoEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsPartybranchInfoEntity cqsPartybranchInfoEntity) {
		Page<CqsPartybranchInfoEntity> page = cqsPartybranchInfoMapper.findPage(new Query<CqsPartybranchInfoEntity>(params).getPage(),
					cqsPartybranchInfoEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsPartybranchInfoEntity> findList(CqsPartybranchInfoEntity cqsPartybranchInfoEntity){
		return cqsPartybranchInfoMapper.findList(cqsPartybranchInfoEntity);
	}

	/**
     * 批量删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchByIds(Long[] ids) {
		for (Long id : ids) {
			FileUploadUtils.me().removeFileUpload(id,"cqs_partybranch_info_img");
		}
		return this.removeByIds(Arrays.asList(ids));
	}

	/**
     * 单个删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean delCqsPartybranchInfoById(Long id) {
		FileUploadUtils.me().removeFileUpload(id,"cqs_partybranch_info_img");
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsPartybranchInfo(CqsPartybranchInfoEntity cqsPartybranchInfo){
		if(this.save(cqsPartybranchInfo)){
			//更新关联附件信息
			Long pkId =  cqsPartybranchInfo.getId();
			FileUploadUtils.saveFileUpload(pkId,"cqs_partybranch_info_img");
			//保存子表信息
			if(ToolUtil.isNotEmpty(cqsPartybranchInfo.getCqsPartybranchChild())){
				for(CqsPartybranchChildEntity cqsPartybranchChild :cqsPartybranchInfo.getCqsPartybranchChild()){
					cqsPartybranchChild.setPid(pkId);
					cqsPartybranchChildService.addCqsPartybranchChild(cqsPartybranchChild);
				}
			}
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsPartybranchInfoById(CqsPartybranchInfoEntity cqsPartybranchInfo) {
		if(this.updateById(cqsPartybranchInfo)){
			//更新关联附件信息
			Long pkId =  cqsPartybranchInfo.getId();
			FileUploadUtils.saveFileUpload(pkId,"cqs_partybranch_info_img");
			//直接删除，不要判断，否则有BUG
			cqsPartybranchChildService.delCqsPartybranchChildByPkId(pkId);
			//if(cqsPartybranchChildService.delCqsPartybranchChildByPkId(pkId)){
				//保存子表信息
				if(ToolUtil.isNotEmpty(cqsPartybranchInfo.getCqsPartybranchChild())){
					for(CqsPartybranchChildEntity cqsPartybranchChild :cqsPartybranchInfo.getCqsPartybranchChild()){
						cqsPartybranchChild.setPid(pkId);
						cqsPartybranchChildService.addCqsPartybranchChild(cqsPartybranchChild);
					}
				}
			//}
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsPartybranchInfoEntity findCqsPartybranchInfoById(Long id){
		return cqsPartybranchInfoMapper.findCqsPartybranchInfoById(id);
	}


}
