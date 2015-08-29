 package com.gashli.wshop.service.impl;




 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IOrdersDao;
 import com.gashli.wshop.entity.Orders;
 import com.gashli.wshop.service.IOrdersService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;

 @Service("ordersService")
 @Scope("prototype")
 public class OrdersServiceImpl<T extends Orders> extends BaseServiceImpl<T>
   implements IOrdersService<T>
 {

   @Resource(name="ordersDao")
   private IOrdersDao ordersDao;

     public OrdersServiceImpl() {
     }

     public Orders findByNo(String no)
   {
     return this.ordersDao.findByNo(no);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.OrdersServiceImpl
 * JD-Core Version:    0.6.2
 */