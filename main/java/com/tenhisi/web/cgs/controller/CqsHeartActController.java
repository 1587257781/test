/*
 * All content copyright http://www.tenhisi.com, unless
 * otherwise indicated. All rights reserved.
 * No deletion without permission
 */
package com.tenhisi.web.cgs.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.business.annotaion.BussinessLog;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.common.core.enums.BusinessType;
import com.tenhisi.common.core.utils.PageUtil;
import com.tenhisi.common.core.utils.ResponseData;
import com.tenhisi.common.core.utils.ValidatorUtil;
import com.tenhisi.framework.annotation.RepeatSubmit;
import com.tenhisi.framework.sys.entity.SysDictDataEntity;
import com.tenhisi.framework.sys.service.SysDictDataService;
import com.tenhisi.web.cgs.entity.CqsBranchActEntity;
import com.tenhisi.web.cgs.entity.CqsHeartActEntity;
import com.tenhisi.web.cgs.service.CqsHeartActService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 连心活动页面控制器
 * @author Shane
 * @date 2021-09-25 17:01:39
 */
@Controller
@RequestMapping("/cgs/heartAct")
public class CqsHeartActController extends BaseController{

    private String prefix = "modules/cgs/heartAct";

    @Autowired
    private CqsHeartActService cqsHeartActService;
    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 视图页面
     */
    @RequiresPermissions("cgs:heartAct:view")
    @GetMapping(value={"/{pid}/{actype}","/{actype}",""})
    public String heartAct(@PathVariable(value="pid",required=false) Long pid,
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
        return prefix + "/heartAct";
    }

    /**
     * 查看子视图页面
     */
    @RequiresPermissions("cgs:heartAct:view")
    @GetMapping(value={"/sub/{pid}/{actype}/{subtype}","/sub/{actype}/{subtype}","/sub/{actype}"})
    public String heartActSub(@PathVariable(value="pid",required=false) Long pid,
                           @PathVariable(value="actype",required=false) String actype,
                           @PathVariable(value="subtype",required=false) String subtype,
                           ModelMap mmap){
        if(pid!=null && pid !=0) {
            mmap.put("pid", pid);
        }
        if (actype != null){
            mmap.put("actype",actype);
        }
        if (subtype != null){
            String subtypeName = ""; //子类型名称
            //获取活动主类型
            SysDictDataEntity sysDictData = sysDictDataService.getOne(new QueryWrapper<SysDictDataEntity>()
                    .eq("dict_type","cgs_com_act_type")
                    .eq("dict_value",actype));
            String subTypeStr = sysDictData.getRemark();//取得子类的json字串
            if(StrUtil.isNotBlank(subTypeStr)) {
                JSONObject subTypeJson = JSONUtil.parseObj(subTypeStr);//格式转换
                subtypeName = subTypeJson.getStr(subtype);
            }
            mmap.put("subtype",subtype);
            mmap.put("subtypeName",subtypeName);
        }
        return prefix + "/heartActSub";
    }

    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHeartActEntity
     * @return
     */
    @RequiresPermissions("cgs:heartAct:list")
    @RequestMapping(value={"/list/{pid}/{actype}","/list/{actype}","/list"})
    @ResponseBody
    public ResponseData list(@PathVariable(value="pid",required=false) Long pid,
                             @PathVariable(value="actype",required=false) String actype,
                             @RequestParam Map<String, Object> params,
                             CqsHeartActEntity cqsHeartActEntity) {
        if(pid!=null && pid !=0) {
            cqsHeartActEntity.setPid(pid);
        }
        if (StrUtil.isNotBlank(actype) && !"0".equals(actype)){
            cqsHeartActEntity.setActType(actype);
        }
        PageUtil page = cqsHeartActService.findPage(params,cqsHeartActEntity);
		return success(page);
    }

    /**
     * 页面表格分页查询
     * @param params
     * @param cqsHeartActEntity
     * @return
     */
    @RequiresPermissions("cgs:heartAct:list")
    @RequestMapping(value={"/listSub/{pid}/{actype}/{subtype}","/listSub/{actype}/{subtype}","/listSub/{actype}"})
    @ResponseBody
    public ResponseData listSub(@PathVariable(value="pid",required=false) Long pid,
                             @PathVariable(value="actype",required=false) String actype,
                             @PathVariable(value="subtype",required=false) String subtype,
                             @RequestParam Map<String, Object> params,
                             CqsHeartActEntity cqsHeartActEntity) {
        if(pid!=null && pid !=0) {
            cqsHeartActEntity.setPid(pid);
        }
        if (StrUtil.isNotBlank(actype)){
            cqsHeartActEntity.setActType(actype);
        }
        if (StrUtil.isNotBlank(subtype)){
            cqsHeartActEntity.setActSubtype(subtype);
        }
        PageUtil page = cqsHeartActService.findPage(params,cqsHeartActEntity);
        return success(page);
    }

    /**
     * 新增视图
     * @param mmap
     * @return
     */
    @GetMapping(value={"/add/{gridId}","/add/{actype}/{subtype}","/add"})
    public String add(@PathVariable(value="gridId",required=false) Long gridId,
                      @PathVariable(value="actype",required=false) String actype,
                      @PathVariable(value="subtype",required=false) String subtype,
                      ModelMap mmap){
        if (gridId != null && gridId !=0){
            SysDictDataEntity sysDictData = sysDictDataService.getById(gridId);
            String gridName = sysDictData.getDictLabel();
            mmap.put("gridName",gridName);
            mmap.put("pid",gridId);
        }
        if (StrUtil.isNotBlank(subtype)) {
            //获取活动主类型
            SysDictDataEntity sysDictData = sysDictDataService.getOne(new QueryWrapper<SysDictDataEntity>()
                    .eq("dict_type","cgs_com_act_type")
                    .eq("dict_value",actype));
            String subTypeStr = sysDictData.getRemark();//取得子类的json字串
            if(StrUtil.isNotBlank(subTypeStr)) {
                JSONObject subTypeJson = JSONUtil.parseObj(subTypeStr);//格式转换
                String subtypeName = subTypeJson.getStr(subtype);
                mmap.put("actype",actype);
                mmap.put("subtype",subtype);
                mmap.put("subtypeName",subtypeName);
            }
        }
        return prefix + "/add";
    }

    /**
     * 新增
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:heartAct:add")
    @BussinessLog(title = "连心活动", businessType = BusinessType.INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCqsHeartAct(@Validated CqsHeartActEntity cqsHeartAct){
        //校验参数
        ValidatorUtil.validateEntity(cqsHeartAct);
        return cqsHeartActService.addCqsHeartAct(cqsHeartAct)? success(): error("新增失败!");
    }

    /**
     * 修改
     */
    @RequiresPermissions("cgs:heartAct:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap){
        CqsHeartActEntity cqsHeartAct = cqsHeartActService.findCqsHeartActById(id);
        mmap.put("cqsHeartAct", cqsHeartAct);
        return prefix + "/edit";
    }

    /**
     * 修改保存连心活动
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:heartAct:edit")
    @BussinessLog(title = "连心活动", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editCqsHeartAct(CqsHeartActEntity cqsHeartAct){
        //校验参数
		ValidatorUtil.validateEntity(cqsHeartAct);
        return cqsHeartActService.updateCqsHeartActById(cqsHeartAct)? success(): error("修改失败!");
    }

    /**
     * 删除
     */
    @RepeatSubmit
    @RequiresPermissions("cgs:heartAct:del")
    @BussinessLog(title = "连心活动", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData del(Long[] ids) {
      return cqsHeartActService.deleteBatchByIds(ids)? success(): error("删除失败!");
    }

    /**
     * 浏览
     */
    @RequiresPermissions("cgs:heartAct:view")
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap){
        CqsHeartActEntity cqsHeartAct = cqsHeartActService.findCqsHeartActById(id);
        mmap.put("cqsHeartAct", cqsHeartAct);
        return prefix + "/view";
    }
}
