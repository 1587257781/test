/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsPartybranchChildEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_partybranch_child Mapper接口
 * @author: Shane
 * @date 2021-08-16 15:20:19
 */
public interface CqsPartybranchChildMapper extends BaseMapper<CqsPartybranchChildEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsPartybranchChildEntity 实体类
     */
    Page<CqsPartybranchChildEntity> findPage(IPage<CqsPartybranchChildEntity> page,
                                      @Param("cqsPartybranchChild") CqsPartybranchChildEntity cqsPartybranchChildEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsPartybranchChildEntity findCqsPartybranchChildById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsPartybranchChildEntity 查询条件对象
     * @return
     */
    List<CqsPartybranchChildEntity> findList(CqsPartybranchChildEntity cqsPartybranchChildEntity);

}
