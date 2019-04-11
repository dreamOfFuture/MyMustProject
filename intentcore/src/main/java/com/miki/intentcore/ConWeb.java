package com.miki.intentcore;

import android.net.Uri;

import com.miki.intentcore.utils.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConWeb {
    public boolean ConWebTag = false;
    private  conWebCallBack conWebCallBack=null;
    interface  conWebCallBack{
        void conSucess(String text);
        void  conFail();
    }
    public ConWeb (conWebCallBack conWebCallBack){
        super();
        this.conWebCallBack = conWebCallBack;
    }
    private boolean conTag = false;
    public String makePostCon(String path) throws MalformedURLException {
        final String[] text = {null};
        URL url = null;
            url = new URL(path);
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "{}");

        final Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               conWebCallBack.conFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                text[0] = response.body().string();
                conWebCallBack.conSucess(text[0]);
            }
        });
        conTag = false;
        return text[0];
    }

    public String makeGetcon(Uri path) throws MalformedURLException {
        ConWebTag = true;
        final String[] text = {null};
        URL url = new URL(String.valueOf(path));
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                  conWebCallBack.conFail();
                  ConWebTag = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] str =response.body().bytes();
                String type = Utils.anaWebUnicode(str);
                text[0] =new String(str,type);
                conWebCallBack.conSucess(text[0]);
                ConWebTag = false;
            }
        });
        if (text[0] != null) {
            conTag = false;
        }
        return text[0];
    }

    public boolean getTag() {
        return conTag;
    }
}