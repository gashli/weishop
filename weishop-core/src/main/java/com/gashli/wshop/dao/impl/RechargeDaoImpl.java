 package com.gashli.wshop.dao.impl;


 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IRechargeDao;
 import com.gashli.wshop.entity.Recharge;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("rechargeDao")
 @Scope("prototype")
 public class RechargeDaoImpl extends BaseDaoImpl<Recharge>
   implements IRechargeDao
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public Recharge findByNo(String no) {
     String hql = "from Recharge where no=:no";
     Recharge recharge = (Recharge)getSession().createQuery(hql)
       .setString("no", no).uniqueResult();
     return recharge;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.RechargeDaoImpl
 * JD-Core Version:    0.6.2
 */