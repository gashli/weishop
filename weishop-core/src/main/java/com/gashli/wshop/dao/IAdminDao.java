package com.gashli.wshop.dao;


import com.gashli.wshop.entity.Admin;

public abstract interface IAdminDao extends IBaseDao<Admin>
{
  public abstract Admin login(Admin paramAdmin);

  public abstract Admin getAdminName(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IAdminDao
 * JD-Core Version:    0.6.2
 */