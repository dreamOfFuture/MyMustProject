package com.miki.myrealstartswoop.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.miki.myrealstartswoop.R;
import com.miki.intentcore.Bean.BeanBase;
import com.miki.myrealstartswoop.callback.ActivityCallBack;
import com.miki.myrealstartswoop.callback.FragmentCallback;
import com.miki.myrealstartswoop.callback.ViewCallBack;
import com.miki.myrealstartswoop.ui.MyWebView;

import java.util.List;

public class fragmet_web extends Fragment implements ViewCallBack, ActivityCallBack {
    private MyWebView webView;
    Activity activity;
    FragmentCallback fr;
    private static  Bundle  webViewState;
    private ProgressBar progressBar;
    public static String url ="https://www.baidu.com" ;
    private static final String hideTitle="百度一下,你就知道";

    public fragmet_web() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        fr = (FragmentCallback) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragmet_web, container, false);
        progressBar = view.findViewById(R.id.load_web);
        webView=view.findViewById(R.id.web_view);
        webView.startMyWebView(progressBar,this);
        if (webViewState != null) {
            webView.restoreState(webViewState);
        } else if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.loadUrl(url);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onPause() {
        super.onPause();
        webView.onPause();
        webViewState =new Bundle();
        webView.saveState(webViewState);
    }

    @Override
    public void viewCallTitleChange(String title) {
        if (!title.equals(hideTitle))
        fr.titleChange(title);
    }

    @Override
    public void viewCallUrl(Uri uri) {
        fr.CallBackFagment(uri,null);
    }

    @Override
    public void onBackPress() {
        webView.onBackPress();
    }

    @Override
    public void onWebDate(BeanBase bean) {
        if (bean==null){
            fr.titleChange(activity.getResources().getString(R.string.home_page));
        }else {

        }
    }

    @Override
    public void AsyThreaData(Handler handler) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
}
