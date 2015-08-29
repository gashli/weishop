package com.gashli.wshop.service;

import com.gashli.wshop.entity.Admin;

public abstract interface IAdminService<T extends Admin> extends IBaseService<T>
{
  public abstract Admin login(Admin paramAdmin);

  public abstract Admin getAdminName(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.IAdminService
 * JD-Core Version:    0.6.2
 */