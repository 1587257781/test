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
 * cqs_branch_act
 * @author: Shane
 * @date 2021-09-01 01:18:55
 */
@Getter
@Setter
@TableName("cqs_branch_act")
public class CqsBranchActEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 支部编号 */
    @NotNull(message = "参数值不能为空")
    private Long pid;

    /** 活动标题 */
    @NotBlank(message = "参数值不能为空")
    private String actTitle;

    /** 活动类型 */
    @NotBlank(message = "参数值不能为空")
    private String actType;

    /** 活动内容 */
    private String actContent;

    /** 发布日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date actDate;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

    /*支部名称*/
    @TableField(exist = false)
    private String branchName;
}
