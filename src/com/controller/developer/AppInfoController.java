package com.controller.developer;

import com.entiey.AppInfo;
import com.entiey.AppVersion;
import com.entiey.DevUser;
import com.service.*;
import com.tools.FileMessage;
import com.tools.FileUpload;
import com.tools.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * 功能描述：app信息控制层
 *
 * @ClassName: AppInfoController
 * @Author: Lily.
 * @Date: 2019/8/6 14:33
 * @Version: V1.0
 */
@Controller
@RequestMapping("/dev/flatform/app")
public class AppInfoController {

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
     * @return
     */
    @RequestMapping("/list")
    public String appList(Model model, String querySoftwareName, Integer queryStatus, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer pageIndex) {
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
        return "developer/appinfolist";
    }

    /**
     * 跳转添加App基础信息页面
     *
     * @return
     */
    @RequestMapping("/appinfoadd")
    public String getAddPage(Model model) {
        List<AppVersion> versionList = appVersionService.getAll();
        // model.addAttribute("appVersionList",versionList);
        return "developer/appinfoadd";
    }

    /**
     * 舔加App基础信息
     *
     * @param appInfo
     * @param a_logoPicPath
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/appinfoaddsave", method = RequestMethod.POST)
    public String appinfoaddsave(AppInfo appInfo, @RequestParam(value = "a_logoPicPath", required = false) MultipartFile[] a_logoPicPath, HttpServletRequest request, HttpSession session) {
        String logoPicPath = null;      //LOGO图片url路径
        String logoLocPath = null;      //LOGO图片的服务器存储路径
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/statics/uploadfiles/";
        //相对路径
        String basePath = "/statics/uploadfiles/";
        //绝对路径
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        //获取上传后的绝对路径数组
        FileMessage[] paths = FileUpload.fileUpload(a_logoPicPath, request, path, 5, 1);
        if (paths == null) {
            return "redirect:/dev/flatform/app/appinfoadd";
        }
        logoLocPath = paths[0].getAbsolutePath();
         /*  int jj = logoLocPath.lastIndexOf("\\");
        System.out.println(jj);*/
        logoPicPath = basePath + logoLocPath.substring(logoLocPath.lastIndexOf("\\") + 1);
        System.out.println(logoPicPath);
        //初始化创建者和创建时间
        appInfo.setCreatedBy(((DevUser) session.getAttribute("devUserSession")).getId());
        appInfo.setCreationDate(new Date());
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setLogoPicPath(logoPicPath);
        //新增
        if (appInfoService.insert(appInfo) > 0) {
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:/dev/flatform/app/appinfoadd";
        }
    }

    /**
     * 跳转到app基础信息修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/appinfomodify")
    public String appinfomodify(Integer id, Model model) {
        model.addAttribute("appInfo", appInfoService.selectByPrimaryKey(id));
        return "/developer/appinfomodify";
    }

    /**
     * 修改app基础信息
     *
     * @param appInfo
     * @param attach
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/appinfomodifysave")
    public String appinfomodifysave(AppInfo appInfo, @RequestParam(value = "attach", required = false) MultipartFile[] attach, HttpServletRequest request, HttpSession session) {
        AppInfo appInfo1 = appInfoService.selectByPrimaryKey(appInfo.getId());
        if (appInfo1.getLogoPicPath() == null || appInfo1.getLogoPicPath().equals("")) {
            String logoPicPath = null;      //LOGO图片url路径
            String logoLocPath = null;      //LOGO图片的服务器存储路径
            //绝对路径
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            //获取上传后的绝对路径数组
            FileMessage[] paths = FileUpload.fileUpload(attach, request, path, 5, 1);
            if (paths == null) {
                return "redirect:/dev/flatform/app/appinfomodify";
            }
            logoLocPath = paths[0].getAbsolutePath();
            logoPicPath = paths[0].getRelativePath();
            appInfo.setLogoLocPath(logoLocPath);
            appInfo.setLogoPicPath(logoPicPath);
        }
        //初始化修改者和修改时间
        appInfo.setModifyBy(((DevUser) session.getAttribute("devUserSession")).getId());
        appInfo.setModifyDate(new Date());

        //修改数据库数据
        if (appInfoService.updateByPrimaryKeySelective(appInfo) > 0) {
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:/dev/flatform/app/appinfomodify";
        }
    }

    /**
     * 查看app详细信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/appview/{id}")
    public String appview(@PathVariable Integer id, Model model) {
        //查询app基础信息
        model.addAttribute("appInfo", appInfoService.selectByPrimaryKey(id));
        //查询app历史版本列表
        model.addAttribute("appVersionList", appVersionService.getAllByAppId(id));
        return "developer/appinfoview";
    }


}
