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
import com.tenhisi.web.cgs.entity.CqsHomeinfoDisabledEntity;
import com.tenhisi.web.cgs.mapper.CqsHomeinfoDisabledMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 残疾人信息Service接口
 * @author: Shane
 * @date 2021-09-06 02:39:54
 */
@Service
public class CqsHomeinfoDisabledService extends ServiceImpl<CqsHomeinfoDisabledMapper,CqsHomeinfoDisabledEntity> {

	@Resource
	private CqsHomeinfoDisabledMapper cqsHomeinfoDisabledMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsHomeinfoDisabledEntity> queryWrapper = new QueryWrapper<CqsHomeinfoDisabledEntity>();
		String hdType = (String) params.get("hdType");
        queryWrapper.eq(ToolUtil.isNotEmpty(hdType), "hd_type", hdType);
		String hdGrade = (String) params.get("hdGrade");
        queryWrapper.eq(ToolUtil.isNotEmpty(hdGrade), "hd_grade", hdGrade);
		String hdIdno = (String) params.get("hdIdno");
        queryWrapper.eq(ToolUtil.isNotEmpty(hdIdno), "hd_idno", hdIdno);
		Page<CqsHomeinfoDisabledEntity> page = cqsHomeinfoDisabledMapper.selectPage(new Query<CqsHomeinfoDisabledEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsHomeinfoDisabledEntity cqsHomeinfoDisabledEntity) {
		Page<CqsHomeinfoDisabledEntity> page = cqsHomeinfoDisabledMapper.findPage(new Query<CqsHomeinfoDisabledEntity>(params).getPage(),
					cqsHomeinfoDisabledEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsHomeinfoDisabledEntity> findList(CqsHomeinfoDisabledEntity cqsHomeinfoDisabledEntity){
		return cqsHomeinfoDisabledMapper.findList(cqsHomeinfoDisabledEntity);
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
	public boolean delCqsHomeinfoDisabledById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsHomeinfoDisabled(CqsHomeinfoDisabledEntity cqsHomeinfoDisabled){
		if(this.save(cqsHomeinfoDisabled)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsHomeinfoDisabledById(CqsHomeinfoDisabledEntity cqsHomeinfoDisabled) {
		if(this.updateById(cqsHomeinfoDisabled)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsHomeinfoDisabledEntity findCqsHomeinfoDisabledById(Long id){
		return cqsHomeinfoDisabledMapper.findCqsHomeinfoDisabledById(id);
	}

	/**
	 * 根据身份证号获取对象
	 */
	public CqsHomeinfoDisabledEntity findCqsHomeinfoDisabledByIdcode(String idcode){
		return cqsHomeinfoDisabledMapper.findCqsHomeinfoDisabledByIdcode(idcode);
	}


}
