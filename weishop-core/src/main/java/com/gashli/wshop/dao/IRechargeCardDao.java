package com.gashli.wshop.dao;


import com.gashli.wshop.entity.RechargeCard;

public abstract interface IRechargeCardDao extends IBaseDao<RechargeCard>
{
  public abstract RechargeCard getByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IRechargeCardDao
 * JD-Core Version:    0.6.2
 */