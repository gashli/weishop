package com.gashli.wshop.service.impl;



import com.gashli.wshop.entity.Message;
import com.gashli.wshop.service.IMessageService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("messageService")
@Scope("prototype")
public class MessageServiceImpl<T extends Message> extends BaseServiceImpl<T>
  implements IMessageService<T>
{
  public MessageServiceImpl() {
  }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.MessageServiceImpl
 * JD-Core Version:    0.6.2
 */