 package com.gashli.wshop.dao.impl;

 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IAdminDao;
 import com.gashli.wshop.entity.Admin;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("adminDao")
 @Scope("prototype")
 public class AdminDaoImpl extends BaseDaoImpl<Admin>
   implements IAdminDao
 {

   @Resource(name="sessionFactory")
   private SessionFactory sessionFactory;

   private Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }

   public Admin login(Admin admin)
   {
     String hql = "from Admin as a where a.name=:name and a.password=:password";
     Admin findAdmin = (Admin)getSession().createQuery(hql).setString("name", admin.getName())
       .setString("password", admin.getPassword()).uniqueResult();
     return findAdmin;
   }

   public Admin getAdminName(String name)
   {
     String hql = "from Admin where name=:name";
     Admin admin = (Admin)getSession().createQuery(hql).setString("name", name).uniqueResult();
     return admin;
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.AdminDaoImpl
 * JD-Core Version:    0.6.2
 */