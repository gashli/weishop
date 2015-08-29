package com.gashli.wshop.service;


import com.gashli.wshop.entity.RechargeCard;

public abstract interface IRechargeCardService<T extends RechargeCard> extends IBaseService<T>
{
  public abstract RechargeCard getByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IRechargeCardService
 * JD-Core Version:    0.6.2
 */