package com.gashli.wshop.action;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.gashli.wshop.entity.ArticleCate;
import freemarker.template.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gashli.wshop.service.IArticleCateService;
import com.gashli.wshop.utils.BjuiJson;
import com.gashli.wshop.utils.FreemarkerUtils;

@Controller("articleCateAction")
@Scope("prototype")
public class ArticleCateAction extends BaseAction {
  private static final long serialVersionUID = 1L;
  @Resource(
          name = "articleCateService"
  )
  private IArticleCateService<ArticleCate> articleCateService;
  private ArticleCate articleCate;

  public ArticleCateAction() {
  }

  public void list() {
    List list = this.articleCateService.list("from ArticleCate where deleted=0");
    String zNodes = "";

    ArticleCate root;
    for(Iterator var4 = list.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "[ID:" + root.getId() + "]</li>") {
      root = (ArticleCate)var4.next();
    }

    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root1 = new HashMap();
    root1.put("zNodes", zNodes);
    root1.put("list", list);
    FreemarkerUtils.freemarker(this.request, this.response, "articleCateList.ftl", this.cfg, root1);
  }

  public void add() {
    List list = this.articleCateService.list("from ArticleCate where deleted=0");
    String zNodes = "<li data-id=\'0\' data-pid=\'0\' data-tabid=\'0\'>顶级栏目</li>";

    ArticleCate root;
    for(Iterator var4 = list.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
      root = (ArticleCate)var4.next();
    }

    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root1 = new HashMap();
    root1.put("zNodes", zNodes);
    root1.put("list", list);
    FreemarkerUtils.freemarker(this.request, this.response, "articleCateAdd.ftl", this.cfg, root1);
  }

  public void save() {
    String callbackData = "";
    this.articleCate.setDeleted(false);
    this.articleCate.setCreateDate(new Date());
    boolean result = this.articleCateService.saveOrUpdate(this.articleCate);

    try {
      if(result) {
        callbackData = BjuiJson.json("200", "添加成功", "", "", "", "true", "", "");
      } else {
        callbackData = BjuiJson.json("300", "添加失败", "", "", "", "", "", "");
      }
    } catch (JSONException var6) {
      var6.printStackTrace();
    }

    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var5) {
      var5.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
  }

  public void getNameById() {
    String idStr = this.request.getParameter("id");
    String callbackData = "";
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var7) {
      var7.printStackTrace();
    }

    if(idStr != null && !"".equals(idStr)) {
      int id = 0;

      try {
        id = Integer.parseInt(idStr);
      } catch (Exception var6) {
        callbackData = "参数错误";
      }

      ArticleCate findArticleCate = (ArticleCate)this.articleCateService.findById(ArticleCate.class, id);
      if(findArticleCate == null) {
        callbackData = "栏目不存在";
      } else {
        callbackData = findArticleCate.getName();
      }
    } else {
      callbackData = "参数错误";
    }

    this.log.info(callbackData);
    out.print(callbackData);
    out.flush();
    out.close();
  }

  public void info() {
    String idStr = this.request.getParameter("id");
    String callbackData = "";
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var11) {
      var11.printStackTrace();
    }

    try {
      if(idStr != null && !"".equals(idStr)) {
        int e = 0;

        try {
          e = Integer.parseInt(idStr);
        } catch (Exception var10) {
          callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
          out.print(callbackData);
          out.flush();
          out.close();
        }

        ArticleCate findArticleCate = (ArticleCate)this.articleCateService.findById(ArticleCate.class, e);
        if(findArticleCate == null) {
          callbackData = BjuiJson.json("300", "栏目不存在", "", "", "", "", "", "");
          out.print(callbackData);
          out.flush();
          out.close();
        } else {
          List list = this.articleCateService.list("from ArticleCate where deleted=0");
          String zNodes = "<li data-id=\'0\' data-pid=\'0\' data-tabid=\'0\'>顶级栏目</li>";

          ArticleCate fatherName;
          for(Iterator root = list.iterator(); root.hasNext(); zNodes = zNodes + "<li data-id=\'" + fatherName.getId() + "\' data-pid=\'" + fatherName.getFatherId() + "\' data-tabid=\'" + fatherName.getId() + "\'>" + fatherName.getName() + "</li>") {
            fatherName = (ArticleCate)root.next();
          }

          String fatherName1 = "";
          if(findArticleCate.getFatherId() != 0) {
            ArticleCate root1 = (ArticleCate)this.articleCateService.findById(ArticleCate.class, findArticleCate.getFatherId());
            if(root1 != null) {
              fatherName1 = ((ArticleCate)this.articleCateService.findById(ArticleCate.class, findArticleCate.getFatherId())).getName();
            } else {
              fatherName1 = "上级栏目不存在";
            }
          } else {
            fatherName1 = "顶级栏目";
          }

          this.cfg = new Configuration();
          this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
          HashMap root2 = new HashMap();
          root2.put("articleCate", findArticleCate);
          root2.put("zNodes", zNodes);
          root2.put("fatherName", fatherName1);
          FreemarkerUtils.freemarker(this.request, this.response, "articleCateEdit.ftl", this.cfg, root2);
        }
      } else {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
        out.print(callbackData);
        out.flush();
        out.close();
      }
    } catch (JSONException var12) {
      var12.printStackTrace();
    }

  }

  public void update() {
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var6) {
      var6.printStackTrace();
    }

    String callbackData = "";

    try {
      if(this.articleCate == null) {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      } else if(this.articleCate.getFatherId() == this.articleCate.getId().intValue()) {
        callbackData = BjuiJson.json("300", "上级栏目不能选择当前修改的栏目", "", "", "", "", "", "");
      } else {
        ArticleCate e = (ArticleCate)this.articleCateService.findById(ArticleCate.class, this.articleCate.getId().intValue());
        e.setFatherId(this.articleCate.getFatherId());
        e.setName(this.articleCate.getName());
        boolean result = this.articleCateService.saveOrUpdate(e);
        if(result) {
          callbackData = BjuiJson.json("200", "修改成功", "", "", "", "true", "", "");
        } else {
          callbackData = BjuiJson.json("300", "修改失败", "", "", "", "", "", "");
        }
      }
    } catch (JSONException var5) {
      var5.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
  }

  public void delete() {
    String idStr = this.request.getParameter("id");
    String callbackData = "";
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
      if(idStr != null && !"".equals(idStr)) {
        int e = 0;

        try {
          e = Integer.parseInt(idStr);
        } catch (Exception var8) {
          callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
        }

        ArticleCate findArticleCate = (ArticleCate)this.articleCateService.findById(ArticleCate.class, e);
        if(findArticleCate == null) {
          callbackData = BjuiJson.json("300", "栏目不存在", "", "", "", "", "", "");
        } else {
          List sanList = this.articleCateService.listByFatherId(e);
          this.log.info(sanList);
          if(sanList.size() != 0) {
            callbackData = BjuiJson.json("300", "该栏目存在下级栏目，请先删除下级栏目", "", "", "", "", "", "");
          } else {
            boolean result = this.articleCateService.delete(findArticleCate);
            if(result) {
              callbackData = BjuiJson.json("200", "删除成功", "articleCateList", "", "", "true", "", "");
            } else {
              callbackData = BjuiJson.json("300", "删除失败", "", "", "", "", "", "");
            }
          }
        }
      } else {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      }
    } catch (IOException var9) {
      var9.printStackTrace();
    } catch (JSONException var10) {
      var10.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
  }

  public ArticleCate getArticleCate() {
    return this.articleCate;
  }

  public void setArticleCate(ArticleCate articleCate) {
    this.articleCate = articleCate;
  }
}
