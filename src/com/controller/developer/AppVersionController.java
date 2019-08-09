package com.controller.developer;

import com.entiey.AppInfo;
import com.entiey.AppVersion;
import com.entiey.DevUser;
import com.service.AppInfoService;
import com.service.AppVersionService;
import com.tools.FileMessage;
import com.tools.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：版本信息控制层
 *
 * @ClassName: AppVersionController
 * @Author: Lily.
 * @Date: 2019/8/6 19:22
 * @Version: V1.0
 */
@Controller
@RequestMapping("/dev/flatform/app")
public class AppVersionController {

    @Resource(name = "appInfoService")
    AppInfoService appInfoService;
    @Resource(name = "appVersionService")
    AppVersionService appVersionService;

    /**
     * 跳转到新增版本页面
     *
     * @return
     */
    @RequestMapping("/appversionadd")
    public String appversionadd(Integer id, Model model) {
        List<AppVersion> versionList = appVersionService.getAllByAppId(id);
        model.addAttribute("appVersionList", appVersionService.getAllByAppId(id));
        return "developer/appversionadd";
    }

    /**
     * 添加版本信息
     *
     * @param appVersion
     * @param a_downloadLink
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/addversionsave")
    public String addversionsave(AppVersion appVersion, @RequestParam(value = "a_downloadLink", required = false) MultipartFile[] a_downloadLink, HttpServletRequest request, HttpSession session) {
        //apk服务器的存储路径
        String apkLocPath = null;
        //下载链接
        String downloadLink = null;

        //获取绝对路径
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        //最大文件大小（MB）
        int fileSize = 500;
        //文件类型 1：图片  2：apk
        int fileType = 2;
        //调用文件上传方法
        FileMessage[] paths = FileUpload.fileUpload(a_downloadLink, request, path, fileSize, fileType);
        if (paths == null) {
            return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId();
        }
        apkLocPath = paths[0].getAbsolutePath();
        downloadLink = paths[0].getRelativePath();
        //apk文件名称
        String apkFileName = apkLocPath.substring(apkLocPath.lastIndexOf("\\") + 1);
        //初始化创始人、时间、路径、apk名称、下载链接、版本大小、最新更新时间
        appVersion.setCreatedBy(((DevUser) session.getAttribute("devUserSession")).getId());
        appVersion.setCreationDate(new Date());
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);
        appVersion.setDownloadLink(downloadLink);
        appVersion.setModifyDate(new Date());
        //将版本添加到数据库
        if (appVersionService.insertSelective(appVersion) > 0) {
            AppInfo appInfo = new AppInfo();
            appInfo.setId(appVersion.getAppId());
            appInfo.setVersionId(appVersion.getId());
            //修改应用的最新版本
            appInfoService.updateByPrimaryKeySelective(appInfo);
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId();
        }
    }

    /**
     * 跳转到修改版本信息
     *
     * @param vid
     * @param aid
     * @param model
     * @return
     */
    @RequestMapping("/appversionmodify")
    public String appversionmodify(Integer vid, Integer aid, Model model) {
        //获取历史版本信息
        model.addAttribute("appVersionList", appVersionService.getAllByAppId(aid));
        //获取最新版本信息
        model.addAttribute("appVersion", appVersionService.selectByPrimaryKey(vid));
        return "developer/appversionmodify";
    }

    /**
     * 修改版本信息
     *
     * @param appVersion
     * @param attach
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/appversionmodifysave")
    public String appversionmodifysave(AppVersion appVersion, @RequestParam(value = "attach", required = false) MultipartFile[] attach, HttpServletRequest request, HttpSession session) {
        AppVersion appVersion1 = appVersionService.selectByPrimaryKey(appVersion.getId());
        if (appVersion1.getDownloadLink() == null || appVersion1.getDownloadLink().equals("")) {
            //apk服务器的存储路径
            String apkLocPath = null;
            //下载链接
            String downloadLink = null;
            //获取绝对路径
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            //最大文件大小（MB）
            int fileSize = 500;
            //文件类型 1：图片  2：apk
            int fileType = 2;
            //调用文件上传方法
            FileMessage[] paths = FileUpload.fileUpload(attach, request, path, fileSize, fileType);
            if (paths == null) {
                return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId();
            }
            apkLocPath = paths[0].getAbsolutePath();
            downloadLink = paths[0].getRelativePath();
            //apk文件名称
            String apkFileName = apkLocPath.substring(apkLocPath.lastIndexOf("\\") + 1);
            //初始化apk文件信息
            appVersion.setApkLocPath(apkLocPath);
            appVersion.setApkFileName(apkFileName);
            appVersion.setDownloadLink(downloadLink);
        }
        appVersion.setModifyBy(((DevUser) session.getAttribute("devUserSession")).getId());
        appVersion.setModifyDate(new Date());
        if (appVersionService.updateByPrimaryKeySelective(appVersion) > 0) {
            return "redirect:/dev/flatform/app/list";
        } else {
            return "redirect:/dev/flatform/app/appversionmodify?vid=" + appVersion.getId() + "&aid=" + appVersion.getAppId();
        }
    }

}
