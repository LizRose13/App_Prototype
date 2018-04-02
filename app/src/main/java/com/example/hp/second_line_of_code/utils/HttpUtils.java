package com.example.hp.second_line_of_code.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络请求的封装
 * @author liz
 * @version V1.0
 * @date 2018/3/29
 */

public class HttpUtils {

    public static  void  sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request =new Request.Builder()
                .url(address)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public static  void sendHttpRequest(final String address,final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;

                try {
                    URL url=new URL(address);
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

                    if(listener!=null){
                        listener.onFinish(response.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onError(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError(e);
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
}
