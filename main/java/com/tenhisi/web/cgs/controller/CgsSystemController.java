package com.tenhisi.web.cgs.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tenhisi.common.core.controller.BaseController;
import com.tenhisi.framework.sys.entity.SysCompEntity;
import com.tenhisi.framework.sys.entity.SysDictDataEntity;
import com.tenhisi.framework.sys.entity.SysFilesEntity;
import com.tenhisi.framework.sys.service.SysCompService;
import com.tenhisi.framework.sys.service.SysDictDataService;
import com.tenhisi.framework.sys.service.SysFileService;
import com.tenhisi.framework.utils.Global;
import com.tenhisi.web.cgs.entity.*;
import com.tenhisi.web.cgs.service.*;
import com.tenhisi.web.utils.CopyUtil;
import com.tenhisi.web.utils.XxteaUtils;
import com.tenhisi.web.vo.HomeInfoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cgs")
public class CgsSystemController extends BaseController {

    private String prefix = "modules/cgs";

    @Autowired
    private SysDictDataService sysDictDataService;

    @Autowired
    private SysCompService sysCompService;

    @Autowired
    private CqsHomeInfoService cqsHomeInfoService;

    @Autowired
    private CqsHomeinfoPeopleService cqsHomeinfoPeopleService;

    @Autowired
    private CqsHomeinfoTenantService cqsHomeinfoTenantService;

    @Autowired
    private CqsPartybranchInfoService cqsPartybranchInfoService;

    @Autowired
    private CqsPartybranchChildService cqsPartybranchChildService;

    @Autowired
    private CqsBranchActService cqsBranchActService;

    @Autowired
    private CqsVisitRecordService cqsVisitRecordService;

    @Autowired
    private SysFileService sysFileService;

    /**
     * 默认主页
     * @return
     */
    @RequestMapping("mapMain")
    public String mapMain(ModelMap mmap) {
        return prefix +"/mapMain";
    }

    /**
     * 网格连心 党建地图
     * @return
     */
    @RequestMapping("partyMain")
    public String partyMain(ModelMap mmap) {
        List<CqsPartybranchInfoEntity> cqsPartybranchInfoEntities =
                cqsPartybranchInfoService.list(new QueryWrapper<CqsPartybranchInfoEntity>().eq("comp_id","1239875326212898817"));
        mmap.put("partybranchList",cqsPartybranchInfoEntities);
        return prefix +"/partyMain";
    }

    /**
     * 服务联动
     * @return
     */
    @RequestMapping("servMain")
    public String servMain(ModelMap mmap) {
        return prefix +"/servMain";
    }

    /**
     * 网格管理
     * @return
     */
    @RequestMapping("gridMain")
    public String gridMain(ModelMap mmap) {
        return prefix +"/gridMain";
    }

    /**
     * 微治理
     * @param mmap
     * @return
     */
    @RequestMapping("mrcio/govern")
    public String mrcioGovern(ModelMap mmap) {
        return prefix +"/mrcioGovern";
    }

    /**
     * 微协商
     * @param mmap
     * @return
     */
    @RequestMapping("mrcio/consult")
    public String mrcioConsult(ModelMap mmap) {
        return prefix +"/mrcioConsult";
    }


    /**
     * 一本通
     * @param mmap
     * @return
     */
    @RequestMapping("allinonePassbook")
    public String allinonePassbook(ModelMap mmap) {
        List<SysDictDataEntity> list = sysDictDataService.selectDictDataByType("cgs_allinonePassbook");
        mmap.put("allinonePassbooks",list);
        return prefix +"/allinonePassbook";
    }

    @RequestMapping("testMain")
    public String testMain(ModelMap mmap) {
        return prefix +"/testMain";
    }

    /**
     * 智慧物业
     * @return
     */
    @RequestMapping("camMain")
    public String camMain(ModelMap mmap) {
        JSONObject jo_LngLat = JSONUtil.createObj();

        jo_LngLat.set("1","118.489730,31.682040");
        jo_LngLat.set("2","118.490500,31.682410");
        jo_LngLat.set("3","118.490130,31.681540");
        jo_LngLat.set("4","118.489760,31.681510");
        jo_LngLat.set("5","118.489030,31.681220");
        jo_LngLat.set("6","118.489560,31.681060");
        jo_LngLat.set("7","118.490170,31.681200");
        //私钥
        String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL/d6TNsYXQ9FEwy" +
                "fx58ezSpHvhvi4vGJ/IxJt5U/v6UWYdnpa10lbT8MvjuGLk9UsnRgexL5p8ZcbgV" +
                "APhQ9y8+EUZo2fN2KRFzP/Ietsl+4j0/losAHBjkjXK4P2Xv9j4F17jnu63QuTW/" +
                "DQvyh+wxWW4H3s2/qGLSw5bstuxjAgMBAAECgYAo49NCSVPPoQDFaHGc/qyHQY+/" +
                "JI6Z4EY9IGqHMZgSi201JUqy18jcBG+ci6mrOL1/E25b/KUOvS52K8vEIAU9poje" +
                "3KxTvLMTljUWtOmt9nqksVcobBOF/LCJ851b87uGXc9PFoCT7SaQmi0xt4hRNKn5" +
                "si+Px1HcTXoLPNx5MQJBAO4F4sGDR7Qd8NM65EH24Y1Kup4/R/bJOVG00+iVGcoa" +
                "55Vyvw5NcEeRrfEvZEJbihPZvG3mNvCmqYazd20lxMsCQQDOW5uQsumw32hgqaGO" +
                "g8VovK2cohEH5SMOidS2tsAvw5ThKO6sEpszlgWQSM7OnfjtCKi+4Xobkr/HhS0/" +
                "KhvJAkAOLN2PRsE7cdkIy2YSo6BVKNEqYXxorx0xx4IjRNXvWmDWXqoQMP5x1LQ2" +
                "O+tNpGP5wKrfJKm69UH9WqzPHEO3AkEAqCxcMaa9cNoBVJWqBl05aswpqPcjbg29" +
                "bkHBy05QfhyknoMfT7iyJ25iBl5vvE9d6L8f1sAnJYWJKe9NGqcUkQJAITEom07D" +
                "vkjWJ+6vQxfyi2eIFh/WLEZmcwBxRyH4afZbSQVhk2Oi+TrTH0rTiIWTdvNVkaFu" +
                "xIFS3KjWC8IQxg==";


        RSA rsa = new RSA(PRIVATE_KEY, null);
        String encFilePath = "C:\\wwwroot\\webapp\\deviceCode.file";
        Path encPath = Paths.get(encFilePath);
        byte[] encBytes = new byte[0];
        try {
            encBytes = Files.readAllBytes(encPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] decrypt = rsa.decrypt(encBytes, KeyType.PrivateKey);
        String str = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        System.out.println(str);
        List<Map<String,String>> listadr = new ArrayList<>();
        JSONArray jsonArray = JSONUtil.parseObj(str).getJSONArray("subs");
        List<Dict> list = JSONUtil.toList(jsonArray, Dict.class);
        int i = 0;
        for (Dict dict : list) {
            if (i < jo_LngLat.size()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", dict.getStr("id"));
                map.put("name", dict.getStr("name"));
                String live = getLiveAddr(PRIVATE_KEY, dict.getStr("id"));//获取HLS流
                map.put("live", HexUtil.encodeHexStr(live));
                String lnglat = jo_LngLat.getStr(Convert.toStr(i+1));
                System.out.println("lnglat===="+lnglat);
                map.put("lng", StrUtil.split(lnglat,',').get(0));
                map.put("lat", StrUtil.split(lnglat,',').get(1));
                listadr.add(map);
            }
            i++;
        }
        mmap.put("jo_LngLat",jo_LngLat);
        mmap.put("listadr",listadr);
        System.out.println(listadr.size());
        return prefix +"/camMain";
    }

    private String getLiveDir(String region_id){
        String appid = "maanshan_zhsq";
        String secret = "XGUVwvWwB2ElT0VAJB6o9w";
        byte[] key = secret.getBytes();
        Long timestamp = DateUtil.current();
         //驱动参数
        String params = HexUtil.encodeHexStr(XxteaUtils.encrypt("region_id=1534873".getBytes(),key));
        HMac hmacsha256 = new HMac(HmacAlgorithm.HmacSHA256,key);
        String signature = hmacsha256.digestHex(appid+params+timestamp);
        String ss = Global.getConfig("CGS_LIVE_URL");
        String curl = "http://220.180.38.88:9001/h2/v1/tree/list_dir?appid="+appid+"&params="+params+"&signature="+signature+"&timestamp="+timestamp;
        String curlstr = "curl \""+curl+"\" -S";
        System.out.println(curlstr);
        curlstr = RuntimeUtil.execForStr(curlstr);
        System.out.println(curlstr);
        return curlstr;
    }

    /**
     * 通过设备编码查到监控地址
     * @param PRIVATE_KEY
     * @param device_code
     * @return
     */
    private String getLiveAddr(String PRIVATE_KEY,String device_code){
        System.out.println(device_code);
        String appid = "maanshan_zhsq";
        String secret = "XGUVwvWwB2ElT0VAJB6o9w";
        byte[] key = secret.getBytes();
        Long timestamp = DateUtil.current();
        //设备参数
        String params = "device_code="+device_code+"&protocol=HLS";
        params = HexUtil.encodeHexStr(XxteaUtils.encrypt(params.getBytes(),key));
        HMac hmacsha256 = new HMac(HmacAlgorithm.HmacSHA256,key);
        String signature = hmacsha256.digestHex(appid+params+timestamp);
        String curl = Global.getConfig("CGS_LIVE_URL")+"/h2/v1/tree/live_addr?appid="+appid+"&params="+params+"&signature="+signature+"&timestamp="+timestamp;
        //String curlstr = "curl \""+curl+"\" -s";
        //curlstr = RuntimeUtil.execForStr(curlstr);
        //System.out.println(curlstr);
        RSA rsa = new RSA(PRIVATE_KEY, null);
        byte[] encBytes = HttpUtil.downloadBytes(curl);//直接获取文件流
        byte[] decrypt = rsa.decrypt(encBytes, KeyType.PrivateKey);
        String curlstr = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        System.out.println("curlstr1="+curlstr);
        //转换实际地址为转向链接地址，为了解决IP限制问题
        curlstr = StrUtil.replace(curlstr,"220.180.38.89:9002","223.240.76.158:83");
        System.out.println("curlstr2="+curlstr);
        return curlstr;
    }

    /**
     * 天翼社区
     * @return
     */
    @RequestMapping("tianyiMain")
    public String tianyiMain(ModelMap mmap) {
        return prefix +"/tianyiMain";
    }

    /**
     * 网格信息
     * @return
     */
    @GetMapping()
    @RequestMapping(value={"/gridShow/{gridCode}","/gridShow"})
    public String gridShow(@PathVariable String gridCode, ModelMap mmap) {
        //设备session
        //StpUtil.getSession().set("gridCode", gridCode);
        String gridName = "";
        JSONObject jo_LngLat = JSONUtil.createObj();
        JSONObject td_LngLat = JSONUtil.createObj();
        //获取网格信息
        SysDictDataEntity sysDictDataEntity = sysDictDataService.getOne(new QueryWrapper<SysDictDataEntity>()
                .eq("dict_type","cgs_grid_set")
                .eq("dict_value",gridCode)
        );
        if (sysDictDataEntity != null) {
            gridName = sysDictDataEntity.getDictLabel();
            String marker = sysDictDataEntity.getRemark();
            if (!"".equals(marker)) {
                //获取网格所有楼的经纬度
                JSONObject jo_getRemark = JSONUtil.parseObj(marker);
                //这里比较单一，只取半山花园的数据。1001是半山花园小区的编号
                jo_LngLat = jo_getRemark.getJSONObject("1001");
                //获取map覆盖图的对角线点值
                td_LngLat = jo_getRemark.getJSONObject("mapPicPoint");
            }
        }
        mmap.put("compName","半山花园");
        mmap.put("gridName",gridName);
        mmap.put("gridMapPic","gridShow"+gridCode+".png");
        mmap.put("jo_LngLat",jo_LngLat);
        mmap.put("td_LngLat",td_LngLat);
        return  prefix +"/gridShow";
    }

    /**
     * 楼栋信息
     * @return
     */
    @RequestMapping("buildingShow/{compCode}/{buildingCode}")
    public String buildingShow(@PathVariable String compCode,@PathVariable String buildingCode,ModelMap mmap) {
        //根据序号获取对应单位信息，这里需要配置时要求唯一。
        SysCompEntity sysCompEntity = sysCompService.getOne(new QueryWrapper<SysCompEntity>()
                .eq("order_num",compCode));
        Long compId = sysCompEntity.getId();//获取单位（小区）的id
        SysDictDataEntity sysDictDataEntity = sysDictDataService.getOne(new QueryWrapper<SysDictDataEntity>()
                .eq("dict_type","cgs_house_type")
                .eq("dict_value",compId)
                .eq("dict_label",buildingCode));
        //这里不够健壮，可能有空值
        int unitNum = Convert.toInt(sysDictDataEntity.getCssClass());//这里存的是单元数
        String houseInfo = sysDictDataEntity.getCssStyle();//这里存的是楼屋信息，6,2代表6层，每层2户
        int[] houseInfos = StrUtil.splitToInt(houseInfo,',');
        int colmd = 12/houseInfos[1];//算出col的数
        List<Map<String, Object>> houseInfoList = new ArrayList<>();
        for (int i = 1; i <= unitNum; i++) {
            Map<String, Object> map = new HashMap<>();
            CqsHomeInfoEntity cqsHomeInfoEntity = new CqsHomeInfoEntity();
            cqsHomeInfoEntity.setCompId(compId);
            cqsHomeInfoEntity.setHouseCode(buildingCode);
            cqsHomeInfoEntity.setUnitCode(String.valueOf(i));
            List<HomeInfoRes> homeList = CopyUtil.copyList(cqsHomeInfoService.findList(cqsHomeInfoEntity), HomeInfoRes.class);
            //倒序
            homeList = homeList.stream().sorted(Comparator.comparing(HomeInfoRes::getHomeCode).reversed()).collect(Collectors.toList());
            for (HomeInfoRes item : homeList) {
                CqsHomeinfoPeopleEntity cqsHomeinfoPeopleEntity = new CqsHomeinfoPeopleEntity();
                cqsHomeinfoPeopleEntity.setPid(item.getId());
                List<CqsHomeinfoPeopleEntity> list = cqsHomeinfoPeopleService.findList(cqsHomeinfoPeopleEntity);
                String collect = list.stream().map(CqsHomeinfoPeopleEntity::getFlags).collect(Collectors.joining());
                collect = collect.replace("null","").replace(",", "").replaceAll("(?s)(.)(?=.*\\1)", "");
                item.setFlags(collect);
            }

            map.put("unit",i);
            map.put("homeList",homeList);
            houseInfoList.add(map);
        }
        //获取家庭分类
        List<SysDictDataEntity> family_flags = sysDictDataService.list(new QueryWrapper<SysDictDataEntity>()
                .eq("dict_type","cgs_family_flag"));
        mmap.put("compName",sysCompEntity.getFullName());//小区名称
        mmap.put("buildingCode",buildingCode);//楼栋号
        mmap.put("homeCode",sysDictDataEntity.getDictSort());//房间编号
        mmap.put("houseInfoList",houseInfoList);//单元和房间号
        mmap.put("colsm","col-sm-"+colmd);//每层房间数
        mmap.put("familyFlags",family_flags);//家庭分类
        return prefix +"/buildingShow";
    }

    /**
     * 获取家庭信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/familyList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> familyList(String code) {
        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","");
        String pid = getPid(code);
        if (!"".equals(pid)){
            List<CqsHomeinfoPeopleEntity> cqsHomeinfoPeopleEntities = cqsHomeinfoPeopleService.list(new QueryWrapper<CqsHomeinfoPeopleEntity>()
                    .eq("pid",pid));
            if (cqsHomeinfoPeopleEntities != null) {
                map.put("count", cqsHomeinfoPeopleEntities.size());
                map.put("data", cqsHomeinfoPeopleEntities);
            }else{
                map.put("code","-1");
                map.put("msg","没有数据");
            }
        }else{
            map.put("code","-1");
            map.put("msg","没有数据");
        }
        return map;
    }

    /**
     * 获取租户信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/tenantList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> tenantList(String code) {
        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","");
        String pid = getPid(code);
        if (!"".equals(pid)){
            List<CqsHomeinfoTenantEntity> cqsHomeinfoTenantEntities = cqsHomeinfoTenantService.list(new QueryWrapper<CqsHomeinfoTenantEntity>()
                    .eq("pid",pid));
            if (cqsHomeinfoTenantEntities != null) {
                map.put("count", cqsHomeinfoTenantEntities.size());
                map.put("data", cqsHomeinfoTenantEntities);
            }else{
                map.put("code","-1");
                map.put("msg","没有数据");
            }
        }else{
            map.put("code","-1");
            map.put("msg","没有数据");
        }
        return map;
    }

    /**
     * 获取家庭走访记录
     * @param code
     * @return
     */
    @RequestMapping(value = "/visitRecordList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> visitRecordList(String code) {
        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","");
        String pid = getPid(code);
        if (!"".equals(pid)){
            List<CqsVisitRecordEntity> cqsVisitRecordEntities = cqsVisitRecordService.list(new QueryWrapper<CqsVisitRecordEntity>()
                    .eq("pid",pid));
            if (cqsVisitRecordEntities != null) {
                map.put("count", cqsVisitRecordEntities.size());
                map.put("data", cqsVisitRecordEntities);
            }else{
                map.put("code","-1");
                map.put("msg","没有数据");
            }
        }else{
            map.put("code","-1");
            map.put("msg","没有数据");
        }
        return map;
    }

    /**
     * 获取住房表ID
     * @param code 小区编号+楼栋号+房间号
     * @return
     */
    private String getPid(String code){
        String pid = "";
        String compCode = StrUtil.sub(code,0,4);//获取小区编号
        String houseCode = Convert.toStr(Convert.toInt(StrUtil.sub(code,4,7)));//获取单元号
        String homeCode = StrUtil.sub(code,7,10);//获取房间编号
        //根据序号获取对应单位信息，这里需要配置时要求唯一。
        SysCompEntity sysCompEntity = sysCompService.getOne(new QueryWrapper<SysCompEntity>()
                .eq("order_num",compCode));
        Long compId = sysCompEntity.getId();//获取单位（小区）的id
        CqsHomeInfoEntity cqsHomeInfoEntity = cqsHomeInfoService.getOne(new QueryWrapper<CqsHomeInfoEntity>()
                .eq("comp_id",compId).eq("house_code",houseCode).eq("home_code",homeCode));
        if (cqsHomeInfoEntity !=null) {
            pid = Convert.toStr(cqsHomeInfoEntity.getId());
        }
        return pid;
    }

    /**
     * 支部信息
     * @return
     */
    @RequestMapping("partyShow/{partyCode}")
    public String partyShow(@PathVariable Long partyCode, ModelMap mmap) {
        //获取支部信息
        CqsPartybranchInfoEntity cqsPartybranchInfoEntity = cqsPartybranchInfoService.findCqsPartybranchInfoById(partyCode);
        String marker = cqsPartybranchInfoEntity.getRemark();
        String gridCode = "";
        String gridName = "";
        JSONObject td_LngLat = JSONUtil.createObj();
        if (!"".equals(marker)) {
            //获取网格所有楼的经纬度
            JSONObject jo_getRemark = JSONUtil.parseObj(marker);
            gridCode = jo_getRemark.getStr("gridCode");
            gridName = jo_getRemark.getStr("gridName");
            //获取map覆盖图的对角线点值
            td_LngLat = jo_getRemark.getJSONObject("mapPicPoint");
        }
        List<CqsPartybranchChildEntity> cqsPartybranchChildEntities = cqsPartybranchChildService.list(
                new QueryWrapper<CqsPartybranchChildEntity>().eq("pid",cqsPartybranchInfoEntity.getId()));
        //获取活动分类
        List<SysDictDataEntity> sysDictDataEntitys = sysDictDataService.list(new QueryWrapper<SysDictDataEntity>()
                .eq("dict_type","cgs_act_type"));
        mmap.put("compName","半山花园社区");
        mmap.put("branchInfo",cqsPartybranchInfoEntity);
        mmap.put("branchChildInfo",cqsPartybranchChildEntities);
        mmap.put("actTypes",sysDictDataEntitys);
        mmap.put("gridMapPic","gridShow"+gridCode+".png");
        mmap.put("gridCode",gridCode);
        mmap.put("gridName",gridName);
        mmap.put("td_LngLat",td_LngLat);
        return  prefix +"/partyShow";
    }

    /**
     * 重点人员
     * @return
     */
    @RequestMapping("focusGroups")
    public String focusGroups(ModelMap mmap) {
        List<SysDictDataEntity> sysDictDataEntitys = sysDictDataService.list(new QueryWrapper<SysDictDataEntity>()
                .eq("dict_type","cgs_family_flag"));
        mmap.put("cgs_family_flag",sysDictDataEntitys);
        return prefix +"/focusGroups";
    }

    /**
     * 党员阵地图片显示
     * @param id
     * @return
     */
    @RequestMapping(value = "/partyHousePicList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> partyHousePicList(String id) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<SysFilesEntity> selectSysFilesList = sysFileService.selectSysFilesList(id,"cqs_partybranch_info_img");
        for (SysFilesEntity sysFilesEntity : selectSysFilesList) {
            Map<String, Object> picmap = new HashMap<>();
            picmap.put("alt",sysFilesEntity.getFileName());
            picmap.put("pid",sysFilesEntity.getId());
            picmap.put("src","/cgs/sys/comm/fileUploadView?fileId="+sysFilesEntity.getId());
            picmap.put("thumb","/cgs/sys/comm/download?fileId="+sysFilesEntity.getId());
            mapList.add(picmap);
        }
        map.put("title","");
        map.put("id",id);
        map.put("start","");
        map.put("data",mapList);
        return map;
    }
}
