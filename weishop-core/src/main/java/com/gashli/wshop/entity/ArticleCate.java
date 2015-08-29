 package com.gashli.wshop.entity;

 import javax.persistence.Entity;
 import javax.persistence.Table;

 @Entity
 @Table(name="article_cate")
 public class ArticleCate extends BaseBean
 {
   private String name;
   private int fatherId;

   public String getName()
   {
     return this.name;
   }
   public void setName(String name) {
     this.name = name;
   }
   public int getFatherId() {
     return this.fatherId;
   }
   public void setFatherId(int fatherId) {
     this.fatherId = fatherId;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.entities.ArticleCate
 * JD-Core Version:    0.6.2
 */