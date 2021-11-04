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
import lombok.Getter;
import lombok.Setter;

/**
 * cqs_community_info
 * @author: Shane
 * @date 2021-08-02 22:47:39
 */
@Getter
@Setter
@TableName("cqs_community_info")
public class CqsCommunityInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 社区名称 */
    private Long compId;

    @TableField(exist=false)
    private SysCompEntity company;

    /** 整体面积 */
    @NotBlank(message = "参数值不能为空")
    private String postName;

    /** 总房栋 */
    private Integer houseNum;

    /** 户数 */
    private Integer homeNum;

    /** 人口总数 */
    private Integer peopleNum;

    /** 党员人数 */
    private Integer pmemberNum;

    /** 团员人数 */
    private Integer lmemberNum;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

}
