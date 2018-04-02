package com.example.hp.second_line_of_code;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.second_line_of_code.adapter.FruitRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 一、Recyclerview
 * 1.导入依赖在布局文件里面完整引入
 * 2. 编写item_card_fruit作为每个子项目的样式
 * 3.准备适配器参见FruitRecyclerAdapter
 * 4.用GridLayoutManager控制每行加载的个数
 * 5.引用适配器
 *
 * 二、cardview
 * 1.依赖加引用
 *
 * 三、使用glide加载图片
 * 1. 导入依赖
 * 2.Glide.with(context).load(url、本地路径、资源Id).into(iv的容器)
 *
 * 四、swipeRefreshlayout
 * 1.布局文件中直接引用
 * 2. 设置下拉监听事件，在子线程中加载数据
 * 3. 回到主线程更新ui
 *
 * @author liz
 * @version V1.0
 * @date 2018/3/30
 */

public class RecyclerActivity extends BaseActivity {

    private List<Fruit> fruits=new ArrayList<>();
    private  RecyclerView rv_cardview_fruits;
    private GridLayoutManager layoutManager;
    private FruitRecyclerAdapter adapter;
    private SwipeRefreshLayout  srl_recycler;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initListFruits();
        rv_cardview_fruits=findViewById(R.id.rv_cardview_fruits);
        srl_recycler=findViewById(R.id.srl_recycler);

        //用recycler包含cardview卡片布局网格
        layoutManager=new GridLayoutManager(this,2);
        rv_cardview_fruits.setLayoutManager(layoutManager);
        adapter=new FruitRecyclerAdapter(fruits);
        rv_cardview_fruits.setAdapter(adapter);


        srl_recycler.setColorSchemeColors(R.color.colorPrimary); //下拉颜色样式
        srl_recycler.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refershFruits();
            }
        });

    }

    private void refershFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addListFruits();
                        adapter.notifyDataSetChanged();
                        srl_recycler.setRefreshing(false); //刷新结束隐藏进度条
                    }
                });

            }
        }).start();
    }

    private void addListFruits() {
        for(int i=0;i<10;i++){
            Fruit fruit=new Fruit("corn",R.mipmap.ic_corn);
            fruits.add(fruit);
        }
    }

    private void initListFruits() {
        for(int i=0;i<10;i++){
            Fruit fruit=new Fruit("pineapple",R.mipmap.icon_pineapple);
            fruits.add(fruit);
        }
    }

}
