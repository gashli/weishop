//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.gashli.wshop.entity.ProductCate;
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
import com.gashli.wshop.service.IProductCateService;
import com.gashli.wshop.utils.BjuiJson;
import com.gashli.wshop.utils.FreemarkerUtils;

@Controller("productCateAction")
@Scope("prototype")
public class ProductCateAction extends BaseAction {
  private static final long serialVersionUID = 1L;
  @Resource(
          name = "productCateService"
  )
  private IProductCateService<ProductCate> productCateService;
  private ProductCate productCate;

  public ProductCateAction() {
  }

  public void list() {
    List list = this.productCateService.list("from ProductCate where deleted=0");
    String zNodes = "";

    ProductCate root;
    for(Iterator var4 = list.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "[ID:" + root.getId() + "]</li>") {
      root = (ProductCate)var4.next();
    }

    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root1 = new HashMap();
    root1.put("zNodes", zNodes);
    root1.put("list", list);
    FreemarkerUtils.freemarker(this.request, this.response, "productCateList.ftl", this.cfg, root1);
  }

  public void add() {
    List list = this.productCateService.list("from ProductCate where deleted=0");
    String zNodes = "<li data-id=\'0\' data-pid=\'0\' data-tabid=\'0\'>顶级分类</li>";

    ProductCate root;
    for(Iterator var4 = list.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
      root = (ProductCate)var4.next();
    }

    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root1 = new HashMap();
    root1.put("zNodes", zNodes);
    root1.put("list", list);
    FreemarkerUtils.freemarker(this.request, this.response, "productCateAdd.ftl", this.cfg, root1);
  }

  public void save() {
    String callbackData = "";
    this.productCate.setDeleted(false);
    this.productCate.setCreateDate(new Date());
    boolean result = this.productCateService.saveOrUpdate(this.productCate);

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

      ProductCate findProductCate = (ProductCate)this.productCateService.findById(ProductCate.class, id);
      if(findProductCate == null) {
        callbackData = "分类不存在";
      } else {
        callbackData = findProductCate.getName();
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

        ProductCate findProductCate = (ProductCate)this.productCateService.findById(ProductCate.class, e);
        if(findProductCate == null) {
          callbackData = BjuiJson.json("300", "分类不存在", "", "", "", "", "", "");
          out.print(callbackData);
          out.flush();
          out.close();
        } else {
          List list = this.productCateService.list("from ProductCate where deleted=0");
          String zNodes = "<li data-id=\'0\' data-pid=\'0\' data-tabid=\'0\'>顶级分类</li>";

          ProductCate fatherName;
          for(Iterator root = list.iterator(); root.hasNext(); zNodes = zNodes + "<li data-id=\'" + fatherName.getId() + "\' data-pid=\'" + fatherName.getFatherId() + "\' data-tabid=\'" + fatherName.getId() + "\'>" + fatherName.getName() + "</li>") {
            fatherName = (ProductCate)root.next();
          }

          String fatherName1 = "";
          if(findProductCate.getFatherId() != 0) {
            ProductCate root1 = (ProductCate)this.productCateService.findById(ProductCate.class, findProductCate.getFatherId());
            if(root1 != null) {
              fatherName1 = ((ProductCate)this.productCateService.findById(ProductCate.class, findProductCate.getFatherId())).getName();
            } else {
              fatherName1 = "上级分类不存在";
            }
          } else {
            fatherName1 = "顶级分类";
          }

          this.cfg = new Configuration();
          this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
          HashMap root2 = new HashMap();
          root2.put("productCate", findProductCate);
          root2.put("zNodes", zNodes);
          root2.put("fatherName", fatherName1);
          FreemarkerUtils.freemarker(this.request, this.response, "productCateEdit.ftl", this.cfg, root2);
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
      if(this.productCate == null) {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      } else if(this.productCate.getFatherId() == this.productCate.getId().intValue()) {
        callbackData = BjuiJson.json("300", "上级分类不能选择当前修改的分类", "", "", "", "", "", "");
      } else {
        ProductCate e = (ProductCate)this.productCateService.findById(ProductCate.class, this.productCate.getId().intValue());
        e.setFatherId(this.productCate.getFatherId());
        e.setName(this.productCate.getName());
        boolean result = this.productCateService.saveOrUpdate(e);
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

        ProductCate findProductCate = (ProductCate)this.productCateService.findById(ProductCate.class, e);
        if(findProductCate == null) {
          callbackData = BjuiJson.json("300", "分类不存在", "", "", "", "", "", "");
        } else {
          List sanList = this.productCateService.listByFatherId(e);
          this.log.info(sanList);
          if(sanList.size() != 0) {
            callbackData = BjuiJson.json("300", "该分类存在下级分类，请先删除下级分类", "", "", "", "", "", "");
          } else {
            boolean result = this.productCateService.delete(findProductCate);
            if(result) {
              callbackData = BjuiJson.json("200", "删除成功", "ProductCateList", "", "", "true", "", "");
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

  public ProductCate getProductCate() {
    return this.productCate;
  }

  public void setProductCate(ProductCate productCate) {
    this.productCate = productCate;
  }
}
