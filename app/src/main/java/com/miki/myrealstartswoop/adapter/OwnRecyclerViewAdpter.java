package com.miki.myrealstartswoop.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.SaveListItemData.ListItemData;
import com.miki.myrealstartswoop.callback.FragmentCallback;

import java.net.URL;
import java.util.List;

public class OwnRecyclerViewAdpter extends RecyclerView.Adapter<OwnRecyclerViewAdpter.GainData> {
    List<ListItemData.TemData> list;
    FragmentCallback callback;

    public OwnRecyclerViewAdpter(List<ListItemData.TemData> listData,FragmentCallback callback) {
        list = listData;
        this.callback =callback;
    }


    public class GainData extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton imageButton;

        public GainData(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.book_name);
            imageButton = itemView.findViewById(R.id.book_icon);
        }
    }

    @NonNull
    @Override
    public GainData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_item, viewGroup, false);
        return new GainData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GainData gainData, final int i) {
        gainData.textView.setText(list.get(i).getBook_name());
        gainData.imageButton.setImageDrawable(list.get(i).getBook_icon());
        gainData.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemListDiata.savePosition = list.get(i).getBook_read();
                callback.CallBackFagment(Uri.parse(list.get(i).getBook_uri()),list.get(i).getBook_web());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
