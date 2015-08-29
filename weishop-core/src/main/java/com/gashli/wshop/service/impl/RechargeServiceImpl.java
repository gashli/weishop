 package com.gashli.wshop.service.impl;




 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IRechargeDao;
 import com.gashli.wshop.entity.Recharge;
 import com.gashli.wshop.service.IRechargeService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;

 @Service("rechargeService")
 @Scope("prototype")
 public class RechargeServiceImpl<T extends Recharge> extends BaseServiceImpl<T>
   implements IRechargeService<T>
 {

   @Resource(name="rechargeDao")
   private IRechargeDao rechargeDao;

     public RechargeServiceImpl() {
     }

     public Recharge findByNo(String no)
   {
     return this.rechargeDao.findByNo(no);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.RechargeServiceImpl
 * JD-Core Version:    0.6.2
 */