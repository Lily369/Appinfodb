package com.controller.developer;

import com.alibaba.fastjson.JSONArray;
import com.entiey.AppInfo;
import com.entiey.AppVersion;
import com.entiey.DataDictionary;
import com.entiey.DevUser;
import com.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：ajax控制层
 *
 * @ClassName: AjaxController
 * @Author: Lily.
 * @Date: 2019/8/5 19:36
 * @Version: V1.0
 */
@Controller
@RequestMapping("dev/flatform/app")
public class AjaxController {

    @Resource(name = "dataDictionaryService")
    DataDictionaryService dataDictionaryService;
    @Resource(name = "appCategoryService")
    AppCategoryService appCategoryService;
    @Resource(name = "appInfoService")
    AppInfoService appInfoService;
    @Resource(name = "appVersionService")
    AppVersionService appVersionService;


    /**
     * 级别分类
     *
     * @param pid
     * @return
     */
    @RequestMapping("/categorylevellist.json")
    @ResponseBody
    public String level(Integer pid) {
        return JSONArray.toJSONString(appCategoryService.getAllByParentId(pid));
    }

    /**
     * 查询所属平台
     *
     * @param tcode
     * @return
     */
    @RequestMapping("/datadictionarylist.json")
    @ResponseBody
    public String platform(String tcode) {
        List<DataDictionary> list = dataDictionaryService.getValueNameByTypeCode(tcode);
        return JSONArray.toJSONString(dataDictionaryService.getValueNameByTypeCode(tcode));
    }

    /**
     * apk名称验证
     *
     * @param APKName apk名称
     * @return
     */
    @RequestMapping("/apkexist.json")
    @ResponseBody
    public Object apkexist(String APKName) {
        Map<String, Object> map = new HashMap<>();
        if (APKName == null || APKName == "") {
            map.put("APKName", "empty");
            return map;
        }
        Integer count = appInfoService.countByAPKName(APKName);
        if (count > 0) {
            map.put("APKName", "exist");
        } else {
            map.put("APKName", "noexist");
        }
        return map;
    }

    /**
     * 修改删除文件
     *
     * @param id
     * @param flag
     * @return
     */
    @RequestMapping("/delfile.json")
    @ResponseBody
    public Object delfile(Integer id, String flag) {
        Map<String, Object> map = new HashMap<>();
        if (flag.equals("logo")) {
            AppInfo appInfo = new AppInfo();
            appInfo.setId(id);
            appInfo.setLogoPicPath("");
            appInfo.setLogoLocPath("");
            if (appInfoService.updateByPrimaryKeySelective(appInfo) > 0) {
                map.put("result", "success");
            } else {
                map.put("result", "failed");
            }
        } else if (flag.equals("apk")) {
            AppVersion appVersion = new AppVersion();
            appVersion.setId(id);
            appVersion.setDownloadLink("");
            appVersion.setApkLocPath("");
            appVersion.setApkFileName("");
            if (appVersionService.updateByPrimaryKeySelective(appVersion) > 0) {
                map.put("result", "success");
            } else {
                map.put("result", "failed");
            }
        }
        return map;
    }

    /**
     * 删除app信息
     *
     * @param id
     * @return
     */
    @RequestMapping("delapp.json")
    @ResponseBody
    public Object delapp(Integer id) {
        Map<String, Object> map = new HashMap<>();
        //查看app是否存在
        if (appInfoService.selectByPrimaryKey(id) == null) {
            map.put("delResult", "notexist");
            return map;
        }
        //查看app版本是否存在，如存在则删除
        List<AppVersion> appVersion = appVersionService.getAllByAppId(id);
        if (appVersion != null) {
            //删除版本
            appVersionService.deleteByAppId(id);
        }
        //删除app
        if (appInfoService.deleteByPrimaryKey(id) > 0) {
            map.put("delResult", "true");
        } else {
            map.put("delResult", "false");
        }
        return map;
    }

    /**
     * 上架或下架
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/{id}/sale.json")
    @ResponseBody
    public Object sale(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", "0");
        //根据id获取app信息
        AppInfo appInfo = appInfoService.selectByPrimaryKey(id);
        appInfo.setModifyBy(((DevUser) session.getAttribute("devUserSession")).getId());
        appInfo.setModifyDate(new Date());
        //如果app不为上架则改为上架，反之
        if (appInfo.getStatus() == 5) {
            appInfo.setStatus(4);
        } else if (appInfo.getStatus() == 4) {
            appInfo.setStatus(5);
        }
        if (appInfoService.updateByPrimaryKeySelective(appInfo) > 0) {
            map.put("resultMsg", "success");
        } else {
            map.put("resultMsg", "failed");
        }
        return map;
    }

}
