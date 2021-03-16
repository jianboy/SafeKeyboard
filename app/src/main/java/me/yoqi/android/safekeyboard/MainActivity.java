package me.yoqi.android.safekeyboard;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import me.yoqi.android.safekeyboard.keyboard.KeyBoardDialogUtils;

public class MainActivity extends AppCompatActivity {
    private KeyBoardDialogUtils keyBoardDialogUtils;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        keyBoardDialogUtils = new KeyBoardDialogUtils(this);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoardDialogUtils.show(et);
            }
        });
    }
}