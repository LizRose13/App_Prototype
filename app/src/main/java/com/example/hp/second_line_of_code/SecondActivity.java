package com.example.hp.second_line_of_code;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import javax.security.auth.login.LoginException;

/**
 * @author liz
 * @version V1.0
 * @date 2018/3/26
 */

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button bt2=findViewById(R.id.finishBt);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid()); //杀死当前进程
            }
        });

    }
}
