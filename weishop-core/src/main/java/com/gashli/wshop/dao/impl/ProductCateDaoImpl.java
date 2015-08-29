 package com.gashli.wshop.dao.impl;



 import java.util.List;

 import com.gashli.wshop.dao.IProductCateDao;
 import com.gashli.wshop.entity.ProductCate;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("productCateDao")
 @Scope("prototype")
 public class ProductCateDaoImpl<T extends ProductCate> extends BaseDaoImpl<T>
   implements IProductCateDao<T>
 {
   public List<T> listByFatherId(int fid)
   {
     String hql = "from ProductCate where fatherId=" + fid;
     List list = createQuery(hql).list();
     return list;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ProductCateDaoImpl
 * JD-Core Version:    0.6.2
 */