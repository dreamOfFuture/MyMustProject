package com.miki.myrealstartswoop.SaveListItemData;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemListDiata {
    public static int savePosition = 0;
    public static boolean conChange = true;
    public final static List<String> saveText = new ArrayList<>();
    public final static List<String> saveUrl = new ArrayList<>();
    public static String book_name;
    public static Drawable book_icon;
    public static String book_info;
    public static String book_author;
    public static String book_web;
    public static int book_reading;
    public static String book_uri;

    public static void changListData() {
        Collections.reverse(saveText);
        Collections.reverse(saveUrl);
        conChange = !conChange;
    }

    public static void ownChangData() {
        if (conChange) {
            Collections.reverse(saveText);
            Collections.reverse(saveUrl);
        }
    }

    public static int getUpAutoUpdateSize() {
        return ++savePosition;
    }

    public static int getDownAutoUpdateSize() {
        return --savePosition;
    }

    public static String getUrl() {
        return saveUrl.get(savePosition);
    }

    public static String getText() {
        return saveText.get(savePosition);
    }
}
