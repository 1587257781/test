/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsHeartActEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_heart_act Mapper接口
 * @author: Shane
 * @date 2021-09-25 17:01:39
 */
public interface CqsHeartActMapper extends BaseMapper<CqsHeartActEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsHeartActEntity 实体类
     */
    Page<CqsHeartActEntity> findPage(IPage<CqsHeartActEntity> page,
                                      @Param("cqsHeartAct") CqsHeartActEntity cqsHeartActEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsHeartActEntity findCqsHeartActById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsHeartActEntity 查询条件对象
     * @return
     */
    List<CqsHeartActEntity> findList(CqsHeartActEntity cqsHeartActEntity);

}
