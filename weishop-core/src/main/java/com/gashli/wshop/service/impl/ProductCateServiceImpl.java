 package com.gashli.wshop.service.impl;






 import java.util.List;
 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IProductCateDao;
 import com.gashli.wshop.entity.Product;
 import com.gashli.wshop.entity.ProductCate;
 import com.gashli.wshop.service.IProductCateService;
 import com.gashli.wshop.service.IProductService;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;

 @Repository("productCateService")
 @Scope("prototype")
 public class ProductCateServiceImpl<T extends ProductCate> extends BaseServiceImpl<T>
   implements IProductCateService<T>
 {

   @Resource(name="productCateDao")
   private IProductCateDao<T> productCateDao;

   @Resource(name="productService")
   private IProductService<Product> productService;

   public boolean delete(T baseBean)
   {
     return this.productCateDao.delete(baseBean);
   }

   public List<T> listByFatherId(int fid)
   {
     return this.productCateDao.listByFatherId(fid);
   }

     public ProductCateServiceImpl() {
     }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.ProductCateServiceImpl
 * JD-Core Version:    0.6.2
 */