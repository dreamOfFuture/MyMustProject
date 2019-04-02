package com.miki.intentcore;

import com.miki.intentcore.utils.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadingConWeb  {
    public static boolean isBusyTag =false;
    private ConReading conReading;
    public ReadingConWeb(ConReading conReading){
        this.conReading=conReading;
    }
     public interface ConReading{
         void anaSuccess(String text);
    }
    public void startAna(String path) throws MalformedURLException {
        isBusyTag=true;
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
                isBusyTag=false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] str =response.body().bytes();
                String type = Utils.anaWebUnicode(str);
                text[0] =new String(str,type);
                conReading.anaSuccess(text[0]);
                isBusyTag=false;
            }
        });
    }
}
