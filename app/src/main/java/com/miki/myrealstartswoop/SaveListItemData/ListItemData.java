package com.miki.myrealstartswoop.SaveListItemData;

import android.graphics.drawable.Drawable;

import com.miki.intentcore.Bean.BeanBase;

import java.util.ArrayList;
import java.util.List;

public class ListItemData extends BeanBase {
    private static List<TemData> list = new ArrayList<>();

    public static List<TemData> getTemDataList() {
        return list;
    }

    public static class TemData {
        Drawable book_icon = null;
        String book_name = null;
        boolean bool_sort = false;
        String book_web = null;
        int book_read = -1;
        String book_uri = null;

        public TemData(Drawable book_icon, String book_name, boolean bool_sort, int book_read, String book_uri, String book_web) {
            this.book_icon = book_icon;
            this.book_name = book_name;
            this.bool_sort = bool_sort;
            this.book_read = book_read;
            this.book_uri = book_uri;
            this.book_web = book_web;
        }

        public boolean isBool_sort() {
            return bool_sort;
        }

        public String  getBook_web(){
            return book_web;
        }

        public Drawable getBook_icon() {
            return book_icon;
        }

        public String getBook_name() {
            return book_name;
        }

        public int getBook_read() {
            return book_read;
        }

        public void setBook_read(int book_read) {
            this.book_read = book_read;
        }

        public String getBook_uri() {
            return book_uri;
        }

        public void setBook_uri(String book_uri) {
            this.book_uri = book_uri;
        }
    }
}
