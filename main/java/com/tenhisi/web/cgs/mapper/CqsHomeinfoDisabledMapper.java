/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tenhisi.web.cgs.entity.CqsHomeinfoDisabledEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * cqs_homeinfo_disabled Mapper接口
 * @author: Shane
 * @date 2021-09-06 02:39:54
 */
public interface CqsHomeinfoDisabledMapper extends BaseMapper<CqsHomeinfoDisabledEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsHomeinfoDisabledEntity 实体类
     */
    Page<CqsHomeinfoDisabledEntity> findPage(IPage<CqsHomeinfoDisabledEntity> page,
                                      @Param("cqsHomeinfoDisabled") CqsHomeinfoDisabledEntity cqsHomeinfoDisabledEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsHomeinfoDisabledEntity findCqsHomeinfoDisabledById(@Param("id") Long id);

    /**
     * 通过身份证号查询
     * @param idcode 身份证号
     * @return
     */
    CqsHomeinfoDisabledEntity findCqsHomeinfoDisabledByIdcode(@Param("idcode") String idcode);

    /**
     * 查询列表
     * @param cqsHomeinfoDisabledEntity 查询条件对象
     * @return
     */
    List<CqsHomeinfoDisabledEntity> findList(CqsHomeinfoDisabledEntity cqsHomeinfoDisabledEntity);

}
