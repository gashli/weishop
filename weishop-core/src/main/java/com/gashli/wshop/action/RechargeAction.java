//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.gashli.wshop.entity.Config;
import com.gashli.wshop.entity.Recharge;
import com.gashli.wshop.entity.User;
import freemarker.template.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gashli.wshop.service.IConfigService;
import com.gashli.wshop.service.IRechargeService;
import com.gashli.wshop.service.IUserService;
import com.gashli.wshop.utils.BjuiJson;
import com.gashli.wshop.utils.BjuiPage;
import com.gashli.wshop.utils.FreemarkerUtils;
import com.gashli.wshop.utils.StringUtils;

@Controller("rechargeAction")
@Scope("prototype")
public class RechargeAction extends BaseAction {
  private static final long serialVersionUID = 1L;
  @Resource(
          name = "rechargeService"
  )
  private IRechargeService<Recharge> rechargeService;
  @Resource(
          name = "userService"
  )
  private IUserService<User> userService;
  private Recharge recharge;
  private String ftlFileName;
  @Resource(
          name = "configService"
  )
  private IConfigService<Config> configService;

  public RechargeAction() {
  }

  public void list() {
    String key = this.request.getParameter("key");
    String countHql = "select count(*) from Recharge where deleted=0";
    String hql = "from Recharge where deleted=0";
    if(StringUtils.isNotEmpty(key)) {
      countHql = countHql + " and (user.name=\'" + key + "\' or no=\'" + key + "\')";
      hql = hql + " and (user.name=\'" + key + "\' or no=\'" + key + "\')";
    }

    hql = hql + " order by id desc";
    boolean count = false;
    int count1 = this.rechargeService.getTotalCount(countHql, new Object[0]);
    this.page = new BjuiPage(this.pageCurrent, this.pageSize);
    this.page.setTotalCount(count1);
    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    List rechargeList = this.rechargeService.list(hql, this.page.getStart(), this.page.getPageSize(), new Object[0]);
    HashMap root = new HashMap();
    root.put("rechargeList", rechargeList);
    root.put("page", this.page);
    root.put("key", key);
    FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
  }

  String formatString(String text) {
    return text == null?"":text;
  }

  public void save() {
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var7) {
      var7.printStackTrace();
    }

    String callbackData = "";
    if(this.recharge == null) {
      callbackData = "<script>alert(\'参数错误\');window.location.href=\'javascript:history.go(-1);\'</script>";
    } else {
      Random random = new Random();
      int n = random.nextInt(9999);
      n += 10000;
      String no = String.valueOf(System.currentTimeMillis()) + n;
      this.recharge.setNo(no);
      this.recharge.setCreateDate(new Date());
      boolean result = this.rechargeService.saveOrUpdate(this.recharge);
      if(result) {
        callbackData = "<script>alert(\'提交成功\');window.location.href=\'recharge.jsp\'</script>";
      } else {
        callbackData = "<script>alert(\'提交失败，请重试\');window.location.href=\'javascript:history.go(-1);\'</script>";
      }
    }

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
    } catch (IOException var8) {
      var8.printStackTrace();
    }

    try {
      if(idStr != null && !"".equals(idStr)) {
        int e = 0;

        try {
          e = Integer.parseInt(idStr);
        } catch (Exception var7) {
          callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
        }

        Recharge findrecharge = (Recharge)this.rechargeService.findById(Recharge.class, e);
        if(findrecharge == null) {
          callbackData = BjuiJson.json("300", "充值不存在", "", "", "", "", "", "");
        } else {
          this.cfg = new Configuration();
          this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
          HashMap root = new HashMap();
          root.put("recharge", findrecharge);
          FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
        }
      } else {
        callbackData = BjuiJson.json("300", "参数不能为空", "", "", "", "", "", "");
      }
    } catch (JSONException var9) {
      var9.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
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
      if(this.recharge == null) {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      } else {
        Recharge e = (Recharge)this.rechargeService.findById(Recharge.class, this.recharge.getId().intValue());
        this.recharge.setCreateDate(e.getCreateDate());
        this.recharge.setDeleted(e.isDeleted());
        this.recharge.setVersion(e.getVersion());
        boolean result = this.rechargeService.saveOrUpdate(this.recharge);
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
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var8) {
      var8.printStackTrace();
    }

    String callbackData = "";

    try {
      String e = this.request.getParameter("id");
      if(e != null && !"".equals(e)) {
        int id = 0;

        try {
          id = Integer.parseInt(e);
        } catch (Exception var7) {
          callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
        }

        Recharge findrecharge = (Recharge)this.rechargeService.findById(Recharge.class, id);
        if(findrecharge == null) {
          callbackData = BjuiJson.json("300", "充值不存在", "", "", "", "true", "", "");
        } else {
          boolean result = this.rechargeService.delete(findrecharge);
          if(result) {
            callbackData = BjuiJson.json("200", "删除成功", "", "", "", "", "", "");
          } else {
            callbackData = BjuiJson.json("300", "删除失败", "", "", "", "", "", "");
          }
        }
      } else {
        callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
      }
    } catch (JSONException var9) {
      var9.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
  }

  public Recharge getRecharge() {
    return this.recharge;
  }

  public void setRecharge(Recharge recharge) {
    this.recharge = recharge;
  }

  public String getFtlFileName() {
    return this.ftlFileName;
  }

  public void setFtlFileName(String ftlFileName) {
    this.ftlFileName = ftlFileName;
  }
}
