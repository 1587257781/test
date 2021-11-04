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
import com.tenhisi.web.cgs.entity.CqsVisitRecordEntity;
import com.tenhisi.web.cgs.service.CqsVisitRecordService;

/**
 * 走访记录页面控制器
 * @author Shane
 * @date 2021-08-19 17:16:30
 */
@Controller
@RequestMapping("/cgs/visitRecord")
public class CqsVisitRecordController extends BaseController{

    private String prefix = "modules/cgs/visitRecord";

    @Autowired
    private CqsVisitRecordService cqsVisitRecordService;

    @Autowired
    private CqsHomeInfoService cqsHomeInfoService;

    @Autowired
    private SysCompService sysCompService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:visitRecord:view")
    @GetMapping()
    public String visitRecord(){
        return prefix + "/visitRecord";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsVisitRecordEntity
     * @return
     */
    @RequiresPermissions("cgs:visitRecord:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsVisitRecordEntity cqsVisitRecordEntity) {
        PageUtil page = cqsVisitRecordService.findPage(params,cqsVisitRecordEntity);
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
    @RequiresPermissions("cgs:visitRecord:add")
    @BussinessLog(title = "走访记录", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsVisitRecord(@Validated CqsVisitRecordEntity cqsVisitRecord){
        //校验参数
        ValidatorUtil.validateEntity(cqsVisitRecord);
        return cqsVisitRecordService.addCqsVisitRecord(cqsVisitRecord)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:visitRecord:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsVisitRecordEntity cqsVisitRecord = cqsVisitRecordService.findCqsVisitRecordById(id);
        mmap.put("cqsVisitRecord", cqsVisitRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存走访记录
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:visitRecord:edit")
    @BussinessLog(title = "走访记录", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsVisitRecord(CqsVisitRecordEntity cqsVisitRecord){
        //校验参数
		ValidatorUtil.validateEntity(cqsVisitRecord);
        return cqsVisitRecordService.updateCqsVisitRecordById(cqsVisitRecord)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:visitRecord:del")
    @BussinessLog(title = "走访记录", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsVisitRecordService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
