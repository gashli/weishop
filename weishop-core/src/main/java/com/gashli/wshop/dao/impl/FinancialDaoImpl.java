 package com.gashli.wshop.dao.impl;

 import java.util.List;
 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IFinancialDao;
 import com.gashli.wshop.entity.Financial;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("financialDao")
 @Scope("prototype")
 public class FinancialDaoImpl extends BaseDaoImpl<Financial>
   implements IFinancialDao
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public List<Financial> getByUser(Integer userId)
   {
     String hql = "from Financial where user.id=:userId and deleted=0";

     List financialList = getSession().createQuery(hql)
       .setInteger("userId", userId.intValue()).list();
     return financialList;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.FinancialDaoImpl
 * JD-Core Version:    0.6.2
 */