package com.miki.myrealstartswoop.viewmanger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.miki.myrealstartswoop.R;

public class ViewMangerBase {
    protected AppCompatActivity activity;
    protected Toolbar toolbar;
    private static View view = null;
    public ViewMangerBase(AppCompatActivity activity,Toolbar toolbar){
        this.activity=activity;
        this.toolbar=toolbar;
    };
    public void setTitle(String title){
       TextView textView= view.findViewById(R.id.title_name);
       textView.setText(title);
    }
    protected void  setView(View view){
        this.view = view;
    }
    public String  getTitle(){
        TextView textView =view.findViewById(R.id.title_name);
        return (String) textView.getText();
    }
}
