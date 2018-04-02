package com.example.hp.second_line_of_code;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.second_line_of_code.BaseActivity;
import com.example.hp.second_line_of_code.R;

/**
 *
 * CollapsingToolbarLayout可以实现一部分布局在滑动过程中隐藏，另一部分一直显示
 * NestedScrollView
 *
 * @author liz
 * @version V1.0
 * @date 2018/4/1
 */

public class FruitActivity extends BaseActivity {

    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    private Toolbar tb_fruit;
    private CollapsingToolbarLayout ctl_fruit;
    private ImageView iv_fruit;
    private TextView tv_fruit;
    private ActionBar ab_fruit;
    private String fruitContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        tb_fruit=findViewById(R.id.tb_fruit);
        ctl_fruit=findViewById(R.id.ctl_fruit);
        iv_fruit=findViewById(R.id.iv_fruit);
        tv_fruit=findViewById(R.id.tv_fruit);


        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitNameId=intent.getIntExtra(FRUIT_IMAGE_ID,0);

        setSupportActionBar(tb_fruit);
        ab_fruit=getSupportActionBar();
        if(ab_fruit!=null){
            ab_fruit.setDisplayHomeAsUpEnabled(true);
        }
        ctl_fruit.setTitle(fruitName);
        Glide.with(this).load(fruitNameId).into(iv_fruit);
        fruitContext=generateFruitContext(fruitName);
        tv_fruit.setText(fruitContext);

    }

    private String generateFruitContext(String fruitName) {

        StringBuilder fruitContext=new StringBuilder();
        for(int i=0;i<500;i++){
            fruitContext.append(fruitName);
        }

        return fruitContext.toString();
    }


    /**
     * 导航栏功能键的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
