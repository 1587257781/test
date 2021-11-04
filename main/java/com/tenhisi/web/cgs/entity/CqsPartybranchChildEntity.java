/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotBlank;

import com.tenhisi.common.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_partybranch_child
 * @author: Shane
 * @date 2021-08-16 15:20:19
 */
@Getter
@Setter
@TableName("cqs_partybranch_child")
public class CqsPartybranchChildEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 父表ID */
    private Long pid;

    /** 名称 */
    @NotBlank(message = "参数值不能为空")
    private String name;

    /** 电话 */
    private String phone;

    /** 职务 */
    private String position;

}
