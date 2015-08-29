//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.alibaba.fastjson.JSONObject;
import com.gashli.wshop.entity.Admin;
import com.gashli.wshop.entity.Financial;
import com.gashli.wshop.entity.RechargeCard;
import com.gashli.wshop.entity.User;
import freemarker.template.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gashli.wshop.service.IAdminService;
import com.gashli.wshop.service.IFinancialService;
import com.gashli.wshop.service.IRechargeCardService;
import com.gashli.wshop.service.IUserService;
import com.gashli.wshop.utils.BjuiJson;
import com.gashli.wshop.utils.BjuiPage;
import com.gashli.wshop.utils.FreemarkerUtils;
import com.gashli.wshop.utils.StringUtils;

@Controller("rechargeCardAction")
@Scope("prototype")
public class RechargeCardAction extends BaseAction {
  private static final long serialVersionUID = 1L;
  @Resource(
          name = "rechargeCardService"
  )
  private IRechargeCardService<RechargeCard> rechargeCardService;
  private RechargeCard rechargeCard;
  @Resource(
          name = "adminService"
  )
  private IAdminService<Admin> adminService;
  @Resource(
          name = "userService"
  )
  private IUserService<User> userService;
  @Resource(
          name = "financialService"
  )
  private IFinancialService<Financial> financialService;

  public RechargeCardAction() {
  }

  public void list() {
    String no = this.request.getParameter("no");
    String status = this.request.getParameter("status");
    boolean count = false;
    String countHql = "select count(*) from RechargeCard where deleted=0";
    if(StringUtils.isNotEmpty(no)) {
      countHql = countHql + " and no like \'%" + no + "%\'";
    }

    if(StringUtils.isNotEmpty(status)) {
      countHql = countHql + " and status=" + status;
    }

    int count1 = this.rechargeCardService.getTotalCount(countHql, new Object[0]);
    this.page = new BjuiPage(this.pageCurrent, this.pageSize);
    this.page.setTotalCount(count1);
    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    String hql = "from RechargeCard where deleted=0";
    if(StringUtils.isNotEmpty(no)) {
      hql = hql + " and no like \'%" + no + "%\'";
    }

    if(StringUtils.isNotEmpty(status)) {
      hql = hql + " and status=" + status;
    }

    hql = hql + " order by id desc";
    List rechargeCardList = this.rechargeCardService.list(hql, this.page.getStart(), this.page.getPageSize(), new Object[0]);
    HashMap root = new HashMap();
    root.put("rechargeCardList", rechargeCardList);
    root.put("page", this.page);
    root.put("no", no);
    FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardList.ftl", this.cfg, root);
  }

  public void add() {
    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root = new HashMap();
    FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardAdd.ftl", this.cfg, root);
  }

  public void save() {
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var17) {
      var17.printStackTrace();
    }

    String numStr = this.request.getParameter("num");
    String moneyStr = this.request.getParameter("money");
    int num = Integer.parseInt(numStr);
    double money = Double.parseDouble(moneyStr);
    String callbackData = "";

    try {
      Date e = new Date();
      Random random = new Random();
      String b1 = "";
      String b2 = "";

      for(int i = 0; i < num; ++i) {
        int a1 = random.nextInt(9999);
        a1 += 10000;
        b1 = String.valueOf(a1);
        int a2 = random.nextInt(9999);
        a2 += 10000;
        b2 = String.valueOf(a2);
        String no = b1 + b2;
        RechargeCard rechargeCard = new RechargeCard();
        rechargeCard.setDeleted(false);
        rechargeCard.setCreateDate(e);
        rechargeCard.setMoney(money);
        rechargeCard.setStatus(Integer.valueOf(0));
        rechargeCard.setNo(no);
        this.rechargeCardService.saveOrUpdate(rechargeCard);
      }

      callbackData = BjuiJson.json("200", "成功生成" + num + "张充值卡", "", "", "", "true", "", "");
    } catch (JSONException var18) {
      var18.printStackTrace();
    }

    out.print(callbackData);
    out.flush();
    out.close();
  }

  public void info() {
    String no = this.request.getParameter("no");
    this.cfg = new Configuration();
    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
    HashMap root = new HashMap();
    root.put("no", no);
    FreemarkerUtils.freemarker(this.request, this.response, "rechargeCardChongzhi.ftl", this.cfg, root);
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

        RechargeCard findRechargeCard = (RechargeCard)this.rechargeCardService.findById(RechargeCard.class, id);
        if(findRechargeCard == null) {
          callbackData = BjuiJson.json("300", "充值卡不存在", "", "", "", "", "", "");
        } else {
          boolean result = this.rechargeCardService.delete(findRechargeCard);
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

  public void userUseRechargeCard() {
    PrintWriter out = null;

    try {
      out = this.response.getWriter();
    } catch (IOException var10) {
      var10.printStackTrace();
    }

    HttpSession session = this.request.getSession();
    User loginUser = (User)session.getAttribute("loginUser");
    User findUser = (User)this.userService.findById(User.class, loginUser.getId().intValue());
    String no = this.request.getParameter("no");
    RechargeCard findRechargeCard = this.rechargeCardService.getByNo(no);
    JSONObject json = new JSONObject();
    if(findRechargeCard == null) {
      json.put("status", "0");
      json.put("message", "充值卡不存在");
    } else if(findRechargeCard.getStatus().intValue() == 1) {
      json.put("status", "0");
      json.put("message", "充值卡已被使用");
    } else {
      Financial financial = new Financial();
      financial.setType(Integer.valueOf(1));
      financial.setMoney(Double.valueOf(findRechargeCard.getMoney()));
      financial.setNo(String.valueOf(System.currentTimeMillis()));
      financial.setOperator(loginUser.getName());
      financial.setUser(findUser);
      financial.setCreateDate(new Date());
      financial.setDeleted(false);
      financial.setBalance(findUser.getCommission());
      financial.setPayment("充值卡充值");
      financial.setRemark("充值卡充值,充值卡卡号:" + findRechargeCard.getNo());
      this.financialService.saveOrUpdate(financial);
      findUser.setBalance(Double.valueOf(findUser.getBalance().doubleValue() + findRechargeCard.getMoney()));
      this.userService.saveOrUpdate(findUser);
      findRechargeCard.setStatus(Integer.valueOf(1));
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      findRechargeCard.setUseTime(sdf.format(new Date()));
      findRechargeCard.setUseUserNo(findUser.getNo());
      this.rechargeCardService.saveOrUpdate(findRechargeCard);
      json.put("status", "1");
      json.put("message", "充值成功，充值金额:" + findRechargeCard.getMoney() + "元");
    }

    out.print(json.toString());
    out.flush();
    out.close();
  }

  public RechargeCard getRechargeCard() {
    return this.rechargeCard;
  }

  public void setRechargeCard(RechargeCard rechargeCard) {
    this.rechargeCard = rechargeCard;
  }
}
