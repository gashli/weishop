 package com.gashli.wshop.dao.impl;



 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IPhoneValidateCodeDao;
 import com.gashli.wshop.entity.PhoneValidateCode;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("phoneValidateCodeDao")
 @Scope("prototype")
 public class PhoneValidateCodeDaoImpl<T extends PhoneValidateCode> extends BaseDaoImpl<T>
   implements IPhoneValidateCodeDao<T>
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public PhoneValidateCode getPhoneValidateCode(String phone, String code)
   {
     String hql = "from PhoneValidateCode where phone=:phone and code=:code and deleted=0 and status=0";
     PhoneValidateCode phoneValidateCode = (PhoneValidateCode)getSession().createQuery(hql)
       .setString("phone", phone).setString("code", code).uniqueResult();
     return phoneValidateCode;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.PhoneValidateCodeDaoImpl
 * JD-Core Version:    0.6.2
 */