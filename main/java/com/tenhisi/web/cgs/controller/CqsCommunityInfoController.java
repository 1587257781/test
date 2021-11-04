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
import com.tenhisi.web.cgs.entity.CqsCommunityInfoEntity;
import com.tenhisi.web.cgs.service.CqsCommunityInfoService;

/**
 * 社区信息页面控制器
 * @author Shane
 * @date 2021-08-02 22:47:39
 */
@Controller
@RequestMapping("/cgs/CommunityInfo")
public class CqsCommunityInfoController extends BaseController{

    private String prefix = "modules/cgs/CommunityInfo";

    @Autowired
    private CqsCommunityInfoService cqsCommunityInfoService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:CommunityInfo:view")
    @GetMapping()
    public String CommunityInfo(){
        return prefix + "/CommunityInfo";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsCommunityInfoEntity
     * @return
     */
    @RequiresPermissions("cgs:CommunityInfo:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsCommunityInfoEntity cqsCommunityInfoEntity) {
        PageUtil page = cqsCommunityInfoService.findPage(params,cqsCommunityInfoEntity);
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
    @RequiresPermissions("cgs:CommunityInfo:add")
    @BussinessLog(title = "社区信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsCommunityInfo(@Validated CqsCommunityInfoEntity cqsCommunityInfo){
        //校验参数
        ValidatorUtil.validateEntity(cqsCommunityInfo);
        return cqsCommunityInfoService.addCqsCommunityInfo(cqsCommunityInfo)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:CommunityInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsCommunityInfoEntity cqsCommunityInfo = cqsCommunityInfoService.findCqsCommunityInfoById(id);
        mmap.put("cqsCommunityInfo", cqsCommunityInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存社区信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:CommunityInfo:edit")
    @BussinessLog(title = "社区信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsCommunityInfo(CqsCommunityInfoEntity cqsCommunityInfo){
        //校验参数
		ValidatorUtil.validateEntity(cqsCommunityInfo);
        return cqsCommunityInfoService.updateCqsCommunityInfoById(cqsCommunityInfo)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:CommunityInfo:del")
    @BussinessLog(title = "社区信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsCommunityInfoService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
