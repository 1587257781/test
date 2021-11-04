package com.tenhisi.web.cgs.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tenhisi.common.core.page.Query;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ToolUtil;
import com.tenhisi.framework.sys.service.SysDictDataService;
import com.tenhisi.web.cgs.entity.CgsSysDictDataEntity;
import com.tenhisi.web.cgs.mapper.CgsSysDictDataMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

@Primary
@Service
public class CgsSysDictDataService extends SysDictDataService {

    @Resource
    private CgsSysDictDataMapper cgsSysDictDataMapper;
    /**
     * 页面展示查询翻页
     */
    public PageUtil findPage(Map<String, Object> params) {
        String dictLabel = (String) params.get("dictLabel");
        String dictType = (String) params.get("dictType");
        String status = (String) params.get("status");
        Page<CgsSysDictDataEntity> page = cgsSysDictDataMapper.searchPage(new Query<CgsSysDictDataEntity>(params).getPage(),
                new QueryWrapper<CgsSysDictDataEntity>().like(ToolUtil.isNotEmpty(dictLabel),
                        "dict_label", dictLabel).like(ToolUtil.isNotEmpty(dictType),
                        "dict_type", dictType).like(ToolUtil.isNotEmpty(status),"status",status));
        return new PageUtil(page);
    }

    public CgsSysDictDataEntity  getById(Long id) {
        return cgsSysDictDataMapper.searchById(id);
    }
}
