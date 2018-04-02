package com.example.hp.second_line_of_code;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author liz
 * @version V1.0
 * @date 2018/3/29
 */

public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    private  Button btn_thread_change;
    private  TextView tv_thread_msg;
    public static  final  int UPDATE_TEXT=1;
    public Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        btn_thread_change=findViewById(R.id.btn_thread_change);
        tv_thread_msg=findViewById(R.id.tv_thread_msg);

        btn_thread_change.setOnClickListener(this);

        /**
         * 必须传递handler回调函数，否则会报错内部类内存泄漏问题
         */
        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case UPDATE_TEXT:
                        tv_thread_msg.setText("success");
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_thread_change:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }
}
