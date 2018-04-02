package com.example.hp.second_line_of_code.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.second_line_of_code.Fruit;
import com.example.hp.second_line_of_code.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author liz
 * @version V1.0
 * @date 2018/3/27
 */

public class FruitAdapter  extends ArrayAdapter<Fruit> {

    private  int resourceId; //每一项的布局格式的id
    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    /**
     * 返回每一个项的样式
     * @param position ：获取该项在数组的位置
     * @param convertView ：对之前加载好的布局进行缓存，以便之后可以重新使用
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         Fruit fruit=getItem(position);
         View view;
         ViewHolder viewHolder;


         if(convertView==null){
             view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
             viewHolder=new ViewHolder();
             viewHolder.fruitIv=view.findViewById(R.id.iv_fruit_image);
             viewHolder.fruitTv=view.findViewById(R.id.tv_fruit_name);
             view.setTag(viewHolder); //将viewHolder存在于view中
         }else{
             view=convertView;
             viewHolder= (ViewHolder) view.getTag();
         }

        viewHolder.fruitIv.setImageResource(fruit.getImageId());
        viewHolder.fruitTv.setText(fruit.getName());

        return view;
    }

    public  class ViewHolder  {
        ImageView  fruitIv;
        TextView fruitTv;
    }
}
