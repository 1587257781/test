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
import com.tenhisi.web.cgs.entity.CqsPartyMemberEntity;
import com.tenhisi.web.cgs.entity.CqsPartybranchInfoEntity;
import com.tenhisi.web.cgs.service.CqsPartyMemberService;
import com.tenhisi.web.cgs.service.CqsPartybranchInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 党员信息页面控制器
 * @author Shane
 * @date 2021-09-01 00:47:42
 */
@Controller
@RequestMapping("/cgs/partyMember")
public class CqsPartyMemberController extends BaseController{

    private String prefix = "modules/cgs/partyMember";

    @Autowired
    private CqsPartyMemberService cqsPartyMemberService;
    @Autowired
    private CqsPartybranchInfoService cqsPartybranchInfoService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:partyMember:view")
    @GetMapping()
    public String partyMember(){
        return prefix + "/partyMember";
    }
    
    /**
     * 页面表格分页查询
     * @param params
     * @param cqsPartyMemberEntity
     * @return
     */
    @RequiresPermissions("cgs:partyMember:list")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData list(@RequestParam Map<String, Object> params,CqsPartyMemberEntity cqsPartyMemberEntity) {
        PageUtil page = cqsPartyMemberService.findPage(params,cqsPartyMemberEntity);
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
        }
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:partyMember:add")
    @BussinessLog(title = "党员信息", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsPartyMember(@Validated CqsPartyMemberEntity cqsPartyMember){
        //校验参数
        ValidatorUtil.validateEntity(cqsPartyMember);
        return cqsPartyMemberService.addCqsPartyMember(cqsPartyMember)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:partyMember:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsPartyMemberEntity cqsPartyMember = cqsPartyMemberService.findCqsPartyMemberById(id);
        mmap.put("cqsPartyMember", cqsPartyMember);
        return prefix + "/edit";
    }

    /**
     * 根据身份证号码查询修改信息
     */
    @RequiresPermissions("cgs:partyMember:edit")
    @GetMapping("/editbyidcode/{idcode}")
    public String editbyidcode(@PathVariable("idcode") String idcode, ModelMap mmap){
        CqsPartyMemberEntity cqsPartyMember = cqsPartyMemberService.findCqsPartyMemberByIdcode(idcode);
        mmap.put("cqsPartyMember", cqsPartyMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存党员信息
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:partyMember:edit")
    @BussinessLog(title = "党员信息", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsPartyMember(CqsPartyMemberEntity cqsPartyMember){
        //校验参数
		ValidatorUtil.validateEntity(cqsPartyMember);
        return cqsPartyMemberService.updateCqsPartyMemberById(cqsPartyMember)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:partyMember:del")
    @BussinessLog(title = "党员信息", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsPartyMemberService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

}
