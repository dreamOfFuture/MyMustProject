package com.miki.intentcore.modemanger;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.miki.intentcore.Bean.BookListBean;
import com.miki.intentcore.Bean.WebAppBean;
import com.miki.intentcore.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartAna {
    public static BookListBean startAna(String text) {
        String title = null;
        String author = null;
        String info = null;
        Drawable icon = null;
        String other =null;
        List<HashMap<String, String>> list = null;
        Document doc = Jsoup.parse(text);
        WebAppBean webAppBean = WebAppBean.getWebApp();
        String[] datas = webAppBean.getDatas();
        if (webAppBean != null) {
            if (datas[5]!=null){
                Elements elementEx =doc.select(datas[5].split("\\|")[0]);
                if (elementEx.size()>0){
                String need =datas[5].split("\\|")[1];
                other=Utils.catUrl( elementEx.get(0).attr(need));
            }
            }
            for (int i = 0; i < datas.length; i++) {
                switch (i) {
                    case 0:
                        if (datas[0] == null) break;
                        Elements elements = doc.select(datas[0]);
                        if (elements.size() > 0) {
                            title = elements.get(0).text();
                        }
                        break;
                    case 1:
                        if (datas[1] == null) continue;
                        Elements elements1 = doc.select(datas[1]);
                        if (elements1.size() > 0) {
                            author = elements1.get(0).text();
                        }
                        break;
                    case 2:
                        if (datas[2] == null) continue;
                        Elements elements2 = doc.select(datas[2]);
                        if (elements2.size() > 0) {
                        }
                        break;
                    case 3:
                        if (datas[3] == null) continue;
                        Elements elements3 = doc.select(datas[3]);
                        if (elements3.size() > 0) {
                            info = elements3.get(0).text();
                        }
                        break;
                    case 4:
                        if (datas[4] == null) break;
                        String[] filter = datas[4].split("\\|");
                        Elements elements4 = doc.select(filter[0]);
                        if (elements4.size() > 0) {
                            list = new ArrayList<>();
                        }
                        for (Element e : elements4) {
                            HashMap<String, String> map = new HashMap<>();
                            if (other!=null){
                                map.put(e.text(), other+e.attr(filter[1]));
                            }else {
                                map.put(e.text(), e.attr(filter[1]));
                            }
                            list.add(map);
                        }
                        break;
                        default:
                            break;
                }
            }
            if (title != null || list != null) {
                if (other!=null){
                    return new BookListBean(title, icon, author, info, list,AnaModel.orgin_web,other,AnaModel.book_sort);
                }else {
                    return new BookListBean(title, icon, author, info, list,AnaModel.orgin_web,null,AnaModel.book_sort);
                }

            }
        }
        return null;
    }

}
