package me.yoqi.android.safekeyboard.view;

import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;

import me.yoqi.android.safekeyboard.MainActivity;
import me.yoqi.android.safekeyboard.R;
import me.yoqi.android.safekeyboard.base.BaseActivity;
import me.yoqi.android.safekeyboard.model.Config;
import me.yoqi.android.safekeyboard.utils.IMEUtils;

/**
 * @author liuyuqi.gov@msn.cn
 * @date 3/17/2021
 */
public class SplashActivity extends BaseActivity {
    TextView tvInfo;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        tvInfo = findViewById(R.id.tvInfo);
        ArrayList<String> res = new IMEUtils().getIMEList(mContext);
        IMEUtils imeUtils = new IMEUtils();
        boolean isconfigIME = imeUtils.isConfigIME(mContext, Config.packageName);
        String defaultInputMethodPkgName = imeUtils.getIME(mContext);

        if (isconfigIME && defaultInputMethodPkgName.equals(Config.packageName)) {
            //已经设置了输入法
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, Guide1Activity.class));
        }
    }
}