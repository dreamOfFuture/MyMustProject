package com.miki.intentcore;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.miki.intentcore.Bean.BookListBean;
import com.miki.intentcore.modemanger.AnaModel;
import com.miki.intentcore.modemanger.StartAna;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnManager implements ConWeb.conWebCallBack{
    private conWebCallBack conWebCallBack=null;
    List<ConWeb> conWebs=new ArrayList<>();
    @Override
    public void conSucess(String text) {
         conWebCallBack.conSucess(text);
         BookListBean list =StartAna.startAna(text);
        if (list!=null) {
            conWebCallBack.SwoopSuccess(list);
            conWebCallBack.needAsyThread();
        }
    }

    @Override
    public void conFail() {
     conWebCallBack.conFail();
    }

    private static ConnManager connManager = null;

    private ConnManager(conWebCallBack conWebCallBack) {
        super();
        this.conWebCallBack = conWebCallBack;
    }

    public static ConnManager getConnManager(conWebCallBack conWebCallBack) {
        if (connManager != null) {
            return connManager;
        } else {
            connManager = new ConnManager(conWebCallBack);
            return connManager;
        }
    }
   public interface  conWebCallBack{
         void  conSucess(String text);
         void  conFail();
         void SwoopSuccess(BookListBean listBean);
         void needAsyThread();

   }
   public  boolean startCon(Uri path,String title){
        ConWeb conWeb =null;
        AnaModel.startSelectModel(title);
        if (conWebs.size()<1){
           conWeb =new ConWeb(this);
            conWebs.add(conWeb);
        }else {
            conWeb =conWebs.get(0);
        }
       try {
           if (AnaModel.anaModel!=-1&&!conWeb.ConWebTag){
               conWeb.makeGetcon(path);
           }else if (AnaModel.anaModel!=-1&& conWeb.ConWebTag){
               conWebCallBack.conFail();
           }
       } catch (MalformedURLException e) {
           return  false;
       }
       return  true;
   }
}
