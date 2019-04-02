package com.miki.intentcore.Bean;

import android.graphics.drawable.Drawable;
import android.net.Uri;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookListBean extends BeanBase {
   private String title;
   private String book_web;
   private boolean book_sort;
   private Drawable book_list_icon;
   private String  book_list_author;
   private String  book_list_info;
   private String book_uri;
   private List<HashMap<String,String>> list;

    public BookListBean(String title, Drawable book_list_icon, String book_list_author, String book_list_info, List<HashMap<String, String>> list,String book_web,String uri,boolean book_sort) {
        this.title = title;
        this.book_list_icon = book_list_icon;
        this.book_list_author = book_list_author;
        this.book_list_info = book_list_info;
        this.list = list;
        this.book_web=book_web;
        this.book_uri=uri;
        this.book_sort=book_sort;
    }
    public String getBook_uri(){
        return book_uri;
    }

    public String getBook_web(){
        return book_web;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getBook_list_icon() {
        return book_list_icon;
    }

    public String getBook_list_author() {
        return book_list_author;
    }

    public String getBook_list_info() {
        return book_list_info;
    }

    public List<HashMap<String, String>> getList() {
        return list;
    }
    public boolean getBook_sort(){
        return  book_sort;
    }
}
