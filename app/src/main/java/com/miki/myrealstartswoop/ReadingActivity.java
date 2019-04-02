package com.miki.myrealstartswoop;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.Toolbar;

import com.miki.intentcore.ReadingManager;
import com.miki.myrealstartswoop.SQLiteDataBase.MySQLite;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.adapter.ReadingAdapter;

public class ReadingActivity extends ListActivity implements ReadingManager.ReadingCallBack {
    private ReadingManager manager;
    private Handler handler;
    private static Toolbar toolbar;
    private static ReadingAdapter adapter;
    private static boolean isFirst = true;
    private static int conItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readinglayout);
        if (ItemListDiata.savePosition != -1) {
            ItemListDiata.ownChangData();
            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(ItemListDiata.saveText.get(ItemListDiata.savePosition));
            setActionBar(toolbar);
            handler = new Handler();
            isFirst = true;
            conItem = 0;
            adapter = new ReadingAdapter(ReadingActivity.this, R.layout.read_view_item);
            manager = ReadingManager.getReadingManager(this);
            setListAdapter(adapter);
            manager.startReading(ItemListDiata.saveUrl.get(ItemListDiata.savePosition));
        } else {
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int i = getListView().getLastVisiblePosition();
                if (i> conItem&&!manager.isBusyCon()) {
                    conItem = i;
                    toolbar.setTitle(ItemListDiata.saveText.get(ItemListDiata.savePosition));
                            manager.startReading(ItemListDiata.saveUrl.get(ItemListDiata.getUpAutoUpdateSize()));
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void canReading(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.isChange = true;
                adapter.text = text;
                if (isFirst) {
                    adapter.notifyDataSetChanged();
                    isFirst = false;
                    manager.startReading(ItemListDiata.saveUrl.get(ItemListDiata.getUpAutoUpdateSize()));
                }
            }
        });
    }

    @Override
    public void failRading() {
    }

    @Override
    protected void onStop() {
        super.onStop();
        MySQLite mySQLite =new MySQLite(this);
        Cursor cursor =mySQLite.checkBook(ItemListDiata.book_name);
        if (cursor.moveToFirst()){
            mySQLite.updateData(String.valueOf(ItemListDiata.savePosition-1),String.valueOf(ItemListDiata.conChange),ItemListDiata.book_name);
        }
        cursor.close();
    }

}
