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
import com.tenhisi.web.cgs.entity.CqsPartyMemberEntity;
import com.tenhisi.web.cgs.mapper.CqsPartyMemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 党员信息Service接口
 * @author: Shane
 * @date 2021-09-01 00:47:42
 */
@Service
public class CqsPartyMemberService extends ServiceImpl<CqsPartyMemberMapper,CqsPartyMemberEntity> {

	@Resource
	private CqsPartyMemberMapper cqsPartyMemberMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsPartyMemberEntity> queryWrapper = new QueryWrapper<CqsPartyMemberEntity>();
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
		String joinday = (String) params.get("joinday");
        queryWrapper.eq(ToolUtil.isNotEmpty(joinday), "joinday", joinday);
		String house = (String) params.get("house");
        queryWrapper.eq(ToolUtil.isNotEmpty(house), "house", house);
		Page<CqsPartyMemberEntity> page = cqsPartyMemberMapper.selectPage(new Query<CqsPartyMemberEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsPartyMemberEntity cqsPartyMemberEntity) {
		Page<CqsPartyMemberEntity> page = cqsPartyMemberMapper.findPage(new Query<CqsPartyMemberEntity>(params).getPage(),
					cqsPartyMemberEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsPartyMemberEntity> findList(CqsPartyMemberEntity cqsPartyMemberEntity){
		return cqsPartyMemberMapper.findList(cqsPartyMemberEntity);
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
	public boolean delCqsPartyMemberById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsPartyMember(CqsPartyMemberEntity cqsPartyMember){
		if(this.save(cqsPartyMember)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsPartyMemberById(CqsPartyMemberEntity cqsPartyMember) {
		if(this.updateById(cqsPartyMember)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsPartyMemberEntity findCqsPartyMemberById(Long id){
		return cqsPartyMemberMapper.findCqsPartyMemberById(id);
	}

	/**
	 * 根据身份证号获取对象
	 */
	public CqsPartyMemberEntity findCqsPartyMemberByIdcode(String idcode){
		return cqsPartyMemberMapper.findCqsPartyMemberByIdcode(idcode);
	}


}
