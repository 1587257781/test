/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.service;

import com.tenhisi.web.cgs.entity.CqsVisitRecordEntity;
import com.tenhisi.web.cgs.mapper.CqsVisitRecordMapper;
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
 * 走访记录Service接口
 * @author: Shane
 * @date 2021-08-19 17:16:30
 */
@Service
public class CqsVisitRecordService extends ServiceImpl<CqsVisitRecordMapper,CqsVisitRecordEntity> {

	@Resource
	private CqsVisitRecordMapper cqsVisitRecordMapper;

	/**
	 * mybaitis-plus 单表页面分页查询
     */
	public PageUtil findPage(Map<String, Object> params) {
		QueryWrapper<CqsVisitRecordEntity> queryWrapper = new QueryWrapper<CqsVisitRecordEntity>();
		String reDate = (String) params.get("reDate");
        queryWrapper.eq(ToolUtil.isNotEmpty(reDate), "re_date", reDate);
		String reContent = (String) params.get("reContent");
        queryWrapper.like(ToolUtil.isNotEmpty(reContent), "re_content", reContent);
		Page<CqsVisitRecordEntity> page = cqsVisitRecordMapper.selectPage(new Query<CqsVisitRecordEntity>(params).getPage(), queryWrapper);
		return new PageUtil(page);
    }

	/**
	 * 自定义分页查询，含关联实体对像
	 */
	public PageUtil findPage(Map<String, Object> params,CqsVisitRecordEntity cqsVisitRecordEntity) {
		Page<CqsVisitRecordEntity> page = cqsVisitRecordMapper.findPage(new Query<CqsVisitRecordEntity>(params).getPage(),
					cqsVisitRecordEntity,
				(String) params.get(Constant.SQL_FILTER));
		return new PageUtil(page);
	}

	/**
	* 查列表
	*/
	public List<CqsVisitRecordEntity> findList(CqsVisitRecordEntity cqsVisitRecordEntity){
		return cqsVisitRecordMapper.findList(cqsVisitRecordEntity);
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
	public boolean delCqsVisitRecordById(Long id) {
		return this.removeById(id);
	}

	/**
     * 保存
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean addCqsVisitRecord(CqsVisitRecordEntity cqsVisitRecord){
		if(this.save(cqsVisitRecord)){
			return true;
		}
        return false;
    }

	/**
     * 修改根居ID
     */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateCqsVisitRecordById(CqsVisitRecordEntity cqsVisitRecord) {
		if(this.updateById(cqsVisitRecord)){
			return true;
		}
		return false;
	}

	/**
     * 根居ID获取对象
     */
	public CqsVisitRecordEntity findCqsVisitRecordById(Long id){
		return cqsVisitRecordMapper.findCqsVisitRecordById(id);
	}


}
