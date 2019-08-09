package com.entiey;

import java.util.Date;

import lombok.Data;

/**
 * 功能描述：广告实体类
 *
 * @ClassName: ${NAME}
 * @Author: Lily.
 * @Date: 2019/8/2 15:13
 * @Version: V1.0
 */
@Data
public class AdPromotion {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * appId（来源于：app_info表的主键id）
     */
    private Integer appId;

    /**
     * 广告图片存储路径
     */
    private String adPicPath;

    /**
     * 广告点击量
     */
    private Integer adPV;

    /**
     * 轮播位（1-n）
     */
    private Integer carouselPosition;

    /**
     * 起效时间
     */
    private Date startTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 创建者（来源于backend_user用户表的用户id）
     */
    private Integer createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 更新者（来源于backend_user用户表的用户id）
     */
    private Integer modifyBy;

    /**
     * 最新更新时间
     */
    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAdPicPath() {
        return adPicPath;
    }

    public void setAdPicPath(String adPicPath) {
        this.adPicPath = adPicPath;
    }

    public Integer getAdPV() {
        return adPV;
    }

    public void setAdPV(Integer adPV) {
        this.adPV = adPV;
    }

    public Integer getCarouselPosition() {
        return carouselPosition;
    }

    public void setCarouselPosition(Integer carouselPosition) {
        this.carouselPosition = carouselPosition;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}