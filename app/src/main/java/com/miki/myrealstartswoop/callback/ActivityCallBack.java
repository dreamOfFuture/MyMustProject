package com.miki.myrealstartswoop.callback;


import android.os.Handler;

import com.miki.intentcore.Bean.BeanBase;

import java.util.List;

public interface ActivityCallBack {
    void onBackPress();
    void onWebDate(BeanBase bean);
    void AsyThreaData(Handler handler);
}
