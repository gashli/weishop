 package com.gashli.wshop.service.impl;




 import java.util.List;
 import javax.annotation.Resource;

 import com.gashli.wshop.dao.ICommissionDao;
 import com.gashli.wshop.entity.Commission;
 import com.gashli.wshop.service.ICommissionService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Service;

 @Service("commissionService")
 @Scope("prototype")
 public class CommissionServiceImpl<T extends Commission> extends BaseServiceImpl<T>
   implements ICommissionService<T>
 {

   @Resource(name="commissionDao")
   private ICommissionDao commissionDao;

     public CommissionServiceImpl() {
     }

     public List<Commission> getByUser(Integer userId)
   {
     return this.commissionDao.getByUser(userId);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.CommissionServiceImpl
 * JD-Core Version:    0.6.2
 */