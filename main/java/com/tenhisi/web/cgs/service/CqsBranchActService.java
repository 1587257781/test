/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.tenhisi.web.cgs.entity.CqsBranchActEntity;
import com.tenhisi.web.cgs.mapper.CqsBranchActMapper;
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
 * 支部活动Service接口
 * @author: Shane
 * @date 2021-09-01 01:18:55
 */
@Service
public class CqsBranchActService extends ServiceImpl<CqsBranchActMapper,CqsBranchActEntity> {

	@Resource
	private CqsBranchActMapper cqsBranchActMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsBranchActEntity> queryWrapper = new QueryWrapper<CqsBranchActEntity>();
		String actTitle = (String) params.get("actTitle");
        queryWrapper.eq(ToolUtil.isNotEmpty(actTitle), "act_title", actTitle);
		String actType = (String) params.get("actType");
        queryWrapper.eq(ToolUtil.isNotEmpty(actType), "act_type", actType);
		String actDate = (String) params.get("actDate");
        queryWrapper.eq(ToolUtil.isNotEmpty(actDate), "act_date", actDate);
		Page<CqsBranchActEntity> page = cqsBranchActMapper.selectPage(new Query<CqsBranchActEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsBranchActEntity cqsBranchActEntity) {
		Page<CqsBranchActEntity> page = cqsBranchActMapper.findPage(new Query<CqsBranchActEntity>(params).getPage()
						.addOrder(OrderItem.desc("id")),
					cqsBranchActEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsBranchActEntity> findList(CqsBranchActEntity cqsBranchActEntity){
		return cqsBranchActMapper.findList(cqsBranchActEntity);
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
	public boolean delCqsBranchActById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsBranchAct(CqsBranchActEntity cqsBranchAct){
		if(this.save(cqsBranchAct)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsBranchActById(CqsBranchActEntity cqsBranchAct) {
		if(this.updateById(cqsBranchAct)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsBranchActEntity findCqsBranchActById(Long id){
		return cqsBranchActMapper.findCqsBranchActById(id);
	}


}
