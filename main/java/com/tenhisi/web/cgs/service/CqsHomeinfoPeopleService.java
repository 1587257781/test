/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenhisi.common.core.page.Query;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ToolUtil;
import com.tenhisi.framework.utils.Constant;
import com.tenhisi.web.cgs.entity.CqsHomeinfoPeopleEntity;
import com.tenhisi.web.cgs.mapper.CqsHomeinfoPeopleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 家庭成员Service接口
 * @author: Shane
 * @date 2021-09-05 22:10:19
 */
@Service
public class CqsHomeinfoPeopleService extends ServiceImpl<CqsHomeinfoPeopleMapper,CqsHomeinfoPeopleEntity> {

	@Resource
	private CqsHomeinfoPeopleMapper cqsHomeinfoPeopleMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsHomeinfoPeopleEntity> queryWrapper = new QueryWrapper<CqsHomeinfoPeopleEntity>();
		String name = (String) params.get("name");
        queryWrapper.like(ToolUtil.isNotEmpty(name), "name", name);
		String idcode = (String) params.get("idcode");
        queryWrapper.eq(ToolUtil.isNotEmpty(idcode), "idcode", idcode);
		String phone = (String) params.get("phone");
        queryWrapper.eq(ToolUtil.isNotEmpty(phone), "phone", phone);
		String sex = (String) params.get("sex");
        queryWrapper.eq(ToolUtil.isNotEmpty(sex), "sex", sex);
		String birthday = (String) params.get("birthday");
        queryWrapper.eq(ToolUtil.isNotEmpty(birthday), "birthday", birthday);
		String homeRelation = (String) params.get("homeRelation");
        queryWrapper.eq(ToolUtil.isNotEmpty(homeRelation), "home_relation", homeRelation);
		String nation = (String) params.get("nation");
        queryWrapper.like(ToolUtil.isNotEmpty(nation), "nation", nation);
		String flags = (String) params.get("flags");
        queryWrapper.eq(ToolUtil.isNotEmpty(flags), "flags", flags);
		Page<CqsHomeinfoPeopleEntity> page = cqsHomeinfoPeopleMapper.selectPage(new Query<CqsHomeinfoPeopleEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsHomeinfoPeopleEntity cqsHomeinfoPeopleEntity) {
		Page<CqsHomeinfoPeopleEntity> page = cqsHomeinfoPeopleMapper.findPage(new Query<CqsHomeinfoPeopleEntity>(params).getPage(),
					cqsHomeinfoPeopleEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsHomeinfoPeopleEntity> findList(CqsHomeinfoPeopleEntity cqsHomeinfoPeopleEntity){
		return cqsHomeinfoPeopleMapper.findList(cqsHomeinfoPeopleEntity);
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
	public boolean delCqsHomeinfoPeopleById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsHomeinfoPeople(CqsHomeinfoPeopleEntity cqsHomeinfoPeople){
		if(this.save(cqsHomeinfoPeople)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsHomeinfoPeopleById(CqsHomeinfoPeopleEntity cqsHomeinfoPeople) {
		if(this.updateById(cqsHomeinfoPeople)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsHomeinfoPeopleEntity findCqsHomeinfoPeopleById(Long id){
		return cqsHomeinfoPeopleMapper.findCqsHomeinfoPeopleById(id);
	}

	/**
	 * 根据身份证号获取对象
	 */
	public CqsHomeinfoPeopleEntity findCqsHomeinfoPeopleByIdcode(String idcode){
		return cqsHomeinfoPeopleMapper.findCqsHomeinfoPeopleByIdcode(idcode);
	}


}
