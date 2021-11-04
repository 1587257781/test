/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.controller;

import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.*;
import com.tenhisi.framework.annotation.RepeatSubmit;
import com.tenhisi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.ModelMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.tenhisi.web.cgs.entity.CqsChargepersonEntity;
import com.tenhisi.web.cgs.service.CqsChargepersonService;

/**
 * 单位负责人页面控制器
 * @author Shane
 * @date 2021-08-02 22:29:20
 */
@Controller
@RequestMapping("/cgs/chargeperson")
public class CqsChargepersonController extends BaseController{

    private String prefix = "modules/cgs/chargeperson";

    @Autowired
    private CqsChargepersonService cqsChargepersonService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:chargeperson:view")
    @GetMapping()
    public String chargeperson(){
        return prefix + "/chargeperson";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsChargepersonEntity
     * @return
     */
    @RequiresPermissions("cgs:chargeperson:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsChargepersonEntity cqsChargepersonEntity) {
        PageUtil page = cqsChargepersonService.findPage(params,cqsChargepersonEntity);
		return success(page);
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:chargeperson:add")
    @BussinessLog(title = "单位负责人", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsChargeperson(@Validated CqsChargepersonEntity cqsChargeperson){
        //校验参数
        ValidatorUtil.validateEntity(cqsChargeperson);
        return cqsChargepersonService.addCqsChargeperson(cqsChargeperson)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:chargeperson:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsChargepersonEntity cqsChargeperson = cqsChargepersonService.findCqsChargepersonById(id);
        mmap.put("cqsChargeperson", cqsChargeperson);
        return prefix + "/edit";
    }

    /**
     * 修改保存单位负责人
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:chargeperson:edit")
    @BussinessLog(title = "单位负责人", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsChargeperson(CqsChargepersonEntity cqsChargeperson){
        //校验参数
		ValidatorUtil.validateEntity(cqsChargeperson);
        return cqsChargepersonService.updateCqsChargepersonById(cqsChargeperson)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:chargeperson:del")
    @BussinessLog(title = "单位负责人", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsChargepersonService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
