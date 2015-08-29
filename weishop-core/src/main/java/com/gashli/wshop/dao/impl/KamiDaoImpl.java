package com.gashli.wshop.dao.impl;


import com.gashli.wshop.dao.IKamiDao;
import com.gashli.wshop.entity.Kami;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("kamiDao")
@Scope("prototype")
public class KamiDaoImpl<T extends Kami> extends BaseDaoImpl<T>
  implements IKamiDao<T>
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.KamiDaoImpl
 * JD-Core Version:    0.6.2
 */