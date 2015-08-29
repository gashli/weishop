 package com.gashli.wshop.dao.impl;


 import com.gashli.wshop.dao.ICommissionDao;
 import com.gashli.wshop.entity.Commission;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 import javax.annotation.Resource;
 import java.util.List;

 @Repository("commissionDao")
 @Scope("prototype")
 public class CommissionDaoImpl extends BaseDaoImpl<Commission>
   implements ICommissionDao
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public List<Commission> getByUser(Integer userId)
   {
     String hql = "from Commission where user.id=:userId and deleted=0";

     List commissionList = getSession().createQuery(hql)
       .setInteger("userId", userId.intValue()).list();
     return commissionList;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.CommissionDaoImpl
 * JD-Core Version:    0.6.2
 */