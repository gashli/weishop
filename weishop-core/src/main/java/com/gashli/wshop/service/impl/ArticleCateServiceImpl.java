 package com.gashli.wshop.service.impl;




 import java.util.List;
 import javax.annotation.Resource;

 import com.gashli.wshop.dao.IArticleCateDao;
 import com.gashli.wshop.entity.ArticleCate;
 import org.springframework.context.annotation.Scope;
 import org.springframework.stereotype.Repository;
 import com.gashli.wshop.service.IArticleCateService;

 @Repository("articleCateService")
 @Scope("prototype")
 public class ArticleCateServiceImpl<T extends ArticleCate> extends BaseServiceImpl<T>
   implements IArticleCateService<T>
 {

   @Resource(name="articleCateDao")
   private IArticleCateDao<T> articleCateDao;

     public ArticleCateServiceImpl() {
     }

     public List<T> listByFatherId(int fid)
   {
     return this.articleCateDao.listByFatherId(fid);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.ArticleCateServiceImpl
 * JD-Core Version:    0.6.2
 */