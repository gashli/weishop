package com.gashli.wshop.dao;


import com.gashli.wshop.entity.Orders;

public abstract interface IOrdersDao extends IBaseDao<Orders>
{
  public abstract Orders findByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IOrdersDao
 * JD-Core Version:    0.6.2
 */