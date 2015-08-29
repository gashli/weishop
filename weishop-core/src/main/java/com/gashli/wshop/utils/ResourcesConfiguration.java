 package com.gashli.wshop.utils;

 import java.util.ResourceBundle;

 public class ResourcesConfiguration
 {
   private static Object lock = new Object();
   private static ResourcesConfiguration config = null;
   private static ResourceBundle rb = null;
   private static final String CONFIG_FILE = "resources";

   private ResourcesConfiguration()
   {
     rb = ResourceBundle.getBundle("resources");
   }

   public static ResourcesConfiguration getInstance() {
     synchronized (lock) {
       if (config == null) {
         config = new ResourcesConfiguration();
       }
     }
     return config;
   }

   public String getValue(String key) {
     return rb.getString(key);
   }

   public static void main(String[] args) {
     System.out.println(getInstance().getValue("REGISTER"));
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ResourcesConfiguration
 * JD-Core Version:    0.6.2
 */