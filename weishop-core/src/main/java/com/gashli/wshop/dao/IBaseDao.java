package com.gashli.wshop.dao;

import org.hibernate.Query;

import java.util.List;

public abstract interface IBaseDao<T>
{
  public abstract T findById(Class<T> paramClass, int paramInt);

  public abstract boolean saveOrUpdate(T paramT);

  public abstract boolean delete(T paramT);

  public abstract void deleteAll(String paramString1, String paramString2);

  public abstract List<T> list(String paramString);

  public abstract List<T> list(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract int getTotalCount(String paramString, Object[] paramArrayOfObject);

  public abstract Query createQuery(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IBaseDao
 * JD-Core Version:    0.6.2
 */