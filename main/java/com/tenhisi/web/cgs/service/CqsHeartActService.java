/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsHeartActEntity;
import com.tenhisi.web.cgs.mapper.CqsHeartActMapper;
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
 * 连心活动Service接口
 * @author: Shane
 * @date 2021-09-25 17:01:39
 */
@Service
public class CqsHeartActService extends ServiceImpl<CqsHeartActMapper,CqsHeartActEntity> {

	@Resource
	private CqsHeartActMapper cqsHeartActMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsHeartActEntity> queryWrapper = new QueryWrapper<CqsHeartActEntity>();
		String actTitle = (String) params.get("actTitle");
        queryWrapper.eq(ToolUtil.isNotEmpty(actTitle), "act_title", actTitle);
		String actType = (String) params.get("actType");
        queryWrapper.eq(ToolUtil.isNotEmpty(actType), "act_type", actType);
		String actDate = (String) params.get("actDate");
        queryWrapper.eq(ToolUtil.isNotEmpty(actDate), "act_date", actDate);
		Page<CqsHeartActEntity> page = cqsHeartActMapper.selectPage(new Query<CqsHeartActEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsHeartActEntity cqsHeartActEntity) {
		Page<CqsHeartActEntity> page = cqsHeartActMapper.findPage(new Query<CqsHeartActEntity>(params).getPage(),
					cqsHeartActEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsHeartActEntity> findList(CqsHeartActEntity cqsHeartActEntity){
		return cqsHeartActMapper.findList(cqsHeartActEntity);
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
	public boolean delCqsHeartActById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsHeartAct(CqsHeartActEntity cqsHeartAct){
		if(this.save(cqsHeartAct)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsHeartActById(CqsHeartActEntity cqsHeartAct) {
		if(this.updateById(cqsHeartAct)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsHeartActEntity findCqsHeartActById(Long id){
		return cqsHeartActMapper.findCqsHeartActById(id);
	}


}
