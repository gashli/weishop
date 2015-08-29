 package com.gashli.wshop.service.impl;




 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IPhoneValidateCodeDao;
 import com.gashli.wshop.entity.PhoneValidateCode;
 import com.gashli.wshop.service.IPhoneValidateCodeService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("phoneValidateCodeService")
 @Scope("prototype")
 public class PhoneValidateCodeServiceImpl<T extends PhoneValidateCode> extends BaseServiceImpl<T>
   implements IPhoneValidateCodeService<T>
 {

   @Resource(name="phoneValidateCodeDao")
   private IPhoneValidateCodeDao<PhoneValidateCode> phoneValidateCodeDao;

   public PhoneValidateCode getPhoneValidateCode(String phone, String code)
   {
     return this.phoneValidateCodeDao.getPhoneValidateCode(phone, code);
   }

     public PhoneValidateCodeServiceImpl() {
     }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.PhoneValidateCodeServiceImpl
 * JD-Core Version:    0.6.2
 */