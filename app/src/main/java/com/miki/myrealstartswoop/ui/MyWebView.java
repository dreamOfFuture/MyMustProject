package com.miki.myrealstartswoop.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.miki.myrealstartswoop.R;
import com.miki.myrealstartswoop.callback.ViewCallBack;


public class MyWebView extends WebView {
    private  ProgressBar progressBar;
    private  Fragment fragment;
    private ViewCallBack viewCallBack;
    private int  con =0;

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
       //this.getSettings().setJavaScriptEnabled(true);
    }

    public void  startMyWebView(ProgressBar progressBar,Fragment fragment){
        this.progressBar = progressBar;
        this.fragment =fragment;
        viewCallBack = (ViewCallBack) fragment;
        onCreate();
    }
    public void onCreate(){
        this.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                viewCallBack.viewCallTitleChange(title);
            }
        });
        this.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    viewCallBack.viewCallUrl(Uri.parse(url));
                    return  super.shouldOverrideUrlLoading(view,url);
            }
            });

    }
    public void onBackPress(){
        if (canGoBack()){
            goBack();
        }else {
            AlertDialog alertDialog =new AlertDialog.Builder(fragment.getActivity()).setTitle(R.string.determine_the_exit)
               .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       fragment.getActivity().finish();
                   }
               }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }
}
