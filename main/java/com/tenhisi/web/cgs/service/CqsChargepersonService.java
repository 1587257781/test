/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsChargepersonEntity;
import com.tenhisi.web.cgs.mapper.CqsChargepersonMapper;
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
 * 单位负责人Service接口
 * @author: Shane
 * @date 2021-08-02 22:29:20
 */
@Service
public class CqsChargepersonService extends ServiceImpl<CqsChargepersonMapper,CqsChargepersonEntity> {

	@Resource
	private CqsChargepersonMapper cqsChargepersonMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsChargepersonEntity> queryWrapper = new QueryWrapper<CqsChargepersonEntity>();
		String name = (String) params.get("name");
        queryWrapper.like(ToolUtil.isNotEmpty(name), "name", name);
		String mobile = (String) params.get("mobile");
        queryWrapper.like(ToolUtil.isNotEmpty(mobile), "mobile", mobile);
		String duty = (String) params.get("duty");
        queryWrapper.eq(ToolUtil.isNotEmpty(duty), "duty", duty);
		String compId = (String) params.get("compId");
        queryWrapper.eq(ToolUtil.isNotEmpty(compId), "comp_id", compId);
		String deptId = (String) params.get("deptId");
        queryWrapper.eq(ToolUtil.isNotEmpty(deptId), "dept_id", deptId);
		Page<CqsChargepersonEntity> page = cqsChargepersonMapper.selectPage(new Query<CqsChargepersonEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsChargepersonEntity cqsChargepersonEntity) {
		Page<CqsChargepersonEntity> page = cqsChargepersonMapper.findPage(new Query<CqsChargepersonEntity>(params).getPage(),
					cqsChargepersonEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsChargepersonEntity> findList(CqsChargepersonEntity cqsChargepersonEntity){
		return cqsChargepersonMapper.findList(cqsChargepersonEntity);
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
	public boolean delCqsChargepersonById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsChargeperson(CqsChargepersonEntity cqsChargeperson){
		if(this.save(cqsChargeperson)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsChargepersonById(CqsChargepersonEntity cqsChargeperson) {
		if(this.updateById(cqsChargeperson)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsChargepersonEntity findCqsChargepersonById(Long id){
		return cqsChargepersonMapper.findCqsChargepersonById(id);
	}


}
