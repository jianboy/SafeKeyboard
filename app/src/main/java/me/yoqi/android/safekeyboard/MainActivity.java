package me.yoqi.android.safekeyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import me.yoqi.android.safekeyboard.keyboard.KeyBoardDialogUtils;

public class MainActivity extends AppCompatActivity {
    private KeyBoardDialogUtils keyBoardDialogUtils;
    private EditText et;
    private Button btnChangeIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        btnChangeIME = findViewById(R.id.btnChangeIME);
        btnChangeIME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeIME();
            }
        });

        et = (EditText) findViewById(R.id.et);
        keyBoardDialogUtils = new KeyBoardDialogUtils(this);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoardDialogUtils.show(et);
            }
        });
    }

    public void changeIME() {
        //1、判断是否系统启用了安全输入法没有启动者跳到设置界面
        if (true) {
            Intent enableIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
            enableIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(enableIntent);
        } else if (false) {
            // 2、如果设置了安全输入法，但是没有启动，则跳转到切换输入法界面：
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showInputMethodPicker();
        }
    }

}