package com.miki.myrealstartswoop.viewmanger;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;


import com.miki.myrealstartswoop.LoginApp;
import com.miki.myrealstartswoop.R;
public class ToolBarManager extends  ViewMangerBase{
    private  static  final  int LOG_PAGE=0;
    private  static  final  int SERACHER_PAGE=1;
    private static LoginApp loginApp;

    public ToolBarManager(AppCompatActivity activity, Toolbar toolbar) {
        super(activity,toolbar);
    }

    public void setToolbar(int i){
        switch (i) {
            case LOG_PAGE:
                View view = activity.getLayoutInflater().inflate(R.layout.hometoolbar, toolbar);
                setView(view);
                break;
            case SERACHER_PAGE:
                setView(null);
                break;
            default:
                break;
        }
        if (loginApp==null){
             loginApp = (LoginApp) activity;
        }
        loginApp.toobarChange(i);
    }
}
