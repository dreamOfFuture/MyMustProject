package com.miki.intentcore.modemanger;

import com.miki.intentcore.Bean.WebAppBean;

public class AnaModel {
    private final static String webAppTitleOne = "div.index_block>h1";
    private final static String webAppAuthorOne = "div.index_block> p";
    private final static String webAppMeunOne = "ul.chapter>li>a";
    private final static String webAppOtherOne = "div.ablum_read>span.margin_right>a";
    public final static String webAppReadingOne = "<div class=\"txt.+?/div>";

    private final static String webAppTitleTwo = "div#info>h1";
    private final static String webAppAuthorTwo = "div#info>p";
    private final static String webAppImageTwo = "div#fmimg>img";
    private final static String webAppInfoTwo = "div#intro>p|1";
    private final static String webAppMeunTwo = "div#list>dl>dd>a|3";
    private final static String webAppOtherTwo = "div#info>p>a|3";
    public final static String webAppReadingTwo = "(?s)<div id=\"content.+?/div>";

    private static String[] webbooks = new String[]{"顶点小说", "笔趣阁"};

    private final static boolean[] BookSort = {true,false};
    public static boolean book_sort = false;

    public static String orgin_web;
    public static int anaModel = -1;

    public static void startSelectModel(String title) {
        anaModel = -1;
        for (int i = 0; i < webbooks.length; i++) {
            if (title.contains(webbooks[i])) {
                addData(i);
                anaModel = i;
                book_sort = BookSort[i];
                orgin_web = webbooks[i];
                break;
            }
        }
    }

    private static void addData(int i) {
        WebAppBean appBean = WebAppBean.getWebApp();
        switch (i) {
            case 0:
                appBean.setData(webAppTitleOne, webAppAuthorOne, null, null, webAppMeunOne, webAppOtherOne);
                break;
            case 1:
                appBean.setData(webAppTitleTwo, webAppAuthorTwo, webAppImageTwo, webAppInfoTwo, webAppMeunTwo, webAppOtherTwo);
                break;
            default:
                break;
        }
    }

}
