package com.baiwei.tianlong.jindong.network;

import android.content.Context;

import com.baiwei.tianlong.jindong.gen.DaoMaster;
import com.baiwei.tianlong.jindong.gen.DaoSession;

public class DaoManager {
    private static DaoManager daoManager;
    private final DaoSession daoSession;

    private DaoManager(Context context) {
        this.daoSession = DaoMaster.newDevSession(context,"search_keywords.db");
    }

    public static DaoManager getDaoManager(Context context) {
        //双重检测
        if (daoManager == null){
            synchronized (DaoManager.class){
                if (daoManager == null){
                    return daoManager = new DaoManager(context);
                }
            }
        }
        return daoManager;
    }

    public DaoSession daoSession() {
        return daoSession;
    }
}
