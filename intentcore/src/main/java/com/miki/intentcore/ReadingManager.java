package com.miki.intentcore;

import android.util.Log;

import com.miki.intentcore.modemanger.StartReadingAna;

import java.net.MalformedURLException;
import java.util.List;

public class ReadingManager implements  ReadingConWeb.ConReading{
    private static ReadingManager readingManager;
    private ReadingCallBack callBack;
    private static  ReadingConWeb conWeb;
    private ReadingManager(ReadingCallBack callBack) {
        this.callBack=callBack;
    }

    public static ReadingManager getReadingManager(ReadingCallBack callBack) {
        if (readingManager != null)
            return readingManager;
        else {
            readingManager = new ReadingManager(callBack);
            return readingManager;
        }
    }

    @Override
    public void anaSuccess(String text) {
        callBack.canReading(StartReadingAna.startAna(text));
    }
    public void startReading(String path){
        if (conWeb==null){
            conWeb=new ReadingConWeb(this);
        }
        try {
            conWeb.startAna(path);
        } catch (MalformedURLException e) {
            callBack.failRading();
        }
    }
    public  interface ReadingCallBack{
        void canReading(String text);
        void failRading();
    }
     public  boolean isBusyCon(){
        return conWeb.isBusyTag;
     }
}
