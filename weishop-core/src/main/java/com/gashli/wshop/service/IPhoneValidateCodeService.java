package com.gashli.wshop.service;


import com.gashli.wshop.entity.PhoneValidateCode;

public abstract interface IPhoneValidateCodeService<T extends PhoneValidateCode> extends IBaseService<T>
{
  public abstract PhoneValidateCode getPhoneValidateCode(String paramString1, String paramString2);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IPhoneValidateCodeService
 * JD-Core Version:    0.6.2
 */