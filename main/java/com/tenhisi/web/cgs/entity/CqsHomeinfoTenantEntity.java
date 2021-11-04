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
 * cqs_homeinfo_tenant
 * @author: Shane
 * @date 2021-08-13 11:01:01
 */
@Getter
@Setter
@TableName("cqs_homeinfo_tenant")
public class CqsHomeinfoTenantEntity extends BaseEntity {

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
    private String idcode;

    /** 联系电话 */
    private String phone;

    /** 性别 */
    private String sex;

    /** 户口登记地 */
    private String household;

    /** 民族 */
    @NotBlank(message = "参数值不能为空")
    private String nation;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date checkinTime;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date inputTime;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

}
