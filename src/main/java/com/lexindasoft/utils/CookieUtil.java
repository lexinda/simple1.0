package com.lexindasoft.utils;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie相关
 * 
 * @author MuZongyan
 * 
 */
public class CookieUtil {

    // �?��
    public static final int AGE_DAY = 86400;
    // �?���?
    public static final int AGE_MONTH = AGE_DAY*30;
    // 十分�?
    public static final int AGE_TEN_MIN = 600;

    public static final String NAME_USERID = "pep_userid";
    
    public static final String NAME_TICKET = "pep_token";

    public static final String NAME_APPKEY = "pep_key";
    
    //public static final String VIRTUAL_TOKEN = "";

    /**
     * 设置cookie
     * 
     * @param response
     * @param name
     *            cookie名字
     * @param value
     *            cookie�?
     * @param maxAge
     *            cookie生命周期 以秒为单�?
     */
    public static void addCookie(HttpServletResponse response, String name,
            String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     * 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}

