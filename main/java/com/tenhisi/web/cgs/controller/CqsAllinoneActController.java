/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.*;
import com.tenhisi.framework.annotation.RepeatSubmit;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.framework.sys.entity.SysFileEntity;
import com.tenhisi.framework.sys.entity.SysFileUploadEntity;
import com.tenhisi.framework.sys.entity.SysFilesEntity;
import com.tenhisi.framework.sys.service.SysFileService;
import com.tenhisi.framework.sys.service.SysFileUploadService;
import com.tenhisi.web.cgs.entity.CqsBranchActEntity;
import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import com.tenhisi.web.cgs.service.CqsPartybranchInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import com.tenhisi.web.cgs.entity.CqsAllinoneActEntity;
import com.tenhisi.web.cgs.service.CqsAllinoneActService;

/**
 * 一本通页面控制器
 * @author Shane
 * @date 2021-11-02 02:49:36
 */
@Controller
@RequestMapping("/cgs/allinoneAct")
public class CqsAllinoneActController extends BaseController{

    private String prefix = "modules/cgs/allinoneAct";

    @Autowired
    private CqsAllinoneActService cqsAllinoneActService;
    @Autowired
    private CqsPartybranchInfoService cqsPartybranchInfoService;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private SysFileUploadService sysFileUploadService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:allinoneAct:view")
    @GetMapping(value={"/{actype}/{pid}","/{actype}",""})
    public String allinoneAct(@PathVariable(value="actype",required=false) String actype,
                              @PathVariable(value="pid",required=false) Long pid,
                              ModelMap mmap){
        mmap.put("actype",actype);
        mmap.put("pid",pid);
        return prefix + "/allinoneAct";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsAllinoneActEntity
     * @return
     */
    @RequiresPermissions("cgs:allinoneAct:list")
    @RequestMapping(value={"/list/{actype}/{pid}","/list/{actype}","/list"})
    @ResponseBody
    public ResponseData list(@PathVariable(value="actype",required=false) String actype,
                             @PathVariable(value="pid",required=false) Long pid,
                             @RequestParam Map<String, Object> params,
                             CqsAllinoneActEntity cqsAllinoneActEntity) {
        if(StrUtil.isNotBlank(actype)){
            cqsAllinoneActEntity.setActType(actype);
        }
        if(pid !=null && pid !=0){
            cqsAllinoneActEntity.setPid(pid);
        }
        PageUtil page = cqsAllinoneActService.findPage(params,cqsAllinoneActEntity);
		return success(page);
    }

    /**
     * 浏览
     */
    @RequiresPermissions("cgs:allinoneAct:view")
    @GetMapping(value="/view/{id}")
    public String view(@PathVariable("id") Long id,
                       @PathVariable(value="branchId",required=false) Long branchId,
                       ModelMap mmap){
        CqsAllinoneActEntity cqsAllinoneAct = new CqsAllinoneActEntity();
        if(id!=null && id !=0) {
            cqsAllinoneAct = cqsAllinoneActService.findCqsAllinoneActById(id);
            List<SysFilesEntity> sysFilesEntities = sysFileService.selectSysFilesList(
                    Convert.toStr(cqsAllinoneAct.getId()), "cqs_allinone_act_file");
            cqsAllinoneAct.setSysFiles(sysFilesEntities);
        }
        mmap.put("cqsAllinoneAct", cqsAllinoneAct);

        return prefix + "/view";
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap){
        List<CqsPartybranchInfoEntity> cqsPartybranchInfoEntitys = cqsPartybranchInfoService.list();
        mmap.put("branchList",cqsPartybranchInfoEntitys);
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:allinoneAct:add")
    @BussinessLog(title = "一本通", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsAllinoneAct(@Validated CqsAllinoneActEntity cqsAllinoneAct){
        //校验参数
        ValidatorUtil.validateEntity(cqsAllinoneAct);
        return cqsAllinoneActService.addCqsAllinoneAct(cqsAllinoneAct)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:allinoneAct:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsAllinoneActEntity cqsAllinoneAct = cqsAllinoneActService.findCqsAllinoneActById(id);
        mmap.put("cqsAllinoneAct", cqsAllinoneAct);
        return prefix + "/edit";
    }

    /**
     * 修改保存一本通
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:allinoneAct:edit")
    @BussinessLog(title = "一本通", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsAllinoneAct(CqsAllinoneActEntity cqsAllinoneAct){
        //校验参数
		ValidatorUtil.validateEntity(cqsAllinoneAct);
        return cqsAllinoneActService.updateCqsAllinoneActById(cqsAllinoneAct)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:allinoneAct:del")
    @BussinessLog(title = "一本通", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsAllinoneActService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
