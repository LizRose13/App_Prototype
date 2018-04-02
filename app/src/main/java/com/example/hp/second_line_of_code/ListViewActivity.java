package com.example.hp.second_line_of_code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.second_line_of_code.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Listview
 * 1. 写item的布局文件
 * 2. 重写适配器
 * 3. 写用于显示的类并创建该类的数组
 * @author liz
 * @version V1.0
 * @date 2018/3/27
 */

public class ListViewActivity extends BaseActivity {

//    private  String[] data={"Apple","Banana","Orange","Watermelon",
//    "Pear","Grape","Pineapple","Strawberry","Mango"};

    private List<Fruit> fruitLists=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView lv_listview_show=findViewById(R.id.lv_listview_show);

        initFruits();
        FruitAdapter fruitAdapter=new FruitAdapter(ListViewActivity.this,R.layout.item_listview_fruit,fruitLists);
        lv_listview_show.setAdapter(fruitAdapter);
        lv_listview_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit=fruitLists.get(position);
                Toast.makeText(ListViewActivity.this,"click:"+position,Toast.LENGTH_LONG).show();
            }
        });


        /**
         * 1.常规数组形式适配器显示
         */
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ListViewActivity.this,
//                android.R.layout.simple_list_item_1,data);
//        lv_listview_show.setAdapter(adapter);




    }

    private void initFruits() {
        for(int i=0;i<10;i++){
            Fruit fruit=new Fruit("pineapple",R.mipmap.icon_pineapple);
            fruitLists.add(fruit);
        }

    }
}
