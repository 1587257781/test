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
import com.tenhisi.web.cgs.entity.CqsHomeInfoEntity;
import com.tenhisi.web.cgs.service.CqsHomeInfoService;

/**
 * 住房信息页面控制器
 * @author Shane
 * @date 2021-08-13 11:00:57
 */
@Controller
@RequestMapping("/cgs/HomeInfo")
public class CqsHomeInfoController extends BaseController{

    private String prefix = "modules/cgs/HomeInfo";

    @Autowired
    private CqsHomeInfoService cqsHomeInfoService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:HomeInfo:view")
    @GetMapping()
    public String HomeInfo(){
        return prefix + "/HomeInfo";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHomeInfoEntity
     * @return
     */
    @RequiresPermissions("cgs:HomeInfo:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsHomeInfoEntity cqsHomeInfoEntity) {
        PageUtil page = cqsHomeInfoService.findPage(params,cqsHomeInfoEntity);
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
    @RequiresPermissions("cgs:HomeInfo:add")
    @BussinessLog(title = "住房信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsHomeInfo(@Validated CqsHomeInfoEntity cqsHomeInfo){
        //校验参数
        ValidatorUtil.validateEntity(cqsHomeInfo);
        return cqsHomeInfoService.addCqsHomeInfo(cqsHomeInfo)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:HomeInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsHomeInfoEntity cqsHomeInfo = cqsHomeInfoService.findCqsHomeInfoById(id);
        mmap.put("cqsHomeInfo", cqsHomeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存住房信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:HomeInfo:edit")
    @BussinessLog(title = "住房信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsHomeInfo(CqsHomeInfoEntity cqsHomeInfo){
        //校验参数
		ValidatorUtil.validateEntity(cqsHomeInfo);
        return cqsHomeInfoService.updateCqsHomeInfoById(cqsHomeInfo)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:HomeInfo:del")
    @BussinessLog(title = "住房信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsHomeInfoService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
