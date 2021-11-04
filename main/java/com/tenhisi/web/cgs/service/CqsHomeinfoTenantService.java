/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsHomeinfoTenantEntity;
import com.tenhisi.web.cgs.mapper.CqsHomeinfoTenantMapper;
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
 * 租客信息Service接口
 * @author: Shane
 * @date 2021-08-13 11:01:01
 */
@Service
public class CqsHomeinfoTenantService extends ServiceImpl<CqsHomeinfoTenantMapper,CqsHomeinfoTenantEntity> {

	@Resource
	private CqsHomeinfoTenantMapper cqsHomeinfoTenantMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsHomeinfoTenantEntity> queryWrapper = new QueryWrapper<CqsHomeinfoTenantEntity>();
		String name = (String) params.get("name");
        queryWrapper.like(ToolUtil.isNotEmpty(name), "name", name);
		String idcode = (String) params.get("idcode");
        queryWrapper.eq(ToolUtil.isNotEmpty(idcode), "idcode", idcode);
		String phone = (String) params.get("phone");
        queryWrapper.eq(ToolUtil.isNotEmpty(phone), "phone", phone);
		String sex = (String) params.get("sex");
        queryWrapper.eq(ToolUtil.isNotEmpty(sex), "sex", sex);
		String household = (String) params.get("household");
        queryWrapper.like(ToolUtil.isNotEmpty(household), "household", household);
		String nation = (String) params.get("nation");
        queryWrapper.like(ToolUtil.isNotEmpty(nation), "nation", nation);
		Page<CqsHomeinfoTenantEntity> page = cqsHomeinfoTenantMapper.selectPage(new Query<CqsHomeinfoTenantEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsHomeinfoTenantEntity cqsHomeinfoTenantEntity) {
		Page<CqsHomeinfoTenantEntity> page = cqsHomeinfoTenantMapper.findPage(new Query<CqsHomeinfoTenantEntity>(params).getPage(),
					cqsHomeinfoTenantEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsHomeinfoTenantEntity> findList(CqsHomeinfoTenantEntity cqsHomeinfoTenantEntity){
		return cqsHomeinfoTenantMapper.findList(cqsHomeinfoTenantEntity);
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
	public boolean delCqsHomeinfoTenantById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsHomeinfoTenant(CqsHomeinfoTenantEntity cqsHomeinfoTenant){
		if(this.save(cqsHomeinfoTenant)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsHomeinfoTenantById(CqsHomeinfoTenantEntity cqsHomeinfoTenant) {
		if(this.updateById(cqsHomeinfoTenant)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsHomeinfoTenantEntity findCqsHomeinfoTenantById(Long id){
		return cqsHomeinfoTenantMapper.findCqsHomeinfoTenantById(id);
	}


}
