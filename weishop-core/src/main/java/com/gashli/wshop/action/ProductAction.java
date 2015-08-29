//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.gashli.wshop.entity.Config;
import com.gashli.wshop.entity.Product;
import com.gashli.wshop.entity.ProductCate;
import com.gashli.wshop.utils.*;
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
import com.gashli.wshop.service.IConfigService;
import com.gashli.wshop.service.IProductCateService;
import com.gashli.wshop.service.IProductService;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction {
  private static final long serialVersionUID = 1L;
  @Resource(
          name = "productService"
  )
  private IProductService<Product> productService;
  @Resource(
          name = "productCateService"
  )
  private IProductCateService<ProductCate> productCateService;
  @Resource(
          name = "configService"
  )
  private IConfigService<Config> configService;
  private Product product;

  public ProductAction() {
  }

  public void list() {
    String key = this.request.getParameter("key");
    boolean count = false;
    int count1;
    if(!"".equals(key) && key != null) {
      count1 = this.productService.getTotalCount("from Product where title like \'%" + key + "%\' and deleted=0", new Object[0]);
    } else {
      count1 = this.productService.getTotalCount("select count(*) from Product where deleted=0", new Object[0]);
      key = "";
    }

    this.page = new BjuiPage(this.pageCurrent, this.pageSize);
    this.page.setTotalCount(count1);
    List list = this.productService.list("from Product where deleted=0 order by id desc", this.page.getStart(), this.page.getPageSize(), new Object[0]);
    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root = new HashMap();
    root.put("list", list);
    root.put("page", this.page);
    FreemarkerUtils.freemarker(this.request, this.response, "productList.ftl", this.cfg, root);
  }

  public void add() {
    List productCatelist = this.productCateService.list("from ProductCate where deleted=0");
    String zNodes = "";

    ProductCate root;
    for(Iterator var4 = productCatelist.iterator(); var4.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
      root = (ProductCate)var4.next();
    }

    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root1 = new HashMap();
    root1.put("zNodes", zNodes);
    FreemarkerUtils.freemarker(this.request, this.response, "productAdd.ftl", this.cfg, root1);
  }

  public void save() {
    String callbackData = "";

    try {
      if(this.product.getProductCate().getId().intValue() == 0) {
        callbackData = BjuiJson.json("300", "请选择栏目", "", "", "", "", "", "");
      } else if("".equals(this.product.getContent())) {
        callbackData = BjuiJson.json("300", "请输入内容", "", "", "", "", "", "");
      } else {
        if(StringUtils.isEmpty(this.product.getPicture())) {
          this.product.setPicture("images/nopicture.jpg");
        }

        this.product.setDeleted(false);
        this.product.setCreateDate(new Date());
        boolean out = this.productService.saveOrUpdate(this.product);
        if(out) {
          callbackData = BjuiJson.json("200", "添加成功", "", "", "", "true", "", "");
        } else {
          callbackData = BjuiJson.json("300", "添加失败", "", "", "", "", "", "");
        }
      }
    } catch (Exception var5) {
      var5.printStackTrace();
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

        Product findProduct = (Product)this.productService.findById(Product.class, id);
        if(findProduct == null) {
          callbackData = BjuiJson.json("300", "产品不存在", "", "", "", "", "", "");
          e.print(callbackData);
          e.flush();
          e.close();
        } else {
          List productCatelist = this.productCateService.list("from ProductCate where deleted=0");
          String zNodes = "";

          ProductCate root;
          for(Iterator var9 = productCatelist.iterator(); var9.hasNext(); zNodes = zNodes + "<li data-id=\'" + root.getId() + "\' data-pid=\'" + root.getFatherId() + "\' data-tabid=\'" + root.getId() + "\'>" + root.getName() + "</li>") {
            root = (ProductCate)var9.next();
          }

          this.cfg = new Configuration();
          this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
          HashMap root1 = new HashMap();
          root1.put("product", findProduct);
          root1.put("zNodes", zNodes);
          FreemarkerUtils.freemarker(this.request, this.response, "productEdit.ftl", this.cfg, root1);
        }
      } else {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
        e.print(callbackData);
        e.flush();
        e.close();
      }
    } catch (IOException var11) {
      var11.printStackTrace();
    } catch (JSONException var12) {
      var12.printStackTrace();
    }

  }

  public void update() {
    String callbackData = "";

    try {
      PrintWriter e = this.response.getWriter();
      if(this.product == null) {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      } else {
        Product findProduct = (Product)this.productService.findById(Product.class, this.product.getId().intValue());
        findProduct.setProductCate(this.product.getProductCate());
        findProduct.setPicture(this.product.getPicture());
        findProduct.setTitle(this.product.getTitle());
        findProduct.setContent(this.product.getContent());
        boolean result = this.productService.saveOrUpdate(findProduct);
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

        Product findProduct = (Product)this.productService.findById(Product.class, id);
        if(findProduct == null) {
          callbackData = BjuiJson.json("300", "产品不存在", "", "", "", "", "", "");
        } else {
          try {
            boolean e1 = this.productService.delete(findProduct);
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

  public void indexProductList() {
    String idStr = this.request.getParameter("id");
    String key = this.request.getParameter("key");
    String pStr = this.request.getParameter("p");
    int p = 1;
    if(!StringUtils.isEmpty(pStr)) {
      p = Integer.parseInt(pStr);
    }

    boolean count = false;
    String countHql = "select count(*) from Product where deleted=0";
    String hql = "from Product where deleted=0";
    if(!StringUtils.isEmpty(idStr)) {
      countHql = countHql + " and productCate.id=" + idStr;
      hql = hql + " and productCate.id=" + idStr;
    }

    if(!StringUtils.isEmpty(key)) {
      try {
        key = new String(key.getBytes("ISO-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException var14) {
        var14.printStackTrace();
      }

      countHql = countHql + " and title like \'%" + key + "%\'";
      hql = hql + " and title like \'%" + key + "%\'";
    }

    hql = hql + " order by id desc";
    int count1 = this.productService.getTotalCount(countHql, new Object[0]);
    PageModel pageModel = new PageModel();
    pageModel.setAllCount(count1);
    pageModel.setCurrentPage(p);
    List list = this.productService.list(hql, pageModel.getStart(), pageModel.getPageSize(), new Object[0]);
    JSONObject json = new JSONObject();
    if(list.size() == 0) {
      json.put("status", "0");
      json.put("isNextPage", "0");
    } else {
      json.put("status", "1");
      if(list.size() == pageModel.getPageSize()) {
        json.put("isNextPage", "1");
      } else {
        json.put("isNextPage", "0");
      }

      JSONArray out = (JSONArray)JSONArray.toJSON(list);
      json.put("list", out);
    }

    PrintWriter out1 = null;

    try {
      out1 = this.response.getWriter();
    } catch (IOException var13) {
      var13.printStackTrace();
    }

    out1.print(json);
    out1.flush();
    out1.close();
  }

  public void indexProduct() {
    String idStr = this.request.getParameter("id");
    JSONObject json = new JSONObject();
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var8) {
      var8.printStackTrace();
    }

    if(idStr != null && !"".equals(idStr)) {
      int id = 0;

      try {
        id = Integer.parseInt(idStr);
      } catch (Exception var7) {
        json.put("status", "0");
        json.put("message", "参数错误");
      }

      Product findproduct = (Product)this.productService.findById(Product.class, id);
      if(findproduct == null) {
        json.put("status", "0");
        json.put("message", "产品不存在");
      } else {
        JSONObject jsonObj = (JSONObject)JSONObject.toJSON(findproduct);
        json.put("status", "1");
        json.put("product", jsonObj);
      }
    } else {
      json.put("status", "0");
      json.put("message", "参数错误");
    }

    out.print(json);
    out.flush();
    out.close();
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
