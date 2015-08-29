package com.gashli.wshop.dao;


import com.gashli.wshop.entity.ProductCate;

import java.util.List;

public abstract interface IProductCateDao<T extends ProductCate> extends IBaseDao<T>
{
  public abstract List<T> listByFatherId(int paramInt);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IProductCateDao
 * JD-Core Version:    0.6.2
 */