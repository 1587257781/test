/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsHomeInfoEntity;
import com.tenhisi.web.cgs.mapper.CqsHomeInfoMapper;
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
 * 住房信息Service接口
 * @author: Shane
 * @date 2021-08-13 11:00:57
 */
@Service
public class CqsHomeInfoService extends ServiceImpl<CqsHomeInfoMapper,CqsHomeInfoEntity> {

	@Resource
	private CqsHomeInfoMapper cqsHomeInfoMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsHomeInfoEntity> queryWrapper = new QueryWrapper<CqsHomeInfoEntity>();
		String compId = (String) params.get("compId");
        queryWrapper.eq(ToolUtil.isNotEmpty(compId), "comp_id", compId);
		String houseCode = (String) params.get("houseCode");
        queryWrapper.eq(ToolUtil.isNotEmpty(houseCode), "house_code", houseCode);
		String unitCode = (String) params.get("unitCode");
        queryWrapper.eq(ToolUtil.isNotEmpty(unitCode), "unit_code", unitCode);
		String homeCode = (String) params.get("homeCode");
        queryWrapper.eq(ToolUtil.isNotEmpty(homeCode), "home_code", homeCode);
		String roomType = (String) params.get("roomType");
        queryWrapper.eq(ToolUtil.isNotEmpty(roomType), "room_type", roomType);
		Page<CqsHomeInfoEntity> page = cqsHomeInfoMapper.selectPage(new Query<CqsHomeInfoEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsHomeInfoEntity cqsHomeInfoEntity) {
		Page<CqsHomeInfoEntity> page = cqsHomeInfoMapper.findPage(new Query<CqsHomeInfoEntity>(params).getPage(),
					cqsHomeInfoEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsHomeInfoEntity> findList(CqsHomeInfoEntity cqsHomeInfoEntity){
		return cqsHomeInfoMapper.findList(cqsHomeInfoEntity);
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
	public boolean delCqsHomeInfoById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsHomeInfo(CqsHomeInfoEntity cqsHomeInfo){
		if(this.save(cqsHomeInfo)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsHomeInfoById(CqsHomeInfoEntity cqsHomeInfo) {
		if(this.updateById(cqsHomeInfo)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsHomeInfoEntity findCqsHomeInfoById(Long id){
		return cqsHomeInfoMapper.findCqsHomeInfoById(id);
	}


}
