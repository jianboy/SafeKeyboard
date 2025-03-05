package me.yoqi.android.safekeyboard.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuqi.gov@msn.cn
 * @date 3/17/2021
 */
public class IMEUtils {
    public String getIME(Context context) {
        String mDefaultInputMethodPkg = null;
        String mDefaultInputMethodCls = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);
        //输入法类名信息
        if (!TextUtils.isEmpty(mDefaultInputMethodCls)) {
            //输入法包名
            mDefaultInputMethodPkg = mDefaultInputMethodCls.split("/")[0];
        }
        return mDefaultInputMethodCls;
    }

    public ArrayList<String> getIMEList(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methodList = imm.getInputMethodList();
        ArrayList<String> res = new ArrayList<String>();
        for (InputMethodInfo mi : methodList) {
            res.add(mi.getPackageName());
        }
        return res;
    }


    /**
     * 判断输入法是否设置，有问题
     *
     * @param imePkg 输入法包名
     * @return 输入法是否设置
     */
    public boolean isConfigIME(Context context, String imePkg) {
        return getIMEList(context).contains(imePkg);
    }

    /**
     * 判断输入法是否启用
     *
     * @param context
     * @param imePkg
     * @return
     */
    public boolean isEnableIME(Context context, String imePkg) {
        return getDefaultInputMethodPkgName(context).equals(imePkg);
    }

    /**
     * 获取默认输入法
     *
     * @param context 上下文对象
     * @return 输入法字符串
     */
    public String getDefaultInputMethodPkgName(Context context) {
        String mDefaultInputMethodPkg = null;
        String mDefaultInputMethodCls = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);
        //输入法类名信息
        if (!TextUtils.isEmpty(mDefaultInputMethodCls)) {
            //输入法包名
            mDefaultInputMethodPkg = mDefaultInputMethodCls.split("/")[0];
        }
        return mDefaultInputMethodPkg;
    }
}