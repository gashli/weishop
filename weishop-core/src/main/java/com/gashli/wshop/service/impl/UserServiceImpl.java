 package com.gashli.wshop.service.impl;

 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IUserDao;
 import com.gashli.wshop.entity.User;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;
 import com.gashli.wshop.service.IUserService;

 import java.util.List;

 @Service("userService")
 @Scope("prototype")
 public class UserServiceImpl<T extends User> extends BaseServiceImpl<T>
   implements IUserService<T>
 {

   @Resource(name="userDao")
   private IUserDao userDao;

     public UserServiceImpl() {
     }

     public User getUserByName(String name)
   {
     return this.userDao.getUserByName(name);
   }

   public User login(String name, String password)
   {
     return this.userDao.login(name, password);
   }

   public User getUserByPhone(String phone)
   {
     return this.userDao.getUserByPhone(phone);
   }

   public User getUserByNo(String no)
   {
     return this.userDao.getUserByNo(no);
   }

   public List<User> levelUserList(String no)
   {
     return this.userDao.levelUserList(no);
   }

   public List<User> levelUserTodayList(String no)
   {
     return this.userDao.levelUserTodayList(no);
   }

   public List<User> levelUserTodayStatusList(String no)
   {
     return this.userDao.levelUserTodayStatusList(no);
   }

   public User getUserByNameAndPhone(String name, String phone)
   {
     return this.userDao.getUserByNameAndPhone(name, phone);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.UserServiceImpl
 * JD-Core Version:    0.6.2
 */