 package com.gashli.wshop.utils;

 import javax.servlet.http.HttpServletRequest;

 public class IpUtils
 {
   public static String getIpAddress(HttpServletRequest request)
     throws Exception
   {
     String ip = request.getHeader("X-Real-IP");
     if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip))) {
       return ip;
     }
     ip = request.getHeader("X-Forwarded-For");
     if ((!StringUtils.isBlank(ip)) && (!"unknown".equalsIgnoreCase(ip)))
     {
       int index = ip.indexOf(',');
       if (index != -1) {
         return ip.substring(0, index);
       }
       return ip;
     }

     return request.getRemoteAddr();
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IpUtils
 * JD-Core Version:    0.6.2
 */