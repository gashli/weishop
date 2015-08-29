package com.gashli.wshop.service.impl;



import com.gashli.wshop.entity.Withdraw;
import com.gashli.wshop.service.IWithdrawService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("withdrawService")
@Scope("prototype")
public class WithdrawServiceImpl<T extends Withdraw> extends BaseServiceImpl<T>
  implements IWithdrawService<T>
{
  public WithdrawServiceImpl() {
  }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.WithdrawServiceImpl
 * JD-Core Version:    0.6.2
 */