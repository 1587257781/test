/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsVisitRecordEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_visit_record Mapper接口
 * @author: Shane
 * @date 2021-08-19 17:16:30
 */
public interface CqsVisitRecordMapper extends BaseMapper<CqsVisitRecordEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsVisitRecordEntity 实体类
     */
    Page<CqsVisitRecordEntity> findPage(IPage<CqsVisitRecordEntity> page,
                                      @Param("cqsVisitRecord") CqsVisitRecordEntity cqsVisitRecordEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsVisitRecordEntity findCqsVisitRecordById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsVisitRecordEntity 查询条件对象
     * @return
     */
    List<CqsVisitRecordEntity> findList(CqsVisitRecordEntity cqsVisitRecordEntity);

}
