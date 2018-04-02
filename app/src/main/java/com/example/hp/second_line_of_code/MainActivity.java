package com.example.hp.second_line_of_code;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * 一、活动最佳写法
 *
 * 二、material design 设计的toolbar
 * 1.在布局文件中引入v7的toolbar
 * 2. 设计menu样式
 * 3. 编写onCreateOptionsMenu方法实现加载menu布局样式
 * 4. 设置onOptionsItemSelected定义点击事件
 *
 * 三、DrawerLayout布局实现滑动侧边栏
 * 1. 布局文件中引用DrawerLayout，第一个元素为主屏幕，第二个元素为侧边栏
 *
 * 四、Material Design UI
 * 1. FloatActionBar --悬浮按钮
 * 2. snackbar       --仿toast的动画提示交互
 * 3. CoordinateLayout加强版frameLayout，自动实现布局元素位置合理移动
 *
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout  drawer_layout;
    private android.support.v7.app.ActionBar actionBar;
    private NavigationView  nav_view;
    private FloatingActionButton fab_main_done;
    private Button btn_main_cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);


        Button bt_main_second=findViewById(R.id.toSecondBt);
        Button bt_main_listview=findViewById(R.id.btn_main_listview);
        Button bt_main_fragment=findViewById(R.id.btn_main_fragment);
        Button  btn_main_http=findViewById(R.id.btn_main_http);
        Button btn_main_thread=findViewById(R.id.btn_main_thread);
        drawer_layout=findViewById(R.id.drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        fab_main_done=findViewById(R.id.fab_main_done);
        btn_main_cardview=findViewById(R.id.btn_main_cardview);

        setSupportActionBar(toolbar);  //将toolbar设置与actionbar外观和功能一致
        actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);   //让左侧导航按钮显示
            actionBar.setHomeAsUpIndicator(R.drawable.ic_format_list_bulleted_white_18dp); //设置左侧导航按钮的样式
        }

        btn_main_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MainActivity.this, RecyclerActivity.class);
            }
        });


        fab_main_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"data delete?",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"data restore",Toast.LENGTH_LONG).show();
                            }
                        }).show();

            }
        });


        nav_view.setCheckedItem(R.id.item_nav_def);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer_layout.closeDrawers();
                return true;
            }
        });



        btn_main_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MainActivity.this,ThreadActivity.class);
            }
        });

        btn_main_http.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MainActivity.this,HttpActivity.class);
            }
        });

        bt_main_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MainActivity.this,FragmentActivity.class);
            }
        });

        /**
         * 活动管理最佳写法：
         * 1.写一个activity的集合来控制
         * 2.活动设置为singleTask模式，每次只会有一个唯一的活动存在活动栈
         *
         * 活动之间传递值最佳写法：
         * 1.在BaseActivity/某类里写一个static start方法，通过接收传递方的参数来自身启动自身实现
         */
        bt_main_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActBydata(MainActivity.this,SecondActivity.class,"data1...","data2...");
            }
        });


        bt_main_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MainActivity.this,ListViewActivity.class);
            }
        });




        /**
         *  日志使用
         *  快捷键：logi+tab
         *  @parameter TAG is filter TAG
         */
        // Log.i("MainActivity", "onCreate: ");


    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    /**
     * 定义toolbar上的item点击效果
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_toolbar_help:
                Toast.makeText(this,"you clicked help ",Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_toolbar_share:
                Toast.makeText(this,"you clicked share",Toast.LENGTH_LONG).show();;
                break;

            case android.R.id.home: //toolbar左侧的布局默认id
                drawer_layout.openDrawer(GravityCompat.START);
            break;

        }
        return true;
    }
}
