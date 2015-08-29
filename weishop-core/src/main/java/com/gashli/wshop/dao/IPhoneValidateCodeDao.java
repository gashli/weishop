package com.gashli.wshop.dao;


import com.gashli.wshop.entity.PhoneValidateCode;

public abstract interface IPhoneValidateCodeDao<T extends PhoneValidateCode> extends IBaseDao<T>
{
  public abstract PhoneValidateCode getPhoneValidateCode(String paramString1, String paramString2);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IPhoneValidateCodeDao
 * JD-Core Version:    0.6.2
 */