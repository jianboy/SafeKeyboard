package me.yoqi.android.safekeyboard.view;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import me.yoqi.android.safekeyboard.MainActivity;
import me.yoqi.android.safekeyboard.R;
import me.yoqi.android.safekeyboard.base.BaseActivity;
import me.yoqi.android.safekeyboard.model.Config;
import me.yoqi.android.safekeyboard.utils.IMEUtils;

/**
 * @author liuyuqi.gov@msn.cn
 * @date 3/17/2021
 */
public class Guide1Activity extends BaseActivity {
    Button btnFinish, btnStep1Enable, btnStep2Enable;
    boolean isConfigIME, isEnableIME; // 当前输入法是否启用

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_guide1;
    }

    @Override
    protected void init() {
        btnFinish = findViewById(R.id.btnFinish);
        btnStep1Enable = findViewById(R.id.btnStep1Enable);
        btnStep2Enable = findViewById(R.id.btnStep2Enable);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfigIME && isEnableIME) {
                    startActivity(new Intent(Guide1Activity.this, MainActivity.class));
                } else {
                    new AlertDialog.Builder(mContext)
                            .setMessage("请勾选安全输入法！")
                            .setCancelable(false)
                            .setPositiveButton("确定", null)
                            .show();
                }
            }
        });
        btnStep1Enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enableIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                enableIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(enableIntent);
            }
        });
        btnStep2Enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showInputMethodPicker();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IMEUtils imeUtils = new IMEUtils();
        isConfigIME = imeUtils.isConfigIME(mContext, Config.packageName);
        isEnableIME = imeUtils.isEnableIME(mContext, Config.packageName);
        btnStep1Enable.setEnabled(isConfigIME);
        btnStep2Enable.setEnabled(isEnableIME);
    }
}