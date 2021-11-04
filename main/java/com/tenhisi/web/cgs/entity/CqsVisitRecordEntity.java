/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.common.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_visit_record
 * @author: Shane
 * @date 2021-08-19 17:16:30
 */
@Getter
@Setter
@TableName("cqs_visit_record")
public class CqsVisitRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 父键 */
    @NotNull(message = "参数值不能为空")
    private Long pid;

    /** 走访日期 */
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date reDate;

    /** 走访内容 */
    private String reContent;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

    /*住房信息*/
    @TableField(exist = false)
    private String homeInfo;
}
