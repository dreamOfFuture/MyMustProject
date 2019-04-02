package com.miki.myrealstartswoop.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;


public class ReadingAdapter extends ArrayAdapter<String> {
    private TextView textView;
    public boolean isChange = false;
    public String text;
    private final static int maxSize = ItemListDiata.saveUrl.size() + 1;
    LayoutInflater layoutInflater;
    private Context context;

    public ReadingAdapter(Context context, int resource) {
        super(context, resource);
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.read_view_item, parent, false);
        textView = view.findViewById(R.id.read_list_one);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.textstylemust);
        if (text != null && isChange) {
            textView.setText(text);
            textView.setTypeface(typeface);
            isChange = false;
        } else {
            textView.setText(R.string.web_con_busy);
        }
        return textView;
    }

    @Override
    public int getCount() {
        return maxSize;
    }
}
