package com.car.app;

import com.car.app.addressbook.AddressBookFragment;
import com.car.app.base.AppsBaseFragment;
import com.car.app.calendar.CalendarFragment;
import com.car.app.weather.WeatherFragment;

/**
 * Created by shuhj on 2017/3/6.
 */

public enum AppsEnum {

    ADDRESS_BOOK(0, R.drawable.address_book, "通讯录", new AddressBookFragment()),
    CALENDAR(1, R.drawable.calendar, "日历", new CalendarFragment()),
    CAMERA(2, R.drawable.camera, "照相机", new AddressBookFragment()),
    CLOCK(3, R.drawable.clock, "时钟", new AddressBookFragment()),
    GAME_CONTROL(4, R.drawable.games_control, "游戏", new AddressBookFragment()),
    MESSENGER(5, R.drawable.messenger, "短信", new AddressBookFragment()),
    RINGTONE(6, R.drawable.ringtone, "铃声", new AddressBookFragment()),
    SETTING(7, R.drawable.settings, "设置", new AddressBookFragment()),
    SPEECH_BALLOON(8, R.drawable.speech_balloon, "语音", new AddressBookFragment()),
    WEATHER(9, R.drawable.weather, "天气", new WeatherFragment()),
    WORLD(10, R.drawable.world, "浏览器", new AddressBookFragment()),
    YOUTUBE(11, R.drawable.youtube, "视频", new AddressBookFragment());

    private int appId;
    private int appIcon;
    private String appName;
    private AppsBaseFragment appsBaseFragment;

    private AppsEnum(int appId, int appIcon, String appName, AppsBaseFragment appsBaseFragment) {
        this.appId = appId;
        this.appIcon = appIcon;
        this.appName = appName;
        this.appsBaseFragment = appsBaseFragment;
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
