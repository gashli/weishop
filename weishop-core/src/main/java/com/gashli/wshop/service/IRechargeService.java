package com.gashli.wshop.service;


import com.gashli.wshop.entity.Recharge;

public abstract interface IRechargeService<T extends Recharge> extends IBaseService<T>
{
  public abstract Recharge findByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IRechargeService
 * JD-Core Version:    0.6.2
 */