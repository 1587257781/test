/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsPartybranchChildEntity;
import com.tenhisi.web.cgs.mapper.CqsPartybranchChildMapper;
import cn.hutool.core.convert.Convert;
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
 * 支部人员Service接口
 * @author: Shane
 * @date 2021-08-16 15:20:19
 */
@Service
public class CqsPartybranchChildService extends ServiceImpl<CqsPartybranchChildMapper,CqsPartybranchChildEntity> {

	@Resource
	private CqsPartybranchChildMapper cqsPartybranchChildMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsPartybranchChildEntity> queryWrapper = new QueryWrapper<CqsPartybranchChildEntity>();
		Long pid = Convert.toLong(params.get("pid"));
		queryWrapper.eq(ToolUtil.isNotEmpty(pid), "pid", pid);
		Page<CqsPartybranchChildEntity> page = cqsPartybranchChildMapper.selectPage(new Query<CqsPartybranchChildEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsPartybranchChildEntity cqsPartybranchChildEntity) {
		Page<CqsPartybranchChildEntity> page = cqsPartybranchChildMapper.findPage(new Query<CqsPartybranchChildEntity>(params).getPage(),
					cqsPartybranchChildEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsPartybranchChildEntity> findList(CqsPartybranchChildEntity cqsPartybranchChildEntity){
		return cqsPartybranchChildMapper.findList(cqsPartybranchChildEntity);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean delCqsPartybranchChildByPkId(Long pid){
		return this.remove(new QueryWrapper<CqsPartybranchChildEntity>().eq("pid", pid));
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
	public boolean delCqsPartybranchChildById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsPartybranchChild(CqsPartybranchChildEntity cqsPartybranchChild){
		if(this.save(cqsPartybranchChild)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsPartybranchChildById(CqsPartybranchChildEntity cqsPartybranchChild) {
		if(this.updateById(cqsPartybranchChild)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsPartybranchChildEntity findCqsPartybranchChildById(Long id){
		return cqsPartybranchChildMapper.findCqsPartybranchChildById(id);
	}


}
