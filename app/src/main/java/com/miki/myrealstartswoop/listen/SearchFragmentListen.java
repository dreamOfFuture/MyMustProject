package com.miki.myrealstartswoop.listen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.miki.intentcore.Bean.BookListBean;
import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.SQLiteDataBase.MySQLite;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.adapter.ListViewAdapter;

public class SearchFragmentListen implements View.OnClickListener {
    private static boolean isConChangeCollect = true;
    private static ListViewAdapter adapter;
    private static ImageButton iSort;
    private static ImageButton iCollect;
    private static TextView iShowSort;
    private static EditText serachText;
    private static EditText serachNumber;
    private static SearchFragmentListen searchFragmentListen;

    public static void setListen(ListViewAdapter listAdapter, View view) {
        if (searchFragmentListen == null) {
            searchFragmentListen = new SearchFragmentListen();
        }

        adapter = listAdapter;
        iSort = view.findViewById(R.id.book_list_icon);
        iCollect = view.findViewById(R.id.book_collect);
        serachNumber=view.findViewById(R.id.list_item_search_number);
        serachText=view.findViewById(R.id.list_item_text_search);
        iShowSort = view.findViewById(R.id.book_list_info);
        iSort.setOnClickListener(searchFragmentListen);
        iCollect.setOnClickListener(searchFragmentListen);
        serachNumber.setOnClickListener(searchFragmentListen);
        serachText.setOnClickListener(searchFragmentListen);
        iShowSort.setOnClickListener(searchFragmentListen);
        dataInit(ItemListDiata.book_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_list_icon:
                adapter.changSort();
                if (ItemListDiata.conChange) {
                    iSort.setImageResource(R.drawable.ic_book_list_down);
                    iShowSort.setText(R.string.book_list_down);
                } else {
                    iSort.setImageResource(R.drawable.ic_book_list_up);
                    iShowSort.setText(R.string.book_list_up);
                }
                break;
            case R.id.book_collect:
                if (isConChangeCollect) {
                    iCollect.setImageResource(R.drawable.ic_book_collect_yes);
                    adapter.addBookToSQL();
                } else {
                    if (adapter.DeSaveBook(ItemListDiata.book_name)){
                        iCollect.setImageResource(R.drawable.ic_book_collect_no);
                    }
                }
                isConChangeCollect = !isConChangeCollect;
                break;
            case R.id.list_item_number_go:
                if (serachNumber.getText() != null) {
                    String text = String.valueOf(serachNumber.getText());
                    adapter.flayItem(Integer.parseInt(text));
                }
                break;
            case R.id.list_item_text_search_go:
                if (serachText.getText() != null) {
                    String str = String.valueOf(serachText.getText());
                    adapter.seracherItem(str);
                }
                break;
            default:
                break;
        }
    }
    private static void dataInit(String book_name){
        MySQLite sqLiteDatabase =adapter.checkBookSave();
        Cursor cursor = sqLiteDatabase.checkBook(book_name);

        if (!cursor.moveToFirst()){
            isConChangeCollect=true;
            iCollect.setImageResource(R.drawable.ic_book_collect_no);
        }else {
            isConChangeCollect=false;
            iCollect.setImageResource(R.drawable.ic_book_collect_yes);
            ItemListDiata.book_reading = Integer.parseInt(cursor.getString(cursor.getColumnIndex("book_reading")));
            if ("1".equals(cursor.getInt(cursor.getColumnIndex("book_sort")))){
                ItemListDiata.conChange=true;
            }else {
                ItemListDiata.conChange=false;
            }
            cursor.close();
        }
        sqLiteDatabase.close();
    }
}
