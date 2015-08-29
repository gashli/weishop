 package com.gashli.wshop.service.impl;






 import javax.annotation.Resource;

 import com.gashli.wshop.entity.Kami;
 import com.gashli.wshop.entity.Product;
 import com.gashli.wshop.service.IKamiService;
 import com.gashli.wshop.service.IProductService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("productService")
 @Scope("prototype")
 public class ProductServiceImpl<T extends Product> extends BaseServiceImpl<T>
   implements IProductService<T>
 {

   @Resource(name="kamiService")
   private IKamiService<Kami> kamiService;

     public ProductServiceImpl() {
     }

     public boolean delete(T baseBean)
   {
     return this.baseDao.delete(baseBean);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.ProductServiceImpl
 * JD-Core Version:    0.6.2
 */