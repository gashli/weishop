 package com.gashli.wshop.pay.alipay;

 import org.apache.commons.codec.digest.DigestUtils;
 import org.apache.commons.httpclient.methods.multipart.FilePartSource;
 import org.apache.commons.httpclient.methods.multipart.PartSource;

 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.*;

 public class AlipayCore
 {
   public static Map<String, String> paraFilter(Map<String, String> sArray)
   {
     Map result = new HashMap();

     if ((sArray == null) || (sArray.size() <= 0)) {
       return result;
     }

     for (String key : sArray.keySet()) {
       String value = (String)sArray.get(key);
       if ((value != null) && (!value.equals("")) && (!key.equalsIgnoreCase("sign")) &&
         (!key.equalsIgnoreCase("sign_type")))
       {
         result.put(key, value);
       }
     }
     return result;
   }

   public static String createLinkString(Map<String, String> params)
   {
     List keys = new ArrayList(params.keySet());
     Collections.sort(keys);

     String prestr = "";

     for (int i = 0; i < keys.size(); i++) {
       String key = (String)keys.get(i);
       String value = (String)params.get(key);

       if (i == keys.size() - 1)
         prestr = prestr + key + "=" + value;
       else {
         prestr = prestr + key + "=" + value + "&";
       }
     }

     return prestr;
   }

   public static void logResult(String sWord)
   {
     FileWriter writer = null;
     try {
       writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
       writer.write(sWord);
     } catch (Exception e) {
       e.printStackTrace();

       if (writer != null)
         try {
           writer.close();
         } catch (IOException ex) {
           ex.printStackTrace();
         }
     }
     finally
     {
       if (writer != null)
         try {
           writer.close();
         } catch (IOException e) {
           e.printStackTrace();
         }
     }
   }

   public static String getAbstract(String strFilePath, String file_digest_type)
     throws IOException
   {
     PartSource file = new FilePartSource(new File(strFilePath));
     if (file_digest_type.equals("MD5")) {
       return DigestUtils.md5Hex(file.createInputStream());
     }
     if (file_digest_type.equals("SHA")) {
       return DigestUtils.sha256Hex(file.createInputStream());
     }

     return "";
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.AlipayCore
 * JD-Core Version:    0.6.2
 */