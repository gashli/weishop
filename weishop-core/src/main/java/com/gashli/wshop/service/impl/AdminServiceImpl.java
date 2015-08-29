 package com.gashli.wshop.service.impl;




 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IAdminDao;
 import com.gashli.wshop.entity.Admin;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;
 import com.gashli.wshop.service.IAdminService;

 @Service("adminService")
 @Scope("prototype")
 public class AdminServiceImpl<T extends Admin> extends BaseServiceImpl<T>
   implements IAdminService<T>
 {

   @Resource(name="adminDao")
   private IAdminDao adminDao;

     public AdminServiceImpl() {
     }

     public Admin login(Admin admin)
   {
     return this.adminDao.login(admin);
   }

   public Admin getAdminName(String name)
   {
     return this.adminDao.getAdminName(name);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.AdminServiceImpl
 * JD-Core Version:    0.6.2
 */