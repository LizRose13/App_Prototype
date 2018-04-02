package com.example.hp.second_line_of_code;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 所有Activity的父类
 *
 * @author liz
 * @version V1.0
 * @date 2018/3/26
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 隐藏本身标题栏
         */
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 活动间传值最佳写法，在接收值得活动中写一个启动类
     * @param context 发送的activity
     * @param data1 数据
     * @param data2
     */
    public   void startActBydata (Context context,Class startClass, String data1, String data2){
        Intent intent=new Intent(context,startClass);

        intent.putExtra("param1","data1");
        intent.putExtra("param2","data2");
        context.startActivity(intent);

    }


    public  void startAct(Context context,Class startClass){
        Intent intent=new Intent(context,startClass);
        startActivity(intent);
    }


}
