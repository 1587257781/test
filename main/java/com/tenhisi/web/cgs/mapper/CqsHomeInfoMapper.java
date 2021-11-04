/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsHomeInfoEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_home_info Mapper接口
 * @author: Shane
 * @date 2021-08-13 11:00:57
 */
public interface CqsHomeInfoMapper extends BaseMapper<CqsHomeInfoEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsHomeInfoEntity 实体类
     */
    Page<CqsHomeInfoEntity> findPage(IPage<CqsHomeInfoEntity> page,
                                      @Param("cqsHomeInfo") CqsHomeInfoEntity cqsHomeInfoEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsHomeInfoEntity findCqsHomeInfoById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsHomeInfoEntity 查询条件对象
     * @return
     */
    List<CqsHomeInfoEntity> findList(CqsHomeInfoEntity cqsHomeInfoEntity);

}
