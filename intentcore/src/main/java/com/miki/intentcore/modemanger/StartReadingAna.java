package com.miki.intentcore.modemanger;

import com.miki.intentcore.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.miki.intentcore.modemanger.AnaModel.anaModel;

public class StartReadingAna {
    public static String startAna(String text) {
        Pattern pattern = null;
        String reading = null;
        switch (anaModel) {
            case -1:
                break;
            case 0:
                pattern = Pattern.compile(AnaModel.webAppReadingOne);
                break;
            case 1:
                pattern = Pattern.compile(AnaModel.webAppReadingTwo);
                break;
        }
        if (pattern != null) {
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                reading = Utils.xmlChangeTxt(matcher.group(0));
            }
        }
        return reading;
    }
}
