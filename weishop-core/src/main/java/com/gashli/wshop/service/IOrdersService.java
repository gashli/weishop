package com.gashli.wshop.service;


import com.gashli.wshop.entity.Orders;

public abstract interface IOrdersService<T extends Orders> extends IBaseService<T>
{
  public abstract Orders findByNo(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IOrdersService
 * JD-Core Version:    0.6.2
 */