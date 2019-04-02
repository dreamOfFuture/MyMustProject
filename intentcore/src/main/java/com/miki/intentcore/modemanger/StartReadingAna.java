package com.miki.intentcore.modemanger;

import com.miki.intentcore.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.miki.intentcore.modemanger.AnaModel.anaModel;

public class StartReadingAna {
    public static String startAna(String text){
        String reading=null;
      switch (anaModel){
          case -1:break;
          case 0:
              Pattern pattern =Pattern.compile(AnaModel.webAppReadingOne);
              Matcher matcher = pattern.matcher(text);
              if (matcher.find()){
                  reading=Utils.xmlChangeTxt(matcher.group(0));
              }
              break;
          case 1:break;
      }
        return  reading;
    }
}
