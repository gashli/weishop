package com.gashli.wshop.dao.impl;


import com.gashli.wshop.dao.IArticleDao;
import com.gashli.wshop.entity.Article;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
@Scope("prototype")
public class ArticleDaoImpl<T extends Article> extends BaseDaoImpl<T>
  implements IArticleDao<T>
{
}

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.ArticleDaoImpl
 * JD-Core Version:    0.6.2
 */