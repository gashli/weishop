package com.gashli.wshop.service;


import com.gashli.wshop.entity.Commission;

import java.util.List;

public abstract interface ICommissionService<T extends Commission> extends IBaseService<T>
{
  public abstract List<Commission> getByUser(Integer paramInteger);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.ICommissionService
 * JD-Core Version:    0.6.2
 */