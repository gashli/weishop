package com.gashli.wshop.dao;


import com.gashli.wshop.entity.Financial;

import java.util.List;

public abstract interface IFinancialDao extends IBaseDao<Financial>
{
  public abstract List<Financial> getByUser(Integer paramInteger);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IFinancialDao
 * JD-Core Version:    0.6.2
 */