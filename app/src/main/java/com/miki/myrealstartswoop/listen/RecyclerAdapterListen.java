package com.miki.myrealstartswoop.listen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.miki.myrealstartswoop.ReadingActivity;

    public class  RecyclerAdapterListen implements View.OnClickListener{
         private String Url;
         private Activity activity;
         public RecyclerAdapterListen(String Url,Activity activity){
             this.Url=Url;
             this.activity =activity;
         }
        @Override
        public void onClick(View v) {
            Intent intent =new Intent();
            intent.setClass(activity,ReadingActivity.class);
            activity.startActivity(intent);
        }

    }
