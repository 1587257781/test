package com.tenhisi.web.cgs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ResponseData;
import com.tenhisi.framework.redis.SysConfigRedis;
import com.tenhisi.framework.sys.entity.SysDictDataEntity;
import com.tenhisi.web.cgs.entity.CgsSysDictDataEntity;
import com.tenhisi.web.cgs.service.CgsSysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典列表控制类,还是操作原来系统Dict,只是在展示时做改进。
 * @author shaneLuo
 * @Date: 2021/8/3
 * @Version: 1.0
 */
@Controller
@RequestMapping("/cgs/sysDictHouseType")
public class CgsSysDictHouseTypeController extends BaseController {
    private String urlPrefix = "modules/sysDict/houseType";

    @Autowired
    private CgsSysDictDataService sysDictDataService;

    @Autowired
    private SysConfigRedis sysConfigRedis;

    @RequiresPermissions("sys:dict:view")
    @GetMapping()
    public String dictData() {
        return urlPrefix + "/data";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiresPermissions("sys:dict:list")
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params) {
        params.put("dictType","cgs_house_type");
        PageUtil page = sysDictDataService.findPage(params);
        return success(page);
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("dictType","cgs_house_type");
        return urlPrefix + "/add";
    }


    /**
     * 新增保存字典类型
     */
    @BussinessLog(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("sys:dict:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addSave(@Validated SysDictDataEntity dict){
        if(sysDictDataService.save(dict)){
            List<SysDictDataEntity> list = sysDictDataService.list(new QueryWrapper<SysDictDataEntity>().
                    eq("dict_type",dict.getDictType()).
                    eq("status","0").orderBy(true, true, "dict_sort"));
            sysConfigRedis.saveOrUpdateDict(dict.getDictType(),list);
            return success();
        }else{
            return error("新增失败!");
        }
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap){
        mmap.put("dictHtml", sysDictDataService.getById(dictCode));
        return urlPrefix + "/edit";
    }


    /**
     * 修改保存字典类型
     */
    @BussinessLog(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editSave(@Validated CgsSysDictDataEntity dict) {
        if(sysDictDataService.updateDictData(dict)){
            return success();
        }else{
            return error("修改失败!");
        }
    }


    @BussinessLog(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("sys:dict:del")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
        return sysDictDataService.deleteBatchByIds(ids)?success():error("删除失败!");
    }
}
