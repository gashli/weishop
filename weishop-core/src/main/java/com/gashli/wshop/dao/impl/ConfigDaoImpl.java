package com.gashli.wshop.dao.impl;

import com.gashli.wshop.dao.IConfigDao;
import com.gashli.wshop.entity.Config;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("configDao")
@Scope("prototype")
public class ConfigDaoImpl extends BaseDaoImpl<Config>
  implements IConfigDao
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ConfigDaoImpl
 * JD-Core Version:    0.6.2
 */