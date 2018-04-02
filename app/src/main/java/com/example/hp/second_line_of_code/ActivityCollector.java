package com.example.hp.second_line_of_code;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个管理所有activity的工具类，便于实现所有活动的创建销毁
 * @author liz
 * @version V1.0
 * @date 2018/3/26
 */

public class ActivityCollector {

    public static List<Activity> activities=new ArrayList<Activity>();

    public static void addActivity(Activity activity){

        activities.add(activity);
    }

    public  static  void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public  static  void finishAll(){

        for(Activity activity:activities){
            if(!activity.isFinishing())
                activity.finish();
        }
    }


   
}




