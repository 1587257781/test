/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsChargepersonEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_chargeperson Mapper接口
 * @author: Shane
 * @date 2021-08-02 22:29:20
 */
public interface CqsChargepersonMapper extends BaseMapper<CqsChargepersonEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsChargepersonEntity 实体类
     */
    Page<CqsChargepersonEntity> findPage(IPage<CqsChargepersonEntity> page,
                                      @Param("cqsChargeperson") CqsChargepersonEntity cqsChargepersonEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsChargepersonEntity findCqsChargepersonById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsChargepersonEntity 查询条件对象
     * @return
     */
    List<CqsChargepersonEntity> findList(CqsChargepersonEntity cqsChargepersonEntity);

}
