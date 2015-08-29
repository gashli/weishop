package com.gashli.wshop.dao.impl;



import com.gashli.wshop.dao.IProductDao;
import com.gashli.wshop.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("productDao")
@Scope("prototype")
public class ProductDaoImpl<T extends Product> extends BaseDaoImpl<T>
  implements IProductDao<T>
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ProductDaoImpl
 * JD-Core Version:    0.6.2
 */