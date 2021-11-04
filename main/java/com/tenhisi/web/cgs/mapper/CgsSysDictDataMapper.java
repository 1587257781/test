package com.tenhisi.web.cgs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tenhisi.framework.sys.mapper.SysDictDataMapper;
import com.tenhisi.web.cgs.entity.CgsSysDictDataEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

//继承要加 @Primary
@Primary
public interface CgsSysDictDataMapper extends SysDictDataMapper {

    //QueryWrapper 要加注解@Param(Constants.WRAPPER)
    Page<CgsSysDictDataEntity> searchPage(Page<CgsSysDictDataEntity> page, @Param(Constants.WRAPPER)QueryWrapper<CgsSysDictDataEntity> ew);

    CgsSysDictDataEntity searchById(Long id);
}
