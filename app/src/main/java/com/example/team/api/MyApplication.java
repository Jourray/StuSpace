package com.example.team.api;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.team.plugutils.DeviceUuidFactory;

import java.util.UUID;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2615:00
 * desc   :
 * package: StuSpace:
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;
    private UUID deviceUuid;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        deviceUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
        Log.e("deviceUuid", "deviceUuid: "+"\n"+deviceUuid.toString() );
    }

    public static Context getAppContext() {
        return myApplication.getApplicationContext();
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
