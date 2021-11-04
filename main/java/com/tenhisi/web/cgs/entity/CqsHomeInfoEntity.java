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
import com.tenhisi.framework.sys.entity.SysCompEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_home_info
 * @author: Shane
 * @date 2021-08-13 11:00:57
 */
@Getter
@Setter
@TableName("cqs_home_info")
public class CqsHomeInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 社区 */
    @NotNull(message = "参数值不能为空")
    private Long compId;

    @TableField(exist=false)
    private SysCompEntity company;

    /** 楼栋号 */
    private String houseCode;

    /** 单元号 */
    private String unitCode;

    /** 房间号 */
    private String homeCode;

    /** 整体面积 */
    @NotNull(message = "参数值不能为空")
    private String homeArea;

    /** 房屋性质 */
    private String roomType;

    /** 租赁开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date rentStartday;

    /** 租赁到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date rentEndday;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

}
