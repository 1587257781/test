/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_partybranch_info Mapper接口
 * @author: Shane
 * @date 2021-08-16 15:20:18
 */
public interface CqsPartybranchInfoMapper extends BaseMapper<CqsPartybranchInfoEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsPartybranchInfoEntity 实体类
     */
    Page<CqsPartybranchInfoEntity> findPage(IPage<CqsPartybranchInfoEntity> page,
                                      @Param("cqsPartybranchInfo") CqsPartybranchInfoEntity cqsPartybranchInfoEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsPartybranchInfoEntity findCqsPartybranchInfoById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsPartybranchInfoEntity 查询条件对象
     * @return
     */
    List<CqsPartybranchInfoEntity> findList(CqsPartybranchInfoEntity cqsPartybranchInfoEntity);

}
