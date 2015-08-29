 package com.gashli.wshop.entity;

 import java.io.Serializable;
 import javax.persistence.Entity;
 import javax.persistence.JoinColumn;
 import javax.persistence.Table;

 @Entity

 @Table(name="message")
 public class Message extends BaseBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String title;

   @JoinColumn(columnDefinition="text")
   private String content;

   @JoinColumn(columnDefinition="text")
   private String reply;

   public String getTitle()
   {
     return this.title;
   }
   public void setTitle(String title) {
     this.title = title;
   }
   public String getContent() {
     return this.content;
   }
   public void setContent(String content) {
     this.content = content;
   }
   public String getReply() {
     return this.reply;
   }
   public void setReply(String reply) {
     this.reply = reply;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.entities.Message
 * JD-Core Version:    0.6.2
 */