 package com.gashli.wshop.pay.alipay;

 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Random;

 public class UtilDate
 {
   public static final String dtLong = "yyyyMMddHHmmss";
   public static final String simple = "yyyy-MM-dd HH:mm:ss";
   public static final String dtShort = "yyyyMMdd";

   public static String getOrderNum()
   {
     Date date = new Date();
     DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
     return df.format(date);
   }

   public static String getDateFormatter()
   {
     Date date = new Date();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return df.format(date);
   }

   public static String getDate()
   {
     Date date = new Date();
     DateFormat df = new SimpleDateFormat("yyyyMMdd");
     return df.format(date);
   }

   public static String getThree()
   {
     Random rad = new Random();
     return String.valueOf(rad.nextInt(1000));
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.UtilDate
 * JD-Core Version:    0.6.2
 */