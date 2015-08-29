 package com.gashli.wshop.service.impl;




 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IRechargeCardDao;
 import com.gashli.wshop.entity.RechargeCard;
 import com.gashli.wshop.service.IRechargeCardService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;

 @Service("rechargeCardService")
 @Scope("prototype")
 public class RechargeCardServiceImpl<T extends RechargeCard> extends BaseServiceImpl<T>
   implements IRechargeCardService<T>
 {

   @Resource(name="rechargeCardDao")
   IRechargeCardDao rechargeCardDao;

     public RechargeCardServiceImpl() {
     }

     public RechargeCard getByNo(String no)
   {
     return this.rechargeCardDao.getByNo(no);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.RechargeCardServiceImpl
 * JD-Core Version:    0.6.2
 */