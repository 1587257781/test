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
import com.tenhisi.framework.sys.service.SysCompService;
import com.tenhisi.web.cgs.entity.CqsHomeInfoEntity;
import com.tenhisi.web.cgs.entity.CqsHomeinfoPeopleEntity;
import com.tenhisi.web.cgs.service.CqsHomeInfoService;
import com.tenhisi.web.cgs.service.CqsHomeinfoPeopleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 家庭成员页面控制器
 * @author Shane
 * @date 2021-09-05 22:10:19
 */
@Controller
@RequestMapping("/cgs/homeinfoPeople")
public class CqsHomeinfoPeopleController extends BaseController{

    private String prefix = "modules/cgs/homeinfoPeople";

    @Autowired
    private CqsHomeinfoPeopleService cqsHomeinfoPeopleService;

    @Autowired
    private CqsHomeInfoService cqsHomeInfoService;

    @Autowired
    private SysCompService sysCompService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:homeinfoPeople:view")
    @GetMapping()
    public String homeinfoPeople(){
        return prefix + "/homeinfoPeople";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHomeinfoPeopleEntity
     * @return
     */
    @RequiresPermissions("cgs:homeinfoPeople:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsHomeinfoPeopleEntity cqsHomeinfoPeopleEntity) {
        PageUtil page = cqsHomeinfoPeopleService.findPage(params,cqsHomeinfoPeopleEntity);
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
    @RequiresPermissions("cgs:homeinfoPeople:add")
    @BussinessLog(title = "家庭成员", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsHomeinfoPeople(@Validated CqsHomeinfoPeopleEntity cqsHomeinfoPeople){
        //校验参数
        ValidatorUtil.validateEntity(cqsHomeinfoPeople);
        return cqsHomeinfoPeopleService.addCqsHomeinfoPeople(cqsHomeinfoPeople)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:homeinfoPeople:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsHomeinfoPeopleEntity cqsHomeinfoPeople = cqsHomeinfoPeopleService.findCqsHomeinfoPeopleById(id);
        mmap.put("cqsHomeinfoPeople", cqsHomeinfoPeople);
        return prefix + "/edit";
    }

    /**
     * 根据身份证号码查询修改信息
     */
    @RequiresPermissions("cgs:homeinfoPeople:edit")
    @GetMapping("/editbyidcode/{idcode}")
    public String editbyidcode(@PathVariable("idcode") String idcode, ModelMap mmap){
        CqsHomeinfoPeopleEntity cqsHomeinfoPeople = cqsHomeinfoPeopleService.findCqsHomeinfoPeopleByIdcode(idcode);
        mmap.put("cqsHomeinfoPeople", cqsHomeinfoPeople);
        return prefix + "/edit";
    }

    /**
     * 修改保存家庭成员
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoPeople:edit")
    @BussinessLog(title = "家庭成员", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsHomeinfoPeople(CqsHomeinfoPeopleEntity cqsHomeinfoPeople){
        //校验参数
		ValidatorUtil.validateEntity(cqsHomeinfoPeople);
        return cqsHomeinfoPeopleService.updateCqsHomeinfoPeopleById(cqsHomeinfoPeople)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:homeinfoPeople:del")
    @BussinessLog(title = "家庭成员", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsHomeinfoPeopleService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
