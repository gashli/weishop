package com.gashli.wshop.dao;


import com.gashli.wshop.entity.Recharge;

public abstract interface IRechargeDao extends IBaseDao<Recharge>
{
  public abstract Recharge findByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IRechargeDao
 * JD-Core Version:    0.6.2
 */