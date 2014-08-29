package com.lexindasoft.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AjaxUtils {
	
	private static Gson gson = new Gson();
	
	public static String printJson(Object dataMap, HttpServletRequest req,HttpServletResponse resp) {
    	String result = gson.toJson(dataMap);
    	resp.setContentType("application/json;charset=utf-8");
        return "@" + result;
    }
	
	public static String printJson(int code, String message, HttpServletRequest req,HttpServletResponse resp){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code); 
		map.put("message", message);
		
		return printJson(map,req,resp);
	}
	
	public static String printErrorJson(String message, HttpServletRequest req,HttpServletResponse resp){
		return printJson(-1, message,req,resp);//-1表�?用的error
	}
}
