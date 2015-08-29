 package com.gashli.wshop.utils;


 import org.json.JSONException;
 import org.json.JSONObject;

 public class BjuiJson
 {
   public static String json(String statusCode, String message, String tabid, String dialogid, String divid, String closeCurrent, String forward, String forwardConfirm)
     throws JSONException
   {
     JSONObject json = new JSONObject();
     json.put("statusCode", statusCode);
     json.put("message", message);
     json.put("tabid", tabid);
     json.put("dialogid", dialogid);
     json.put("divid", divid);
     json.put("closeCurrent", closeCurrent);
     json.put("forward", forward);
     json.put("forwardConfirm", forwardConfirm);
     return json.toString();
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.BjuiJson
 * JD-Core Version:    0.6.2
 */