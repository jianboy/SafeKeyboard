package me.yoqi.android.safekeyboard;

import android.app.Application;

/**
 * @author liuyuqi.gov@msn.cn
 * @date 3/17/2021
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}