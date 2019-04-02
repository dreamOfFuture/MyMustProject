package com.miki.myrealstartswoop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.miki.intentcore.Bean.BookListBean;
import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.ReadingActivity;
import com.miki.myrealstartswoop.SQLiteDataBase.MySQLite;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.utils.Utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String>  implements AdapterView.OnItemClickListener {
   private static ListView listView ;
   private Activity activity;
   private  static  ListViewAdapter adapter;
    public static ListViewAdapter getList (Context context){
        if (adapter==null){
            adapter=new ListViewAdapter(context, R.layout.list_view_item ,ItemListDiata.saveText,(Activity) context);
        }
        return  adapter;
    }
    public  void setOnItem(ListView listView){
        this.listView=listView;
        listView.setOnItemClickListener(this);
    }
    private ListViewAdapter(Context context, int resource,List<String> list ,Activity activity) {
        super(context, resource,list);
        this.activity=activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (ItemListDiata.conChange){
            ItemListDiata.savePosition = ItemListDiata.saveUrl.size()-position;
        }else {
            ItemListDiata.savePosition =position;
        }
        Intent intent =new Intent();
        intent.setClass(activity,ReadingActivity.class);
        activity.startActivityForResult(intent,0);
    }
    public void changSort(){
        ItemListDiata.changListData();
        notifyDataSetChanged();
    }
    public boolean seracherItem(String str){
        for(int i=0;i<ItemListDiata.saveText.size();i++){
            if (ItemListDiata.saveText.get(i).contains(str)){
                listView.smoothScrollToPosition(i);
                notifyDataSetChanged();
               return  true;
            }
        }
        return  false;
    }
    public  void  flayItem(int i){
        listView.setSelection(i);
    }
    public  boolean addBookToSQL(){
        MySQLite mySQLite =new MySQLite(activity);
        String con;
        byte[] bytes;
        if (ItemListDiata.conChange){
            con ="1";
        }else {
            con ="0";
        }
        try {
            bytes=Utils.DrawToByte(ItemListDiata.book_icon);
        }catch (Exception e){
            bytes=Utils.DrawToByte(activity.getResources().getDrawable(R.mipmap.have_nothing));
        }

        mySQLite.insertData(ItemListDiata.book_name,ItemListDiata.book_uri
                ,String.valueOf(ItemListDiata.savePosition),con,bytes,ItemListDiata.book_web);
        return true;
    }
    public MySQLite checkBookSave(){
        MySQLite mySQLite =new MySQLite(activity);
       return  mySQLite;
    }
    public  boolean DeSaveBook(String book_name){
       try{
           MySQLite.deleteData(book_name);
           return  true;
       }catch (Exception e){
           return false;
       }finally {
       }
    }
}
