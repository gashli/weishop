package com.gashli.wshop.dao;


import com.gashli.wshop.entity.User;

import java.util.List;

public abstract interface IUserDao extends IBaseDao<User>
{
  public abstract User getUserByName(String paramString);

  public abstract User getUserByPhone(String paramString);

  public abstract User getUserByNameAndPhone(String paramString1, String paramString2);

  public abstract User login(String paramString1, String paramString2);

  public abstract User getUserByNo(String paramString);

  public abstract List<User> levelUserList(String paramString);

  public abstract List<User> levelUserTodayList(String paramString);

  public abstract List<User> levelUserTodayStatusList(String paramString);
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.IUserDao
 * JD-Core Version:    0.6.2
 */