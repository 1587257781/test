/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.common.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_homeinfo_disabled
 * @author: Shane
 * @date 2021-09-06 02:39:54
 */
@Getter
@Setter
@TableName("cqs_homeinfo_disabled")
public class CqsHomeinfoDisabledEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 父键 */
    @NotNull(message = "参数值不能为空")
    private Long pid;

    /** 残疾类别 */
    private String hdType;

    /** 等级 */
    private String hdGrade;

    /** 残疾人证号 */
    private String hdIdno;

    /** 爱好特长 */
    private String hobbies;

    /** 监护人 */
    private String guardian;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

}
