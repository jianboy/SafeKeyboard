package me.yoqi.android.safekeyboard.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import me.yoqi.android.safekeyboard.utils.AppManager;

/**
 * 基类
 * @author liuyuqi.gov@msn.cn
 * @date 3/17/2021
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        mContext = this;
        AppManager.getInstance().addActivity(this);
        init();
    }

    public boolean hasPermission(String permission) {
        boolean has = true;
        try {
            has = PermissionChecker.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return has;
    }
    protected abstract int getLayoutResID();
    protected abstract void init();

}