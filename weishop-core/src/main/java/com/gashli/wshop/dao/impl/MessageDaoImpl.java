package com.gashli.wshop.dao.impl;


import com.gashli.wshop.dao.IMessageDao;
import com.gashli.wshop.entity.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("messageDao")
@Scope("prototype")
public class MessageDaoImpl<T extends Message> extends BaseDaoImpl<T>
  implements IMessageDao<T>
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.MessageDaoImpl
 * JD-Core Version:    0.6.2
 */