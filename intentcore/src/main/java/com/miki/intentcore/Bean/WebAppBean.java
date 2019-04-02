package com.miki.intentcore.Bean;

public class WebAppBean {
    private static WebAppBean webAppBean;
    private static String datas[] =new String[6];
    public  void setData(String title ,String author ,String icon,String info,String meun,String other){
        datas[0]=title;
        datas[1]=author;
        datas[2]=icon;
        datas[3]=info;
        datas[4]=meun;
        datas[5]=other;
    }
    public String[] getDatas(){
        return datas;
    }
    private WebAppBean(){
    }
    public static WebAppBean getWebApp(){
        if (webAppBean!=null)
            return webAppBean;
        else
           webAppBean= new WebAppBean();
            return webAppBean;
    }
}
