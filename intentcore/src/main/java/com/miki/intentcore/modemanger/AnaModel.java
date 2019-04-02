package com.miki.intentcore.modemanger;

import com.miki.intentcore.Bean.WebAppBean;

public class AnaModel {
  private final static String webAppTitleOne="div.index_block>h1";
  private final static String webAppAuthorOne="div.index_block> p";
  private final static String webAppMeunOne="ul.chapter>li>a|href";
  private final static String webAppOtherOne="div.ablum_read>span.margin_right>a|href";
  public  final static String webAppReadingOne="<div class=\"txt.+?/div>";

  private final static boolean[] BookSort={true};
  public  static  boolean book_sort = false;
  private static String[] webbooks =new String[]{"顶点小说"};

  public static String orgin_web;
  public  static  int  anaModel=-1;
    public static void  startSelectModel(String title){
        for (int i=0;i<webbooks.length;i++){
            if (title.contains(webbooks[i])){
                addData(i);
                anaModel=i;
                book_sort=BookSort[i];
                orgin_web=webbooks[i];
                break;
            }
        }
    }
    private static void addData(int i){
        WebAppBean appBean = WebAppBean.getWebApp();
        switch(i){
            case 0: appBean.setData(webAppTitleOne,webAppAuthorOne,null,null,webAppMeunOne,webAppOtherOne);
            break;
            default:break;
        }
    }

}
