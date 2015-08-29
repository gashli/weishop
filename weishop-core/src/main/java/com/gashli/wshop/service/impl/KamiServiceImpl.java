package com.gashli.wshop.service.impl;



import com.gashli.wshop.entity.Kami;
import com.gashli.wshop.service.IKamiService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("kamiService")
@Scope("prototype")
public class KamiServiceImpl<T extends Kami> extends BaseServiceImpl<T>
  implements IKamiService<T>
{
  public KamiServiceImpl() {
  }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.KamiServiceImpl
 * JD-Core Version:    0.6.2
 */