package com.gashli.wshop.dao.impl;



import com.gashli.wshop.dao.IWithdrawDao;
import com.gashli.wshop.entity.Withdraw;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("withdrawDao")
@Scope("prototype")
public class WithdrawDaoImpl extends BaseDaoImpl<Withdraw>
  implements IWithdrawDao
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.WithdrawDaoImpl
 * JD-Core Version:    0.6.2
 */