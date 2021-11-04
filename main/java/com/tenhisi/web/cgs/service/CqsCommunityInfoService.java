/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsCommunityInfoEntity;
import com.tenhisi.web.cgs.mapper.CqsCommunityInfoMapper;
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
 * 社区信息Service接口
 * @author: Shane
 * @date 2021-08-02 22:47:39
 */
@Service
public class CqsCommunityInfoService extends ServiceImpl<CqsCommunityInfoMapper,CqsCommunityInfoEntity> {

	@Resource
	private CqsCommunityInfoMapper cqsCommunityInfoMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsCommunityInfoEntity> queryWrapper = new QueryWrapper<CqsCommunityInfoEntity>();
		String compId = (String) params.get("compId");
        queryWrapper.eq(ToolUtil.isNotEmpty(compId), "comp_id", compId);
		Page<CqsCommunityInfoEntity> page = cqsCommunityInfoMapper.selectPage(new Query<CqsCommunityInfoEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsCommunityInfoEntity cqsCommunityInfoEntity) {
		Page<CqsCommunityInfoEntity> page = cqsCommunityInfoMapper.findPage(new Query<CqsCommunityInfoEntity>(params).getPage(),
					cqsCommunityInfoEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsCommunityInfoEntity> findList(CqsCommunityInfoEntity cqsCommunityInfoEntity){
		return cqsCommunityInfoMapper.findList(cqsCommunityInfoEntity);
	}

	/**
     * 批量删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchByIds(Long[] ids) {
		return this.removeByIds(Arrays.asList(ids));
	}

	/**
     * 单个删除
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean delCqsCommunityInfoById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsCommunityInfo(CqsCommunityInfoEntity cqsCommunityInfo){
		if(this.save(cqsCommunityInfo)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsCommunityInfoById(CqsCommunityInfoEntity cqsCommunityInfo) {
		if(this.updateById(cqsCommunityInfo)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsCommunityInfoEntity findCqsCommunityInfoById(Long id){
		return cqsCommunityInfoMapper.findCqsCommunityInfoById(id);
	}


}
