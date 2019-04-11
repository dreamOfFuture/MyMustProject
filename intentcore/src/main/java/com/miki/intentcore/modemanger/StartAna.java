package com.miki.intentcore.modemanger;

import android.graphics.drawable.Drawable;

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
        String other = null;
        List<HashMap<String, String>> list = null;
        Document doc = Jsoup.parse(text);
        WebAppBean webAppBean = WebAppBean.getWebApp();
        String[] datas = webAppBean.getDatas();
        if (webAppBean != null) {
            if (datas[5] != null) {
                Elements elementEx = doc.select(datas[5].split("\\|")[0]);
                if (elementEx.size() > 0) {
                    String[] need = datas[5].split("\\|");
                    if (need.length > 1){
                        other = Utils.catUrl(elementEx.get(Integer.parseInt(need[1])).attr("href"));
                    }else {
                        other = Utils.catUrl(elementEx.get(0).attr("href"));
                    }
                }
                if (other == null) return null;
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
                            String url = elements2.attr("src");
                            icon = Utils.gaiWebDiawable(url);
                        }
                        break;
                    case 3:
                        if (datas[3] == null) continue;
                        String[] temp = datas[3].split("\\|");
                        int ts = 0;
                        Elements elements3 = null;
                        if (temp.length > 1) {
                            elements3 = doc.select(temp[0]);
                            ts = Integer.parseInt(temp[1]);
                        } else {
                            elements3 = doc.select(datas[3]);
                        }
                        if (elements3.size() > 0)
                            info = elements3.get(ts).text();
                        break;
                    case 4:
                        if (datas[4] == null) break;
                        Elements elements4 = null;
                        String[] temps = null;
                        if (datas[4].split("\\|").length>1){
                            temps = datas[4].split("\\|");
                            elements4 = doc.select(temps[0]);
                        }else {
                            elements4 = doc.select(datas[4]);
                        }
                        if (elements4.size() > 0) {
                            list = new ArrayList<>();
                        }
                        for (Element e : elements4) {
                            HashMap<String, String> map = new HashMap<>();
                            if (temps!=null &&temps.length>1){
                                map.put(e.text(),other+e.attr("href").split("/")[Integer.parseInt(temps[1])]);
                            }else {
                                map.put(e.text(),other+e.attr("href"));
                            }
                            list.add(map);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
         return new BookListBean(title, icon, author, info, list, AnaModel.orgin_web, other, AnaModel.book_sort);
    }
}
