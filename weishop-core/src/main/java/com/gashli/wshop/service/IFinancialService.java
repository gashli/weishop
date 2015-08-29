package com.gashli.wshop.service;


import com.gashli.wshop.entity.Financial;

import java.util.List;

public abstract interface IFinancialService<T extends Financial> extends IBaseService<T>
{
  public abstract List<Financial> getByUser(Integer paramInteger);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IFinancialService
 * JD-Core Version:    0.6.2
 */