//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.gashli.wshop.entity.Article;
import com.gashli.wshop.entity.ArticleCate;
import com.gashli.wshop.entity.Config;
import freemarker.template.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gashli.wshop.service.IArticleCateService;
import com.gashli.wshop.service.IArticleService;
import com.gashli.wshop.service.IConfigService;
import com.gashli.wshop.utils.BjuiJson;
import com.gashli.wshop.utils.BjuiPage;
import com.gashli.wshop.utils.FreemarkerUtils;
import com.gashli.wshop.utils.PageModel;

@Controller("articleAction")
@Scope("prototype")
public class ArticleAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Resource(
            name = "articleService"
    )
    private IArticleService<Article> articleService;
    @Resource(
            name = "articleCateService"
    )
    private IArticleCateService<ArticleCate> articleCateService;
    @Resource(
            name = "configService"
    )
    private IConfigService<Config> configService;
    private Article article;

    public ArticleAction() {
    }

    public void list() {
        String key = this.request.getParameter("key");
        boolean count = false;
        int count1;
        if(!"".equals(key) && key != null) {
            count1 = this.articleService.getTotalCount("from Article where title like \'%" + key + "%\' and deleted=0", new Object[0]);
        } else {
            count1 = this.articleService.getTotalCount("select count(*) from Article where deleted=0", new Object[0]);
            key = "";
        }

        this.page = new BjuiPage(this.pageCurrent, this.pageSize);
        this.page.setTotalCount(count1);
        List list = this.articleService.list("from Article where deleted=0 order by id desc", this.page.getStart(), this.page.getPageSize(), new Object[0]);
        this.cfg = new Configuration();
        this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
        HashMap root = new HashMap();
        root.put("list", list);
        root.put("page", this.page);
        FreemarkerUtils.freemarker(this.request, this.response, "articleList.ftl", this.cfg, root);
    }

    public void add() {
        List articleCatelist = this.articleCateService.list("from ArticleCate where deleted=0");
        String zNodes = "";

        ArticleCate root;
        for(Iterator var4 = articleCatelist.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
            root = (ArticleCate)var4.next();
        }

        this.cfg = new Configuration();
        this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
        HashMap root1 = new HashMap();
        root1.put("zNodes", zNodes);
        FreemarkerUtils.freemarker(this.request, this.response, "articleAdd.ftl", this.cfg, root1);
    }

    public void save() {
        String callbackData = "";

        try {
            if(this.article.getArticleCate().getId().intValue() == 0) {
                callbackData = BjuiJson.json("300", "请选择栏目", "", "", "", "", "", "");
            } else if("".equals(this.article.getContent())) {
                callbackData = BjuiJson.json("300", "请输入内容", "", "", "", "", "", "");
            } else {
                this.article.setDeleted(false);
                this.article.setCreateDate(new Date());
                boolean out = this.articleService.saveOrUpdate(this.article);
                if(out) {
                    callbackData = BjuiJson.json("200", "添加成功", "", "", "", "true", "", "");
                } else {
                    callbackData = BjuiJson.json("300", "添加失败", "", "", "", "", "", "");
                }
            }
        } catch (JSONException var5) {
            var5.printStackTrace();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        PrintWriter out1 = null;

        try {
            out1 = this.response.getWriter();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        out1.print(callbackData);
        out1.flush();
        out1.close();
    }

    public void info() {
        String callbackData = "";
        String idStr = this.request.getParameter("id");

        try {
            PrintWriter e = this.response.getWriter();
            if(idStr != null && !"".equals(idStr)) {
                int id = 0;

                try {
                    id = Integer.parseInt(idStr);
                } catch (Exception var10) {
                    callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
                    e.print(callbackData);
                    e.flush();
                    e.close();
                }

                Article findArticle = (Article)this.articleService.findById(Article.class, id);
                if(findArticle == null) {
                    callbackData = BjuiJson.json("300", "文章不存在", "", "", "", "", "", "");
                    e.print(callbackData);
                    e.flush();
                    e.close();
                } else {
                    List articleCatelist = this.articleCateService.list("from ArticleCate where deleted=0");
                    String zNodes = "";

                    ArticleCate root;
                    for(Iterator var9 = articleCatelist.iterator(); var9.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
                        root = (ArticleCate)var9.next();
                    }

                    this.cfg = new Configuration();
                    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
                    HashMap root1 = new HashMap();
                    root1.put("article", findArticle);
                    root1.put("zNodes", zNodes);
                    FreemarkerUtils.freemarker(this.request, this.response, "articleEdit.ftl", this.cfg, root1);
                }
            } else {
                callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
                e.print(callbackData);
                e.flush();
                e.close();
            }
        } catch (JSONException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

    }

    public void update() {
        String callbackData = "";

        try {
            PrintWriter e = this.response.getWriter();
            if(this.article == null) {
                callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
            } else {
                Article findArticle = (Article)this.articleService.findById(Article.class, this.article.getId().intValue());
                this.article.setCreateDate(findArticle.getCreateDate());
                this.article.setVersion(findArticle.getVersion());
                this.article.setDeleted(findArticle.isDeleted());
                boolean result = this.articleService.saveOrUpdate(this.article);
                if(result) {
                    callbackData = BjuiJson.json("200", "修改成功", "", "", "", "true", "", "");
                } else {
                    callbackData = BjuiJson.json("300", "修改失败", "", "", "", "", "", "");
                }
            }

            e.print(callbackData);
            e.flush();
            e.close();
        } catch (JSONException var5) {
            var5.printStackTrace();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void delete() {
        String callbackData = "";
        String idStr = this.request.getParameter("id");

        try {
            PrintWriter e = this.response.getWriter();
            if(idStr != null && !"".equals(idStr)) {
                int id = 0;

                try {
                    id = Integer.parseInt(idStr);
                } catch (Exception var8) {
                    callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
                }

                Article findArticle = (Article)this.articleService.findById(Article.class, id);
                if(findArticle == null) {
                    callbackData = BjuiJson.json("300", "文章不存在", "", "", "", "", "", "");
                } else {
                    try {
                        boolean e1 = this.articleService.delete(findArticle);
                        if(e1) {
                            callbackData = BjuiJson.json("200", "删除成功", "", "", "", "", "", "");
                        } else {
                            callbackData = BjuiJson.json("300", "删除失败", "", "", "", "", "", "");
                        }
                    } catch (JSONException var7) {
                        var7.printStackTrace();
                    }
                }
            } else {
                callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
            }

            e.print(callbackData);
            e.flush();
            e.close();
        } catch (JSONException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    public void indexArticleList() {
        String idStr = this.request.getParameter("id");
        String key = this.request.getParameter("key");
        String pStr = this.request.getParameter("p");
        Config config = (Config)this.configService.findById(Config.class, 1);
        int p = 1;
        if(pStr != null && !"".equals(pStr)) {
            p = Integer.parseInt(pStr);
        }

        boolean count = false;
        String countHql = "select count(*) from Article where deleted=0 and status=1";
        if(idStr != null && !"".equals(idStr)) {
            countHql = countHql + " and articleCate.id=" + idStr;
        }

        if(key != null && !"".equals(key)) {
            try {
                key = new String(key.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException var13) {
                var13.printStackTrace();
            }

            countHql = countHql + " and title like \'%" + key + "%\'";
        }

        int count1 = this.articleService.getTotalCount(countHql, new Object[0]);
        PageModel pageModel = new PageModel();
        pageModel.setAllCount(count1);
        pageModel.setCurrentPage(p);
        List list = null;
        ArticleCate articleCate = null;
        String hql = "from Article where deleted=0 and status=1";
        if(idStr != null && !"".equals(idStr)) {
            hql = hql + " and articleCate.id=" + idStr;
            articleCate = (ArticleCate)this.articleCateService.findById(ArticleCate.class, Integer.parseInt(idStr));
        }

        if(key != null && !"".equals(key)) {
            hql = hql + " and title like \'%" + key + "%\'";
        }

        hql = hql + " order by id desc";
        list = this.articleService.list(hql, pageModel.getStart(), pageModel.getPageSize(), new Object[0]);
        this.cfg = new Configuration();
        this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/index");
        HashMap root = new HashMap();
        root.put("articleList", list);
        root.put("articleCate", articleCate);
        root.put("page", pageModel.getPageStr("list.do?id=" + idStr + "&p="));
        root.put("config", config);
        FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
    }

    public void indexArticle() {
        String callbackData = "";
        String idStr = this.request.getParameter("id");
        PrintWriter out = null;

        try {
            out = this.response.getWriter();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        if(idStr != null && !"".equals(idStr)) {
            int id = 0;

            try {
                id = Integer.parseInt(idStr);
            } catch (Exception var8) {
                callbackData = "参数错误";
                out.print(callbackData);
                out.flush();
                out.close();
            }

            Article findArticle = (Article)this.articleService.findById(Article.class, id);
            if(findArticle == null) {
                callbackData = "文章不存在";
                out.print(callbackData);
                out.flush();
                out.close();
            } else {
                Config config = (Config)this.configService.findById(Config.class, 1);
                this.cfg = new Configuration();
                this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/index");
                HashMap root = new HashMap();
                root.put("article", findArticle);
                root.put("config", config);
                FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
            }
        } else {
            callbackData = "参数错误";
            out.print(callbackData);
            out.flush();
            out.close();
        }

    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
