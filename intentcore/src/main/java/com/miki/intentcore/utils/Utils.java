package com.miki.intentcore.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    public static Drawable gaiWebDiawable(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = getBitmap(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = null;
        if (bitmap != null) {
            drawable = new BitmapDrawable(bitmap);
        }
        return drawable;
    }

    private static Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String anaWebUnicode(byte[] text) {
        String str = new String(text);
        String type = null;
        String reg = "charset=.+?\\s";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            type = matcher.group(0);
        }

        if (type == null || type.contains("utf") || type.contains("UTF")) {
            type = "UTF-8";
        } else if (type.contains("gbk") || type.contains("GBK")) {
            type = "gb2312";
        }
        return type;
    }

    public static String catUrl(String url) {
        String reg = "/[0-9]+.html";
        return url.replaceAll(reg, "/");
    }

    public static String xmlChangeTxt(String text) {
        String reg1 = "&nbsp;";
        String reg2 = "<br /><br />";
        String reg3 ="<div class=\"txt\" id=\"txt\">";
        String reg4 ="</div>";
        String str1 = text.replaceAll(reg1, "").
                replaceAll(reg2, "\r\n\t\t").
                replaceAll(reg3,"\t\t").replaceAll(reg4,"");
        return str1;
    }
}
