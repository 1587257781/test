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
import java.util.List;

/**
 * cqs_partybranch_info
 * @author: Shane
 * @date 2021-08-16 15:20:18
 */
@Getter
@Setter
@TableName("cqs_partybranch_info")
public class CqsPartybranchInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 社区 */
    private Long compId;

    @TableField(exist=false)
    private SysCompEntity company;

    /** 支部名称 */
    @NotBlank(message = "参数值不能为空")
    private String branchName;

    /** 支部书记 */
    private String principal;

    /** 支部电话 */
    private String branchPhone;

    /** 党组织地址 */
    private String branchAddress;

    /** 经纬度（逗号分隔） */
    private String branchLnglat;

    /** 支部人数 */
    private String branchPeoplenum;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

    @TableField(exist=false)
    private List<CqsPartybranchChildEntity> cqsPartybranchChild;

}
