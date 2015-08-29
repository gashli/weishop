package com.gashli.wshop.service.impl;



import com.gashli.wshop.entity.Article;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.gashli.wshop.service.IArticleService;

@Repository("articleService")
@Scope("prototype")
public class ArticleServiceImpl<T extends Article> extends BaseServiceImpl<T>
  implements IArticleService<T>
{
  public ArticleServiceImpl() {
  }
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.service.impl.ArticleServiceImpl
 * JD-Core Version:    0.6.2
 */