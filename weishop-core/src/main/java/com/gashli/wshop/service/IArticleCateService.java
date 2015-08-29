package com.gashli.wshop.service;


import com.gashli.wshop.entity.ArticleCate;

import java.util.List;

public abstract interface IArticleCateService<T extends ArticleCate> extends IBaseService<T>
{
  public abstract List<T> listByFatherId(int paramInt);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IArticleCateService
 * JD-Core Version:    0.6.2
 */