package com.controller.backend;

import com.alibaba.fastjson.JSONArray;
import com.entiey.AppInfo;
import com.service.AppCategoryService;
import com.service.AppInfoService;
import com.service.AppVersionService;
import com.service.DataDictionaryService;
import com.tools.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 *
 * @ClassName: AppReviewController
 * @Author: Lily.
 * @Date: 2019/8/8 16:22
 * @Version: V1.0
 */
@Controller
@RequestMapping("/manager1/backend/app")
public class AppReviewController {

    @Resource(name = "dataDictionaryService")
    DataDictionaryService dataDictionaryService;
    @Resource(name = "appCategoryService")
    AppCategoryService appCategoryService;
    @Resource(name = "appInfoService")
    AppInfoService appInfoService;
    @Resource(name = "appVersionService")
    AppVersionService appVersionService;

    /**
     * 显示app列表
     *
     * @param model
     * @param querySoftwareName
     * @param queryStatus
     * @param queryFlatformId
     * @param queryCategoryLevel1
     * @param queryCategoryLevel2
     * @param queryCategoryLevel3
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, String querySoftwareName, Integer queryStatus, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer pageIndex) {
        //app状态
        model.addAttribute("statusList", dataDictionaryService.getValueNameByTypeCode("APP_STATUS"));
        //所属平台
        model.addAttribute("flatFormList", dataDictionaryService.getValueNameByTypeCode("APP_FLATFORM"));
        //一级分类
        model.addAttribute("categoryLevel1List", appCategoryService.getAllByParentId(null));
        //保存查询信息
        Map<String, Object> map = new HashMap<>();
        map.put("softwareName", querySoftwareName);
        map.put("status", queryStatus);
        map.put("flatformId", queryFlatformId);
        map.put("categoryLevel1", queryCategoryLevel1);
        map.put("categoryLevel2", queryCategoryLevel2);
        map.put("categoryLevel3", queryCategoryLevel3);
        map.put("pageIndex", pageIndex);
        Page page = appInfoService.getAllByConditionAndPage(map);
        model.addAttribute("pages", page);
        model.addAttribute("appInfoList", page.getList());
        //保存分类查询信息
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("queryFlatformId", queryFlatformId);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        return "backend/applist";
    }

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
     * 跳转到审核页面
     *
     * @param aid
     * @param vid
     * @param model
     * @return
     */
    @RequestMapping("/check")
    public String check(Integer aid, Integer vid, Model model) {
        //查询app基础信息
        model.addAttribute("appInfo", appInfoService.selectByPrimaryKey(aid));
        //查询app最新版本列表
        model.addAttribute("appVersion", appVersionService.selectByPrimaryKey(vid));
        return "backend/appcheck";
    }

    @RequestMapping("/checksave")
    public String checksave(AppInfo appInfo) {
        if (appInfoService.updateByPrimaryKeySelective(appInfo) > 0) {
            return "redirect:/manager1/backend/app/list";
        }
        return "redirect:/manager1/backend/app/check";
    }

}
