package com.example.hp.second_line_of_code.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.hp.second_line_of_code.R;

/**
 * 创建自定义控件：
 * 1.编写控件类
 * 2.在布局文件中引入
 * 3. 实现控件点击事件，利于代码重用
 * @author liz
 * @version V1.0
 * @date 2018/3/26
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button btn_title_back=findViewById(R.id.btn_title_back);



        btn_title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

    }
}
