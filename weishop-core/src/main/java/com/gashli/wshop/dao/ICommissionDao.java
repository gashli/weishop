package com.gashli.wshop.dao;


import com.gashli.wshop.entity.Commission;

import java.util.List;

public abstract interface ICommissionDao extends IBaseDao<Commission>
{
  public abstract List<Commission> getByUser(Integer paramInteger);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ICommissionDao
 * JD-Core Version:    0.6.2
 */