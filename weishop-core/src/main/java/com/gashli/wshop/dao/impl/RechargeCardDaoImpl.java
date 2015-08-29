 package com.gashli.wshop.dao.impl;



 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IRechargeCardDao;
 import com.gashli.wshop.entity.RechargeCard;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("rechargeCardDao")
 @Scope("prototype")
 public class RechargeCardDaoImpl extends BaseDaoImpl<RechargeCard>
   implements IRechargeCardDao
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public RechargeCard getByNo(String no) {
     String hql = "from RechargeCard where no like '%" + no + "%' and deleted=0";
     RechargeCard rechargeCard = (RechargeCard)getSession().createQuery(hql).uniqueResult();
     return rechargeCard;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.RechargeCardDaoImpl
 * JD-Core Version:    0.6.2
 */