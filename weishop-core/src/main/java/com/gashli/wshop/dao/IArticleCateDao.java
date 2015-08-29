package com.gashli.wshop.dao;


import com.gashli.wshop.entity.ArticleCate;

import java.util.List;

public abstract interface IArticleCateDao<T extends ArticleCate> extends IBaseDao<T>
{
  public abstract List<T> listByFatherId(int paramInt);
}
