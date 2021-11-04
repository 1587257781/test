/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ResponseData;
import com.tenhisi.common.core.utils.ValidatorUtil;
import com.tenhisi.framework.annotation.RepeatSubmit;
import com.tenhisi.web.cgs.entity.CqsBranchActEntity;
import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import com.tenhisi.web.cgs.service.CqsBranchActService;
import com.tenhisi.web.cgs.service.CqsPartybranchInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 支部活动页面控制器
 * @author Shane
 * @date 2021-09-01 01:18:55
 */
@Controller
@RequestMapping("/cgs/branchAct")
public class CqsBranchActController extends BaseController{

    private String prefix = "modules/cgs/branchAct";

    @Autowired
    private CqsBranchActService cqsBranchActService;
    @Autowired
    private CqsPartybranchInfoService cqsPartybranchInfoService;
    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:branchAct:view")
    @GetMapping(value={"/{pid}/{actype}","/{pid}",""})
    public String branchAct(@PathVariable(value="pid",required=false) Long pid,
                            @PathVariable(value="actype",required=false) String actype,
                            ModelMap mmap){
        if (pid != null){
            mmap.put("pid",pid);
        }else{
            mmap.put("pid",0);
        }
        if (actype != null){
            mmap.put("actype",actype);
        }else{
            mmap.put("actype",0);
        }
        return prefix + "/branchAct";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsBranchActEntity
     * @return
     */
    @RequiresPermissions("cgs:branchAct:list")
    @RequestMapping(value={"/list/{pid}/{actype}","/list/{pid}","/list"})
    @ResponseBody
    public ResponseData list(@PathVariable(value="pid",required=false) Long pid,
                             @PathVariable(value="actype",required=false) String actype,
                             @RequestParam Map<String, Object> params,
                             CqsBranchActEntity cqsBranchActEntity) {
        if(pid!=null && pid !=0) {
            cqsBranchActEntity.setPid(pid);
        }
        if (!"".equals(actype)){
            cqsBranchActEntity.setActType(actype);
        }
        PageUtil page = cqsBranchActService.findPage(params,cqsBranchActEntity);
		return success(page);
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping(value={"/add/{branchId}","/add"})
    public String add(@PathVariable(value="branchId",required=false) Long branchId, ModelMap mmap){
        if (branchId != null){
            CqsPartybranchInfoEntity cqsPartybranchInfo = cqsPartybranchInfoService.findCqsPartybranchInfoById(branchId);
            String branchName = cqsPartybranchInfo.getBranchName();
            mmap.put("branchName",branchName);
            mmap.put("pid",branchId);
        }else{
            mmap.put("branchName","");
            mmap.put("pid","");
        }
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:branchAct:add")
    @BussinessLog(title = "支部活动", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsBranchAct(@Validated CqsBranchActEntity cqsBranchAct){
        //校验参数
        ValidatorUtil.validateEntity(cqsBranchAct);
        return cqsBranchActService.addCqsBranchAct(cqsBranchAct)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:branchAct:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsBranchActEntity cqsBranchAct = cqsBranchActService.findCqsBranchActById(id);
        mmap.put("cqsBranchAct", cqsBranchAct);
        return prefix + "/edit";
    }

    /**
     * 修改保存支部活动
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:branchAct:edit")
    @BussinessLog(title = "支部活动", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsBranchAct(CqsBranchActEntity cqsBranchAct){
        //校验参数
		ValidatorUtil.validateEntity(cqsBranchAct);
        return cqsBranchActService.updateCqsBranchActById(cqsBranchAct)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:branchAct:del")
    @BussinessLog(title = "支部活动", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsBranchActService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }


    /**
     * 浏览
     */
    @RequiresPermissions("cgs:branchAct:view")
    @GetMapping(value="/view/{id}")
    public String view(@PathVariable("id") Long id,
                       @PathVariable(value="branchId",required=false) Long branchId,
                       ModelMap mmap){
        CqsBranchActEntity cqsBranchAct = new CqsBranchActEntity();
        if(id!=null && id !=0) {
            cqsBranchAct = cqsBranchActService.findCqsBranchActById(id);
        }
        mmap.put("cqsBranchAct", cqsBranchAct);
        return prefix + "/view";
    }

    /**
     * 浏览
     */
    @RequiresPermissions("cgs:branchAct:view")
    @GetMapping(value="/viewb/{branchId}")
    public String viewb(@PathVariable(value="branchId",required=false) Long branchId,
                       ModelMap mmap){
        CqsBranchActEntity cqsBranchAct = new CqsBranchActEntity();
        if(branchId!=null && branchId !=0) {
            cqsBranchAct = cqsBranchActService.getOne(new QueryWrapper<CqsBranchActEntity>()
                    .eq("pid",branchId)
                    .eq("act_type","0")
                    .orderByDesc("id")
                    .last("limit 1"));
        }
        mmap.put("cqsBranchAct", cqsBranchAct);
        return prefix + "/view";
    }
}
