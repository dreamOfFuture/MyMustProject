package com.miki.myrealstartswoop.callback;

import android.net.Uri;

public interface FragmentCallback {
    void CallBackFagment(Uri uri,String title);
    void titleChange(String title);
    void needDataUpdate();
}
