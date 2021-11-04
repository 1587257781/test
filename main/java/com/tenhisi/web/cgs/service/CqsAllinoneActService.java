/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.framework.utils.FileUploadUtils;
import com.tenhisi.web.cgs.entity.CqsAllinoneActEntity;
import com.tenhisi.web.cgs.mapper.CqsAllinoneActMapper;
import com.tenhisi.common.core.page.Query;
import com.tenhisi.common.core.utils.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.utils.ToolUtil;
import com.tenhisi.framework.utils.Constant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

/**
 * 一本通Service接口
 * @author: Shane
 * @date 2021-11-02 02:49:36
 */
@Service
public class CqsAllinoneActService extends ServiceImpl<CqsAllinoneActMapper,CqsAllinoneActEntity> {

	@Resource
	private CqsAllinoneActMapper cqsAllinoneActMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsAllinoneActEntity> queryWrapper = new QueryWrapper<CqsAllinoneActEntity>();
		String actTitle = (String) params.get("actTitle");
        queryWrapper.like(ToolUtil.isNotEmpty(actTitle), "act_title", actTitle);
		String actType = (String) params.get("actType");
        queryWrapper.eq(ToolUtil.isNotEmpty(actType), "act_type", actType);
		String actUpfiles = (String) params.get("actUpfiles");
        queryWrapper.eq(ToolUtil.isNotEmpty(actUpfiles), "act_upfiles", actUpfiles);
		String actDate = (String) params.get("actDate");
        queryWrapper.eq(ToolUtil.isNotEmpty(actDate), "act_date", actDate);
		Page<CqsAllinoneActEntity> page = cqsAllinoneActMapper.selectPage(new Query<CqsAllinoneActEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsAllinoneActEntity cqsAllinoneActEntity) {
		Page<CqsAllinoneActEntity> page = cqsAllinoneActMapper.findPage(new Query<CqsAllinoneActEntity>(params).getPage(),
					cqsAllinoneActEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsAllinoneActEntity> findList(CqsAllinoneActEntity cqsAllinoneActEntity){
		return cqsAllinoneActMapper.findList(cqsAllinoneActEntity);
	}

	/**
     * 批量删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchByIds(Long[] ids) {
		for (Long id : ids) {
			FileUploadUtils.me().removeFileUpload(id,"cqs_allinone_act_file");
		}
		return this.removeByIds(Arrays.asList(ids));
	}

	/**
     * 单个删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean delCqsAllinoneActById(Long id) {
		FileUploadUtils.me().removeFileUpload(id,"cqs_allinone_act_file");
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsAllinoneAct(CqsAllinoneActEntity cqsAllinoneAct){
		if(this.save(cqsAllinoneAct)){
			//更新关联附件信息
			Long pkId =  cqsAllinoneAct.getId();
			FileUploadUtils.saveFileUpload(pkId,"cqs_allinone_act_file");
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsAllinoneActById(CqsAllinoneActEntity cqsAllinoneAct) {
		if(this.updateById(cqsAllinoneAct)){
			//更新关联附件信息
			Long pkId =  cqsAllinoneAct.getId();
			FileUploadUtils.saveFileUpload(pkId,"cqs_allinone_act_file");
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsAllinoneActEntity findCqsAllinoneActById(Long id){
		return cqsAllinoneActMapper.findCqsAllinoneActById(id);
	}


}
