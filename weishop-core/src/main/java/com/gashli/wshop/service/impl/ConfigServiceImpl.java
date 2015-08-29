package com.gashli.wshop.service.impl;


import com.gashli.wshop.entity.Config;
import com.gashli.wshop.service.IConfigService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("configService")
@Scope("prototype")
public class ConfigServiceImpl<T extends Config> extends BaseServiceImpl<T>
        implements IConfigService<T> {
    public ConfigServiceImpl() {
    }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.ConfigServiceImpl
 * JD-Core Version:    0.6.2
 */