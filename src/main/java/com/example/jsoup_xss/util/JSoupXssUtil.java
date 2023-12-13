package com.example.jsoup_xss.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * kbsys
 *
 * @param model
 * @param menuModel
 * @param authId
 * @param request
 * @return
 */
public class JSoupXssUtil {
    public  static String stringBasicCleanXss(String oldStr){
        return Jsoup.clean(oldStr, Safelist.relaxed());
    }
    public  static String stringPermitUrlCleanXss(String oldStr,String permitUrl){
        return Jsoup.clean(oldStr, permitUrl,Safelist.relaxed().preserveRelativeLinks(true));
    }
}
