package com.example.hp.second_line_of_code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hp.second_line_of_code.utils.HttpCallbackListener;
import com.example.hp.second_line_of_code.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 一、使用HttpURLConnection进行网络连接：
 * 1.新建HttpURLConnection对象和URL来获取url连接,设置连接时延和获取方式(在新线程)
 * 2. 利用BufferedReader读取连接输出流，放入stringbuilder中
 * 3.处理字符串方式为response.toString()
 * 4. 打开网络权限
 * 5.模拟器打开网络
 * 6. 更新UI需要借助runOnUiThread回到主线程
 *
 * 二、使用okhttp进行网络请求
 * 1. 添加依赖
 * 2. 申请okhttpClient实例
 * 3. 发起请求创建request对象，传递url参数然后build
 * 4. 用response调用client的newcall进行接受
 * 5. 处理字符串为response.body().String()
 * @author liz
 * @version V1.0
 * @date 2018/3/28
 */

public class HttpActivity extends BaseActivity {

    Button  btn_http_get;
    Button  btn_http_okhttp;
    TextView tv_http_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        btn_http_get=findViewById(R.id.btn_http_get);
        btn_http_okhttp=findViewById(R.id.btn_http_okhttp);
        tv_http_show=findViewById(R.id.tv_http_show);

        btn_http_okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendRequestWithOkhttp();
                HttpUtils.sendOkHttpRequest("https://www.sogou.com/",new okhttp3.Callback(){

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData=response.body().string();
                        showResponse(responseData);
                    }
                });
            }
        });


        btn_http_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    sendRequestWithHttpUrl();
                HttpUtils.sendHttpRequest("https://www.baidu.com", new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        showResponse(response);
                    }
                    @Override
                    public void onError(Exception e) {
                    }
                });
            }
        });


    }

    /**
     * okhttp
     */
    private void sendRequestWithOkhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                //连接主机服务器必须使用真实ip地址
                Request request=new Request.Builder().url("http://192.168.56.1:8080/project/get_data.json").build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();

                    //parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);
                    //showResponse(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 使用GSON:
     * 1. 添加依赖，不能添加直接下载jar包放在Libs下就好了
     * 2. 一个对象 gson.fromJson(jsondata,app.class)
     * 3. 数组对象 gson.fromJson(jsondata,new TypeToken<List<app>>(){}.getType())
     * 4. 创建与json数据对应的java类
     * @param jsonData
     */
    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());

        for (App app : appList) {
            Log.d("httpactivity", "id---:"+app.getId());
            Log.d("httpactivity", "name---:"+app.getName());
            Log.d("httpactivity", "version---:"+app.getVersion());


        }
    }


    /**
     * 使用JSONObject来解析json字符串
     * 1.string->jsonarray
     * 2. jsonobject->jsonarray.get(i)
     * @param responseData
     */
    private void parseJSONWithJSONObject(String responseData) {
        try {
            JSONArray jsonArray=new JSONArray(responseData);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.d("httpactivity", "id:"+id+"\t name:"+name+"\t version:"+version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * HttpURLConnection
     */
    private void sendRequestWithHttpUrl() {
        //开启线程来进行网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;

                try {
                    URL url=new URL("https://www.baidu.com");
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);

                    InputStream in=connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 更新UI方法
     * @param response
     */
    private void showResponse(final String response) {
        //该方法将线程切换到主线程，因为不能在子线程中更新UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_http_show.setText(response);
            }
        });


    }
}
