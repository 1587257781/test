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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.common.core.base.entity.BaseEntity;
import com.tenhisi.framework.sys.entity.SysFilesEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_allinone_act
 * @author: Shane
 * @date 2021-11-02 02:49:36
 */
@Getter
@Setter
@TableName("cqs_allinone_act")
public class CqsAllinoneActEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 支部编号 */
    @NotNull(message = "参数值不能为空")
    private Long pid;

    /** 工单标题 */
    @NotBlank(message = "参数值不能为空")
    private String actTitle;

    /** 工单类型 */
    @NotBlank(message = "参数值不能为空")
    private String actType;

    /** 工单附件 */
    private String actUpfiles;

    /** 工单内容 */
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

    /*支部名称*/
    @TableField(exist=false)
    private List<SysFilesEntity> sysFiles;
}
