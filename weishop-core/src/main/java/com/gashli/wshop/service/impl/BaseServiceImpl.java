 package com.gashli.wshop.service.impl;

 import com.gashli.wshop.dao.IBaseDao;
 import com.gashli.wshop.entity.BaseBean;
 import com.gashli.wshop.service.IBaseService;

 import java.util.List;
 import javax.annotation.Resource;
 import org.hibernate.Query;


 public class BaseServiceImpl<T extends BaseBean>
   implements IBaseService<T>
 {

   @Resource(name="baseDao")
   protected IBaseDao<T> baseDao;

     public BaseServiceImpl() {
     }

     public T findById(Class<T> clazz, int id)
   {
     return (T)(BaseBean)this.baseDao.findById(clazz, id);
   }

   public boolean saveOrUpdate(T baseBean)
   {
     return this.baseDao.saveOrUpdate(baseBean);
   }

   public boolean delete(T baseBean)
   {
     baseBean.setDeleted(true);
     return this.baseDao.saveOrUpdate(baseBean);
   }

   public List<T> list(String hql)
   {
     return this.baseDao.list(hql);
   }

   public List<T> list(String hql, int firstResult, int maxResult, Object[] params)
   {
     return this.baseDao.list(hql, firstResult, maxResult, params);
   }

   public int getTotalCount(String hql, Object[] params)
   {
     return this.baseDao.getTotalCount(hql, params);
   }

   public Query createQuery(String hql)
   {
     return this.baseDao.createQuery(hql);
   }

   public void deleteAll(String entity, String where)
   {
     this.baseDao.deleteAll(entity, where);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.BaseServiceImpl
 * JD-Core Version:    0.6.2
 */