package com.baiwei.tianlong.jindong.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
    public static Context getAppContext(){
        return context;
    }
}
