package com.miki.myrealstartswoop.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miki.intentcore.Bean.BeanBase;
import com.miki.intentcore.Bean.BookListBean;
import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.SQLiteDataBase.MySQLite;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.adapter.ListViewAdapter;
import com.miki.myrealstartswoop.callback.ActivityCallBack;
import com.miki.myrealstartswoop.callback.FragmentCallback;
import com.miki.myrealstartswoop.listen.SearchFragmentListen;

import java.util.List;

public class seracher extends Fragment implements ActivityCallBack {
    FragmentCallback fr;
    BookListBean listBean;
    Activity activity;
    TextView bookName;
    TextView booAuthor;
    ImageView bookIcon;
    TextView bookInfo;
    ListView listView;
    ImageButton iSort;
    ImageButton iCollect;
    ImageButton serachTextGo;
    ImageButton serachNumberGo;
    TextView iShowSort;
    EditText serachText;  //by used into listen
    EditText serachNumber;//by used into listen
    AlertDialog alertDialog;
    GridLayoutManager manager;

    // TODO: Rename and change types of parameters
    public seracher() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        new MySQLite(activity).createTable();
        fr = (FragmentCallback) activity;
        manager = new GridLayoutManager(activity, 2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_seracher, container, false);
        bookName = view.findViewById(R.id.title_name);
        booAuthor = view.findViewById(R.id.author_name);
        bookIcon = view.findViewById(R.id.book_icon);
        bookInfo = view.findViewById(R.id.book_info);
        listView = view.findViewById(R.id.book_list_display);
        iSort = view.findViewById(R.id.book_list_icon);
        iCollect = view.findViewById(R.id.book_collect);
        serachNumberGo = view.findViewById(R.id.list_item_number_go);
        serachTextGo = view.findViewById(R.id.list_item_text_search_go);
        iShowSort = view.findViewById(R.id.book_list_info);
        return view;
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
        alertDialog = new AlertDialog.Builder(activity).setTitle(R.string.determine_the_exit)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                }).setNegativeButton(R.string.no, null).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (alertDialog != null)
            alertDialog.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onWebDate(BeanBase bean) {
        if (ItemListDiata.book_name != null) {
            fr.titleChange(ItemListDiata.book_name);
        } else {
            fr.titleChange(activity.getResources().getString(R.string.book_list_title));
        }
        if (ItemListDiata.book_icon != null) {
            bookIcon.setImageDrawable(ItemListDiata.book_icon);
        } else {
            bookIcon.setImageResource(R.mipmap.have_nothing);

        }
        if (ItemListDiata.book_info != null) {
            bookInfo.setText(ItemListDiata.book_info);
        }
        if (ItemListDiata.book_author != null) {
            booAuthor.setText(ItemListDiata.book_author);
        }
    }

    @Override
    public void AsyThreaData(Handler handler) {
        final ListViewAdapter adpter = ListViewAdapter.getList(activity);
        handler.post(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adpter);
                listView.setTextFilterEnabled(true);
                adpter.setOnItem(listView);
                adpter.notifyDataSetChanged();
                View view = getView();
                if (view == null) {
                    view = LayoutInflater.from(activity).inflate(R.layout.fragment_seracher, null);
                }
                viewSetListen(SearchFragmentListen.getListen(adpter, view));
            }
        });
    }

    private void viewSetListen(SearchFragmentListen listen) {
        if (listen != null) {
            serachNumberGo.setOnClickListener(listen);
            serachTextGo.setOnClickListener(listen);
            iSort.setOnClickListener(listen);
            iCollect.setOnClickListener(listen);
            iShowSort.setOnClickListener(listen);
        }
    }
}