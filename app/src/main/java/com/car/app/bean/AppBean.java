package com.car.app.bean;


import com.car.app.base.AppsBaseFragment;

/**
 * Created by shuhj on 2017/3/7.
 */

public class AppBean {

    private int appId;
    private int appIcon;
    private String appName;
    private AppsBaseFragment appsBaseFragment;

    public AppBean() {
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public AppsBaseFragment getAppsBaseFragment() {
        return appsBaseFragment;
    }

    public void setAppsBaseFragment(AppsBaseFragment appsBaseFragment) {
        this.appsBaseFragment = appsBaseFragment;
    }

}
