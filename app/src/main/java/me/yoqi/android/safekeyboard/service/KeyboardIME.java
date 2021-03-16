package me.yoqi.android.safekeyboard.service;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.PowerManager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import me.yoqi.android.safekeyboard.R;

/**
 * 自定义键盘服务
 *
 * @author liuyuqi.gov@msn.cn
 * @date 3/16/2021
 */
public class KeyboardIME extends InputMethodService {
    private static final String TAG = "KeyboardIME";
    private TextView mTextViewMessage;
    private PowerManager.WakeLock mWakeLock;
    Context mContext;

    private void screenLock() {
        if (mWakeLock != null && !mWakeLock.isHeld()) { //设置10分钟后锁屏
            mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
            return;
        }
        mWakeLock = ((PowerManager) getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "everettjf:remoboard");
        if (mWakeLock != null) {
            mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
        }
    }

    private void screenUnlock() {
        if (mWakeLock != null) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    /**
     * 设置相应控件的事件，如软键盘按钮单击事件
     *
     * @return 返回建立的布局文件对应的View对象
     */
    @Override
    public View onCreateInputView() {
        mContext = this;
        return getLayoutInflater().inflate(R.layout.keyboard_key_board_popu, null);
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();
        screenUnlock();
    }

    @Override
    public boolean onEvaluateFullscreenMode() {
        return super.onEvaluateFullscreenMode();
    }

    @Override
    public void updateInputViewShown() {
        super.updateInputViewShown();
        if (isInputViewShown()) {
            screenLock();
        }
    }
}