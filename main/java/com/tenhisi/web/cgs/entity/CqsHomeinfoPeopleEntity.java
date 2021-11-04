/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.common.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_homeinfo_people
 * @author: Shane
 * @date 2021-09-05 22:10:19
 */
@Getter
@Setter
@TableName("cqs_homeinfo_people")
public class CqsHomeinfoPeopleEntity extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 父键 */
    @NotNull(message = "参数值不能为空")
    private Long pid;

    /** 姓名 */
    @NotBlank(message = "参数值不能为空")
    private String name;

    /** 身份证号 */
    @NotBlank(message = "参数值不能为空")
    private String idcode;

    /** 联系电话 */
    @NotBlank(message = "参数值不能为空")
    private String phone;

    /** 性别 */
    private String sex;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date birthday;

    /** 与户主关系 */
    private String homeRelation;

    /** 民族 */
    private String nation;

    /** 户籍地 */
    private String household;

    /** 居住地 */
    private String domicile;

    /** 文化程度 */
    private String eduLevel;

    /** 婚姻状况 */
    private String maritalState;

    /** 工作单位 */
    private String workUnit;

    /** 职业 */
    private String job;

    /** 身份标识 */
    private String flags;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date inputTime;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

}
