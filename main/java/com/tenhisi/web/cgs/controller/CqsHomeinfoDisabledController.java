/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.controller;

import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ResponseData;
import com.tenhisi.common.core.utils.ValidatorUtil;
import com.tenhisi.framework.annotation.RepeatSubmit;
import com.tenhisi.web.cgs.entity.CqsHomeinfoDisabledEntity;
import com.tenhisi.web.cgs.entity.CqsHomeinfoPeopleEntity;
import com.tenhisi.web.cgs.service.CqsHomeinfoDisabledService;
import com.tenhisi.web.cgs.service.CqsHomeinfoPeopleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 残疾人信息页面控制器
 * @author Shane
 * @date 2021-09-06 02:39:54
 */
@Controller
@RequestMapping("/cgs/homeinfoDisabled")
public class CqsHomeinfoDisabledController extends BaseController{

    private String prefix = "modules/cgs/homeinfoDisabled";

    @Autowired
    private CqsHomeinfoDisabledService cqsHomeinfoDisabledService;
    @Autowired
    private CqsHomeinfoPeopleService cqsHomeinfoPeopleService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:homeinfoDisabled:view")
    @GetMapping()
    public String homeinfoDisabled(){
        return prefix + "/homeinfoDisabled";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHomeinfoDisabledEntity
     * @return
     */
    @RequiresPermissions("cgs:homeinfoDisabled:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsHomeinfoDisabledEntity cqsHomeinfoDisabledEntity) {
        PageUtil page = cqsHomeinfoDisabledService.findPage(params,cqsHomeinfoDisabledEntity);
		return success(page);
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping(value={"/add/{pid}","/add"})
    public String add(@PathVariable(value="pid",required=false) Long pid, ModelMap mmap){
        if (pid != null){
            CqsHomeinfoPeopleEntity cgsHomeinfoPeople = cqsHomeinfoPeopleService.findCqsHomeinfoPeopleById(pid);
            mmap.put("title",cgsHomeinfoPeople.getName());
            mmap.put("pid",pid);
        }
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoDisabled:add")
    @BussinessLog(title = "残疾人信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsHomeinfoDisabled(@Validated CqsHomeinfoDisabledEntity cqsHomeinfoDisabled){
        //校验参数
        ValidatorUtil.validateEntity(cqsHomeinfoDisabled);
        return cqsHomeinfoDisabledService.addCqsHomeinfoDisabled(cqsHomeinfoDisabled)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:homeinfoDisabled:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsHomeinfoDisabledEntity cqsHomeinfoDisabled = cqsHomeinfoDisabledService.findCqsHomeinfoDisabledById(id);
        mmap.put("cqsHomeinfoDisabled", cqsHomeinfoDisabled);
        return prefix + "/edit";
    }

    /**
     * 根据身份证号码查询修改信息
     */
    @RequiresPermissions("cgs:homeinfoDisabled:edit")
    @GetMapping("/editbyidcode/{idcode}")
    public String editbyidcode(@PathVariable("idcode") String idcode, ModelMap mmap){
        CqsHomeinfoDisabledEntity cqsHomeinfoDisabled = cqsHomeinfoDisabledService.findCqsHomeinfoDisabledByIdcode(idcode);
        mmap.put("cqsHomeinfoDisabled", cqsHomeinfoDisabled);
        return prefix + "/edit";
    }

    /**
     * 修改保存残疾人信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoDisabled:edit")
    @BussinessLog(title = "残疾人信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsHomeinfoDisabled(CqsHomeinfoDisabledEntity cqsHomeinfoDisabled){
        //校验参数
		ValidatorUtil.validateEntity(cqsHomeinfoDisabled);
        return cqsHomeinfoDisabledService.updateCqsHomeinfoDisabledById(cqsHomeinfoDisabled)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoDisabled:del")
    @BussinessLog(title = "残疾人信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsHomeinfoDisabledService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
