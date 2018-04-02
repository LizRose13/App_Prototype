package com.example.hp.second_line_of_code.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.second_line_of_code.Fruit;
import com.example.hp.second_line_of_code.R;
import com.example.hp.second_line_of_code.FruitActivity;

import java.util.List;

/**
 * @author liz
 * @version V1.0
 * @date 2018/3/30
 */

public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit>  mFruitList;

     static class ViewHolder extends  RecyclerView.ViewHolder{
         CardView cardView;
         ImageView iv_recycler_fruit;
         TextView tv_recycler_fruit;

         public ViewHolder(View itemView) {
             super(itemView);
             cardView= (CardView) itemView;
             iv_recycler_fruit=itemView.findViewById(R.id.iv_card_image);
             tv_recycler_fruit=itemView.findViewById(R.id.tv_card_name);
         }
     }

    public FruitRecyclerAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

         if(mContext==null){
             mContext=parent.getContext();
         }
         View view= LayoutInflater.from(mContext).inflate(R.layout.item_card_fruit,parent,false);
         final ViewHolder holder=new ViewHolder(view);
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Fruit fruit=mFruitList.get(holder.getAdapterPosition());
                 Intent intent=new Intent(mContext, FruitActivity.class);
                 intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                 intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                 mContext.startActivity(intent);
             }
         });

        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Fruit fruit=mFruitList.get(position);
         holder.tv_recycler_fruit.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.iv_recycler_fruit);

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
