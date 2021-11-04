/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsHomeinfoTenantEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_homeinfo_tenant Mapper接口
 * @author: Shane
 * @date 2021-08-13 11:01:01
 */
public interface CqsHomeinfoTenantMapper extends BaseMapper<CqsHomeinfoTenantEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsHomeinfoTenantEntity 实体类
     */
    Page<CqsHomeinfoTenantEntity> findPage(IPage<CqsHomeinfoTenantEntity> page,
                                      @Param("cqsHomeinfoTenant") CqsHomeinfoTenantEntity cqsHomeinfoTenantEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsHomeinfoTenantEntity findCqsHomeinfoTenantById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsHomeinfoTenantEntity 查询条件对象
     * @return
     */
    List<CqsHomeinfoTenantEntity> findList(CqsHomeinfoTenantEntity cqsHomeinfoTenantEntity);

}
