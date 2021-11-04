/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsBranchActEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_branch_act Mapper接口
 * @author: Shane
 * @date 2021-09-01 01:18:55
 */
public interface CqsBranchActMapper extends BaseMapper<CqsBranchActEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsBranchActEntity 实体类
     */
    Page<CqsBranchActEntity> findPage(IPage<CqsBranchActEntity> page,
                                      @Param("cqsBranchAct") CqsBranchActEntity cqsBranchActEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsBranchActEntity findCqsBranchActById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsBranchActEntity 查询条件对象
     * @return
     */
    List<CqsBranchActEntity> findList(CqsBranchActEntity cqsBranchActEntity);

}
