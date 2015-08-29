package com.gashli.wshop.service.impl;


import com.gashli.wshop.dao.IFinancialDao;
import com.gashli.wshop.entity.Financial;
import com.gashli.wshop.service.IFinancialService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("financialService")
@Scope("prototype")
public class FinancialServiceImpl<T extends Financial> extends BaseServiceImpl<T>
        implements IFinancialService<T> {

    @Resource(name = "financialDao")
    private IFinancialDao financialDao;

    public FinancialServiceImpl() {
    }

    public List<Financial> getByUser(Integer userId) {
        return this.financialDao.getByUser(userId);
    }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.FinancialServiceImpl
 * JD-Core Version:    0.6.2
 */