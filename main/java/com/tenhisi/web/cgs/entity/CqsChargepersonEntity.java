/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.common.core.base.entity.BaseEntity;
import com.tenhisi.framework.sys.entity.SysCompEntity;
import com.tenhisi.framework.sys.entity.SysDeptEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_chargeperson
 * @author: Shane
 * @date 2021-08-02 22:29:20
 */
@Getter
@Setter
@TableName("cqs_chargeperson")
public class CqsChargepersonEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 姓名 */
    @NotBlank(message = "参数值不能为空")
    private String name;

    /** 电话 */
    private String mobile;

    /** 职务 */
    private String duty;

    /** 是否删除 */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

    /** 单位 */
    private Long compId;

    @TableField(exist=false)
    private SysCompEntity company;

    /** 部门 */
    private Long deptId;

    @TableField(exist=false)
    private SysDeptEntity organization;

}
