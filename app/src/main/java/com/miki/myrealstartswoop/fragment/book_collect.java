package com.miki.myrealstartswoop.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miki.intentcore.Bean.BeanBase;
import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.SQLiteDataBase.MySQLite;
import com.miki.myrealstartswoop.SaveListItemData.ListItemData;
import com.miki.myrealstartswoop.adapter.OwnRecyclerViewAdpter;
import com.miki.myrealstartswoop.callback.ActivityCallBack;
import com.miki.myrealstartswoop.callback.FragmentCallback;

import java.util.List;

public class book_collect extends Fragment implements ActivityCallBack {
    private RecyclerView recyclerView;
    private Activity activity;
    private GridLayoutManager manager;
    private OwnRecyclerViewAdpter adpter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_collect, container, false);
        recyclerView = null;
        recyclerView = view.findViewById(R.id.book_collect_sort);
        manager = new GridLayoutManager(activity, 3);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    @Override
    public void onResume() {
        FragmentCallback callback = (FragmentCallback) activity;
        callback.needDataUpdate();
        super.onResume();
    }

    public void onButtonPressed() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public void onWebDate(BeanBase bean) {


    }

    @Override
    public void AsyThreaData( final Handler handler) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                SQLiteDatabase db = SQLiteDatabase.openDatabase(activity.getDatabasePath(MySQLite.SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
                Cursor cursor = MySQLite.selectData(db);
                if (!cursor.moveToFirst()) {
                    db.close();
                    return;
                }
                List<ListItemData.TemData> list = ListItemData.getTemDataList();
                list.clear();
                for (int i = 0;i<cursor.getCount();i++){
                    boolean con = false;
                    if (cursor.getInt(cursor.getColumnIndex(MySQLite.book_sort)) == 1)
                        con = true;
                    list.add(new ListItemData.TemData(com.miki.myrealstartswoop.utils.Utils.ByteToDraw(cursor.getBlob(cursor.getColumnIndex(MySQLite.book_icon))),
                            cursor.getString(cursor.getColumnIndex(MySQLite.book_name)),
                            con, cursor.getInt(cursor.getColumnIndex(MySQLite.book_reading)),
                            cursor.getString(cursor.getColumnIndex(MySQLite.book_url)),
                            cursor.getString(cursor.getColumnIndex(MySQLite.book_web))));
                    cursor.moveToNext();
                }
                db.close();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adpter = new OwnRecyclerViewAdpter(ListItemData.getTemDataList(), (FragmentCallback) activity);
                        recyclerView.setAdapter(adpter);
                        adpter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
