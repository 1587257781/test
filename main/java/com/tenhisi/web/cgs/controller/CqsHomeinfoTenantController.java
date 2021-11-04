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
import com.tenhisi.framework.sys.service.SysCompService;
import com.tenhisi.web.cgs.entity.CqsHomeInfoEntity;
import com.tenhisi.web.cgs.service.CqsHomeInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.ModelMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.tenhisi.web.cgs.entity.CqsHomeinfoTenantEntity;
import com.tenhisi.web.cgs.service.CqsHomeinfoTenantService;

/**
 * 租客信息页面控制器
 * @author Shane
 * @date 2021-08-13 11:01:01
 */
@Controller
@RequestMapping("/cgs/homeinfoTenant")
public class CqsHomeinfoTenantController extends BaseController{

    private String prefix = "modules/cgs/homeinfoTenant";

    @Autowired
    private CqsHomeinfoTenantService cqsHomeinfoTenantService;

    @Autowired
    private CqsHomeInfoService cqsHomeInfoService;

    @Autowired
    private SysCompService sysCompService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:homeinfoTenant:view")
    @GetMapping()
    public String homeinfoTenant(){
        return prefix + "/homeinfoTenant";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHomeinfoTenantEntity
     * @return
     */
    @RequiresPermissions("cgs:homeinfoTenant:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsHomeinfoTenantEntity cqsHomeinfoTenantEntity) {
        PageUtil page = cqsHomeinfoTenantService.findPage(params,cqsHomeinfoTenantEntity);
		return success(page);
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping(value={"/add/{homeId}","/add"})
    public String add(@PathVariable(value="homeId",required=false) Long homeId, ModelMap mmap){
        if (homeId != null){
            CqsHomeInfoEntity cgsHomeInfo = cqsHomeInfoService.findCqsHomeInfoById(homeId);
            String compName = sysCompService.findCompById(cgsHomeInfo.getCompId()).getFullName();
            mmap.put("title",compName+cgsHomeInfo.getHouseCode()+"栋"+cgsHomeInfo.getUnitCode()+"单元"+cgsHomeInfo.getHomeCode()+"室");
            mmap.put("pid",homeId);
        }
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoTenant:add")
    @BussinessLog(title = "租客信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsHomeinfoTenant(@Validated CqsHomeinfoTenantEntity cqsHomeinfoTenant){
        //校验参数
        ValidatorUtil.validateEntity(cqsHomeinfoTenant);
        return cqsHomeinfoTenantService.addCqsHomeinfoTenant(cqsHomeinfoTenant)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:homeinfoTenant:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsHomeinfoTenantEntity cqsHomeinfoTenant = cqsHomeinfoTenantService.findCqsHomeinfoTenantById(id);
        mmap.put("cqsHomeinfoTenant", cqsHomeinfoTenant);
        return prefix + "/edit";
    }

    /**
     * 修改保存租客信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoTenant:edit")
    @BussinessLog(title = "租客信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsHomeinfoTenant(CqsHomeinfoTenantEntity cqsHomeinfoTenant){
        //校验参数
		ValidatorUtil.validateEntity(cqsHomeinfoTenant);
        return cqsHomeinfoTenantService.updateCqsHomeinfoTenantById(cqsHomeinfoTenant)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoTenant:del")
    @BussinessLog(title = "租客信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsHomeinfoTenantService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
