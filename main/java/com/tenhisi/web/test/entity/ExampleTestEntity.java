/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.tenhisi.framework.sys.entity.SysCompEntity;
import com.tenhisi.framework.sys.entity.SysDeptEntity;
import com.tenhisi.common.core.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * example_test
 * @author: Shane
 * @date 2021-12-21 10:42:57
 */
@Getter
@Setter
@TableName("example_test")
public class ExampleTestEntity extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /** 员工编号 */
    @NotBlank(message = "参数值不能为空")
    private String code;

    /** 姓名 */
    @NotBlank(message = "参数值不能为空")
    private String name;

    /** 邮箱 */
    private String email;

    /** 头像 */
    private String avatar;

    /** 电话 */
    private String phone;

    /** 性别 */
    private String sex;

    /** 年龄 */
    private Long age;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date joinDate;

    /** 离职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date leaveDate;

    /** 归属单位 */
    private Long compId;

    @TableField(exist=false)
    private SysCompEntity company;

    /** 归属部门 */
    private Long deptId;

    @TableField(exist=false)
    private SysDeptEntity organization;

    /** 地址 */
    private String addr;

    /** 地址详情 */
    private String addrinfo;

    /** 删除标记（0：正常；1：删除） */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag;

    @TableField(exist=false)
    private List<ExampleTestChildEntity> exampleTestChild;

}
