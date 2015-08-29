 package com.gashli.wshop.dao.impl;


 import java.util.List;

 import com.gashli.wshop.dao.IArticleCateDao;
 import com.gashli.wshop.entity.ArticleCate;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("articleCateDao")
 @Scope("prototype")
 public class ArticleCateDaoImpl<T extends ArticleCate> extends BaseDaoImpl<T>
   implements IArticleCateDao<T>
 {
   public List<T> listByFatherId(int fid)
   {
     String hql = "from ArticleCate where fatherId=" + fid;
     List list = createQuery(hql).list();
     return list;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ArticleCateDaoImpl
 * JD-Core Version:    0.6.2
 */