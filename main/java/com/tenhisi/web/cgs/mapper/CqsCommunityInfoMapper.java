/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.tenhisi.web.cgs.entity.CqsCommunityInfoEntity;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * cqs_community_info Mapper接口
 * @author: Shane
 * @date 2021-08-02 22:47:39
 */
public interface CqsCommunityInfoMapper extends BaseMapper<CqsCommunityInfoEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsCommunityInfoEntity 实体类
     */
    Page<CqsCommunityInfoEntity> findPage(IPage<CqsCommunityInfoEntity> page,
                                      @Param("cqsCommunityInfo") CqsCommunityInfoEntity cqsCommunityInfoEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsCommunityInfoEntity findCqsCommunityInfoById(@Param("id") Long id);

    /**
     * 查询列表
     * @param cqsCommunityInfoEntity 查询条件对象
     * @return
     */
    List<CqsCommunityInfoEntity> findList(CqsCommunityInfoEntity cqsCommunityInfoEntity);

}
