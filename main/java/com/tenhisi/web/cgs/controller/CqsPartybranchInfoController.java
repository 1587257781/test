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
import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import com.tenhisi.web.cgs.service.CqsPartybranchInfoService;
import com.tenhisi.web.cgs.service.CqsPartybranchChildService;

/**
 * 支部信息页面控制器
 * @author Shane
 * @date 2021-08-16 15:20:18
 */
@Controller
@RequestMapping("/cgs/partyBranchInfo")
public class CqsPartybranchInfoController extends BaseController{

    private String prefix = "modules/cgs/partyBranchInfo";

    @Autowired
    private CqsPartybranchInfoService cqsPartybranchInfoService;
    @Autowired
    private CqsPartybranchChildService cqsPartybranchChildService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:partyBranchInfo:view")
    @GetMapping()
    public String partyBranchInfo(){
        return prefix + "/partyBranchInfo";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsPartybranchInfoEntity
     * @return
     */
    @RequiresPermissions("cgs:partyBranchInfo:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsPartybranchInfoEntity cqsPartybranchInfoEntity) {
        PageUtil page = cqsPartybranchInfoService.findPage(params,cqsPartybranchInfoEntity);
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
    @RequiresPermissions("cgs:partyBranchInfo:add")
    @BussinessLog(title = "支部信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsPartybranchInfo(@Validated CqsPartybranchInfoEntity cqsPartybranchInfo){
        //校验参数
        ValidatorUtil.validateEntity(cqsPartybranchInfo);
        return cqsPartybranchInfoService.addCqsPartybranchInfo(cqsPartybranchInfo)? success(): error("新增失败!");
    }

    @RequiresPermissions("cgs:partyBranchInfo:list")
    @RequestMapping(value = "/child/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData childList(@RequestParam Map<String, Object> params) {

        PageUtil page = cqsPartybranchChildService.findPage(params);

        return success(page);
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:partyBranchInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsPartybranchInfoEntity cqsPartybranchInfo = cqsPartybranchInfoService.findCqsPartybranchInfoById(id);
        mmap.put("cqsPartybranchInfo", cqsPartybranchInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存支部信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:partyBranchInfo:edit")
    @BussinessLog(title = "支部信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsPartybranchInfo(CqsPartybranchInfoEntity cqsPartybranchInfo){
        //校验参数
		ValidatorUtil.validateEntity(cqsPartybranchInfo);
        return cqsPartybranchInfoService.updateCqsPartybranchInfoById(cqsPartybranchInfo)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:partyBranchInfo:del")
    @BussinessLog(title = "支部信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsPartybranchInfoService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
