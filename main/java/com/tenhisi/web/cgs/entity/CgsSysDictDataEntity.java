package com.tenhisi.web.cgs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.tenhisi.framework.sys.entity.SysCompEntity;
import com.tenhisi.framework.sys.entity.SysDictDataEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CgsSysDictDataEntity extends SysDictDataEntity {
    private static final long serialVersionUID = 1L;

    @TableField(exist=false)
    private SysCompEntity company;
}
