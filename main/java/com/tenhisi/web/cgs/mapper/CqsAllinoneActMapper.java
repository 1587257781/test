/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsAllinoneActEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_allinone_act Mapper接口
 * @author: Shane
 * @date 2021-11-02 02:49:36
 */
public interface CqsAllinoneActMapper extends BaseMapper<CqsAllinoneActEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsAllinoneActEntity 实体类
     */
    Page<CqsAllinoneActEntity> findPage(IPage<CqsAllinoneActEntity> page,
                                      @Param("cqsAllinoneAct") CqsAllinoneActEntity cqsAllinoneActEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsAllinoneActEntity findCqsAllinoneActById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsAllinoneActEntity 查询条件对象
     * @return
     */
    List<CqsAllinoneActEntity> findList(CqsAllinoneActEntity cqsAllinoneActEntity);

}
