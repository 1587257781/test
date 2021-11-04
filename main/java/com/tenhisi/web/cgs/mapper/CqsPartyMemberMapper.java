/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tenhisi.web.cgs.entity.CqsPartyMemberEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * cqs_party_member Mapper接口
 * @author: Shane
 * @date 2021-09-01 00:47:42
 */
public interface CqsPartyMemberMapper extends BaseMapper<CqsPartyMemberEntity> {

    /**
     * 自定义分页查询
     * @param  page
     * @param  cqsPartyMemberEntity 实体类
     */
    Page<CqsPartyMemberEntity> findPage(IPage<CqsPartyMemberEntity> page,
                                      @Param("cqsPartyMember") CqsPartyMemberEntity cqsPartyMemberEntity,
                                      @Param("sql_filter") String sql_filter);

    /**
     * 通过ID查询
     * @param id 查询ID
     * @return
     */
    CqsPartyMemberEntity findCqsPartyMemberById(@Param("id") Long id);

    /**
     * 通过身份证号查询
     * @param idcode 身份证号
     * @return
     */
    CqsPartyMemberEntity findCqsPartyMemberByIdcode(@Param("idcode") String idcode);

    /**
     * 查询列表
     * @param cqsPartyMemberEntity 查询条件对象
     * @return
     */
    List<CqsPartyMemberEntity> findList(CqsPartyMemberEntity cqsPartyMemberEntity);

}
