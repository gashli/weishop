//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gashli.wshop.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gashli.wshop.entity.*;
import com.gashli.wshop.service.*;
import com.gashli.wshop.utils.*;
import freemarker.template.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("ordersAction")
@Scope("prototype")
public class OrdersAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Resource(
            name = "ordersService"
    )
    private IOrdersService<Orders> ordersService;
    @Resource(
            name = "userService"
    )
    private IUserService<User> userService;
    @Resource(
            name = "productService"
    )
    private IProductService<Product> productService;
    @Resource(
            name = "kamiService"
    )
    private IKamiService<Kami> kamiService;
    @Resource(
            name = "financialService"
    )
    private IFinancialService<Financial> financialService;
    @Resource(
            name = "commissionService"
    )
    private ICommissionService<Commission> commissionService;
    private Orders orders;
    private String ftlFileName;
    @Resource(
            name = "configService"
    )
    private IConfigService<Config> configService;

    public OrdersAction() {
    }

    public void list() {
        String key = this.request.getParameter("key");
        String countHql = "select count(*) from Orders where deleted=0";
        String hql = "from Orders where deleted=0";
        if(StringUtils.isNotEmpty(key)) {
            countHql = countHql + " and (user.name=\'" + key + "\' or no=\'" + key + "\' or productName=\'" + key + "\')";
            hql = hql + " and (user.name=\'" + key + "\' or no=\'" + key + "\' or productName=\'" + key + "\')";
        }

        hql = hql + " order by id desc";
        boolean count = false;
        int count1 = this.ordersService.getTotalCount(countHql, new Object[0]);
        this.page = new BjuiPage(this.pageCurrent, this.pageSize);
        this.page.setTotalCount(count1);
        this.cfg = new Configuration();
        this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
        List ordersList = this.ordersService.list(hql, this.page.getStart(), this.page.getPageSize(), new Object[0]);
        HashMap root = new HashMap();
        root.put("ordersList", ordersList);
        root.put("page", this.page);
        root.put("key", key);
        FreemarkerUtils.freemarker(this.request, this.response, this.ftlFileName, this.cfg, root);
    }

    public void add() {
        String pidStr = this.request.getParameter("pid");
        boolean pid = false;

        int pid1;
        try {
            pid1 = Integer.parseInt(pidStr);
        } catch (Exception var9) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "参数错误");

            try {
                this.request.getRequestDispatcher("cart.jsp").forward(this.request, this.response);
            } catch (ServletException var5) {
                var5.printStackTrace();
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            return;
        }

        Product findProduct = (Product)this.productService.findById(Product.class, pid1);
        if(findProduct == null) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "商品不存在");
        } else {
            this.request.setAttribute("status", "1");
            this.request.setAttribute("product", findProduct);
        }

        try {
            this.request.getRequestDispatcher("cart.jsp").forward(this.request, this.response);
        } catch (ServletException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public void save() {
        String pidStr = this.request.getParameter("pid");
        String numStr = this.request.getParameter("num");
        boolean pid = false;
        boolean num = true;

        int pid1;
        int num1;
        try {
            pid1 = Integer.parseInt(pidStr);
            num1 = Integer.parseInt(numStr);
        } catch (Exception var16) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "参数错误");

            try {
                this.request.getRequestDispatcher("orderAdd.jsp").forward(this.request, this.response);
            } catch (ServletException var13) {
                var13.printStackTrace();
            } catch (IOException var14) {
                var14.printStackTrace();
            }

            return;
        }

        Product findProduct = (Product)this.productService.findById(Product.class, pid1);
        if(findProduct == null) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "商品不存在");
        } else {
            HttpSession session = this.request.getSession();
            User loginUser = (User)session.getAttribute("loginUser");
            Orders newOrders = new Orders();
            newOrders.setProductId("" + findProduct.getId());
            newOrders.setProductName(findProduct.getTitle());
            newOrders.setProductNum(Integer.valueOf(num1));
            newOrders.setProductMoney(findProduct.getMoney());
            newOrders.setUser(loginUser);
            newOrders.setStatus(Integer.valueOf(0));
            newOrders.setMoney(Double.valueOf((double)num1 * findProduct.getMoney().doubleValue()));
            Random random = new Random();
            int n = random.nextInt(9999);
            n += 10000;
            String no = String.valueOf(System.currentTimeMillis()) + n;
            newOrders.setNo(no);
            newOrders.setCreateDate(new Date());
            newOrders.setDeleted(false);
            this.ordersService.saveOrUpdate(newOrders);

            try {
                this.response.sendRedirect("settle?no=" + no);
            } catch (IOException var15) {
                var15.printStackTrace();
            }
        }

    }

    public void settle() {
        String no = this.request.getParameter("no");
        Orders findOrders = this.ordersService.findByNo(no);
        if(findOrders == null) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "订单不存在");
        } else {
            this.request.setAttribute("orders", findOrders);

            try {
                this.request.getRequestDispatcher("settle.jsp").forward(this.request, this.response);
            } catch (ServletException var4) {
                var4.printStackTrace();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

    }

    public void pay() {
        String no = this.request.getParameter("no");
        Orders findOrders = this.ordersService.findByNo(no);
        HttpSession session = this.request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        User findUser = (User)this.userService.findById(User.class, loginUser.getId().intValue());
        JSONObject json = new JSONObject();
        if(findOrders == null) {
            json.put("status", "0");
            json.put("message", "订单不存在");
        } else if(findOrders.getUser().getId() != findUser.getId()) {
            json.put("status", "0");
            json.put("message", "没有权限");
        } else if(findUser.getBalance().doubleValue() < findOrders.getMoney().doubleValue()) {
            json.put("status", "0");
            json.put("message", "余额不足，请先充值");
        } else if(findOrders.getStatus().intValue() == 1) {
            json.put("status", "0");
            json.put("message", "该订单已付款，请不要重复提交支付");
        } else {
            List out = this.kamiService.list("from Kami where deleted=0 and status=0 and product.id=" + findOrders.getProductId(), 0, findOrders.getProductNum().intValue(), new Object[0]);
            if(out.size() < findOrders.getProductNum().intValue()) {
                json.put("status", "0");
                json.put("message", "库存不足，请联系管理员");
            } else {
                findUser.setBalance(Double.valueOf(findUser.getBalance().doubleValue() - findOrders.getMoney().doubleValue()));
                if(findUser.getStatus().intValue() == 0) {
                    findUser.setStatus(Integer.valueOf(1));
                    findUser.setStatusDate(new Date());
                }

                this.userService.saveOrUpdate(findUser);
                findOrders.setStatus(Integer.valueOf(1));
                String e = "卡密信息:<br/>";
                Date date = new Date();
                Iterator findConfig = out.iterator();

                while(findConfig.hasNext()) {
                    Kami financial = (Kami)findConfig.next();
                    e = e + "卡号:" + financial.getNo() + ",密码:" + financial.getPassword() + "<br/>";
                    financial.setSaleTime(date);
                    financial.setOrdersNo(findOrders.getNo());
                    financial.setStatus(Integer.valueOf(1));
                    this.kamiService.saveOrUpdate(financial);
                }

                findOrders.setSummary(e);
                findOrders.setPayDate(date);
                this.ordersService.saveOrUpdate(findOrders);
                Financial var25 = new Financial();
                var25.setType(Integer.valueOf(0));
                var25.setMoney(Double.valueOf(-findOrders.getMoney().doubleValue()));
                var25.setNo(String.valueOf(System.currentTimeMillis()));
                var25.setOperator(loginUser.getName());
                var25.setUser(findUser);
                var25.setCreateDate(new Date());
                var25.setDeleted(false);
                var25.setBalance(findUser.getBalance());
                var25.setPayment("余额付款");
                var25.setRemark("购买" + findOrders.getProductName());
                this.financialService.saveOrUpdate(var25);
                Config var24 = (Config)this.configService.findById(Config.class, 1);
                String levelNos = findUser.getSuperior();
                if(!StringUtils.isEmpty(levelNos)) {
                    String[] leverNoArr = levelNos.split(";");
                    int i = leverNoArr.length - 1;

                    for(int j = 1; i > 0; ++j) {
                        if(!StringUtils.isEmpty(leverNoArr[i])) {
                            User levelUser = this.userService.getUserByNo(leverNoArr[i]);
                            if(levelUser != null) {
                                double commissionRate = 0.0D;
                                if(j == 1) {
                                    commissionRate = var24.getFirstLevel().doubleValue();
                                } else if(j == 2) {
                                    commissionRate = var24.getSecondLevel().doubleValue();
                                } else if(j == 3) {
                                    commissionRate = var24.getThirdLevel().doubleValue();
                                }

                                double commissionNum = findOrders.getMoney().doubleValue() * commissionRate;
                                levelUser.setCommission(Double.valueOf(levelUser.getCommission().doubleValue() + commissionNum));
                                this.userService.saveOrUpdate(levelUser);
                                Commission commission = new Commission();
                                commission.setType(Integer.valueOf(1));
                                commission.setMoney(Double.valueOf(commissionNum));
                                commission.setNo(String.valueOf(System.currentTimeMillis()));
                                commission.setOperator(loginUser.getName());
                                commission.setUser(levelUser);
                                commission.setCreateDate(date);
                                commission.setDeleted(false);
                                commission.setLevel(Integer.valueOf(j));
                                commission.setRemark("第" + j + "级用户:编号【" + loginUser.getNo() + "】购买商品奖励");
                                this.commissionService.saveOrUpdate(commission);
                            }
                        }

                        --i;
                    }
                }

                json.put("status", "1");
                json.put("message", "付款成功");
                json.put("no", findOrders.getNo());
            }
        }

        PrintWriter var23 = null;

        try {
            var23 = this.response.getWriter();
        } catch (IOException var22) {
            var22.printStackTrace();
        }

        var23.print(json.toString());
        var23.flush();
        var23.close();
    }

    public void detail() {
        String no = this.request.getParameter("no");
        Orders findOrders = this.ordersService.findByNo(no);
        if(findOrders == null) {
            this.request.setAttribute("status", "0");
            this.request.setAttribute("message", "订单不存在");
        } else {
            HttpSession session = this.request.getSession();
            User loginUser = (User)session.getAttribute("loginUser");
            if(findOrders.getUser().getId() != loginUser.getId()) {
                this.request.setAttribute("status", "0");
                this.request.setAttribute("message", "没有权限");
            } else {
                this.request.setAttribute("orders", findOrders);

                try {
                    this.request.getRequestDispatcher("ordersDetail.jsp").forward(this.request, this.response);
                } catch (ServletException var6) {
                    var6.printStackTrace();
                } catch (IOException var7) {
                    var7.printStackTrace();
                }
            }
        }

    }

    public void indexList() {
        String pStr = this.request.getParameter("p");
        int p = 1;
        if(!StringUtils.isEmpty(pStr)) {
            p = Integer.parseInt(pStr);
        }

        String type = this.request.getParameter("type");
        HttpSession session = this.request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        String countHql = "select count(*) from Orders where deleted=0 and user.id=" + loginUser.getId();
        String hql = "from Orders where deleted=0 and user.id=" + loginUser.getId();
        if("0".equals(type) || "1".equals(type)) {
            countHql = countHql + " and status=" + type;
            hql = hql + " and status=" + type;
        }

        hql = hql + " order by id desc";
        boolean count = false;
        int count1 = this.ordersService.getTotalCount(countHql, new Object[0]);
        PageModel pageModel = new PageModel();
        pageModel.setAllCount(count1);
        pageModel.setCurrentPage(p);
        List ordersList = this.ordersService.list(hql, pageModel.getStart(), pageModel.getPageSize(), new Object[0]);
        JSONObject json = new JSONObject();
        if(ordersList.size() == 0) {
            json.put("status", "0");
            json.put("isNextPage", "0");
        } else {
            json.put("status", "1");
            if(ordersList.size() == pageModel.getPageSize()) {
                json.put("isNextPage", "1");
            } else {
                json.put("isNextPage", "0");
            }

            JSONArray out = (JSONArray)JSONArray.toJSON(ordersList);
            json.put("list", out);
        }

        PrintWriter out1 = null;

        try {
            out1 = this.response.getWriter();
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        out1.print(json);
        out1.flush();
        out1.close();
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

                Orders findorders = (Orders)this.ordersService.findById(Orders.class, e);
                if(findorders == null) {
                    callbackData = BjuiJson.json("300", "订单不存在", "", "", "", "", "", "");
                } else {
                    this.cfg = new Configuration();
                    this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
                    HashMap root = new HashMap();
                    root.put("orders", findorders);
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
            if(this.orders == null) {
                callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
            } else {
                Orders e = (Orders)this.ordersService.findById(Orders.class, this.orders.getId().intValue());
                this.orders.setCreateDate(e.getCreateDate());
                this.orders.setDeleted(e.isDeleted());
                this.orders.setVersion(e.getVersion());
                boolean result = this.ordersService.saveOrUpdate(this.orders);
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

                Orders findorders = (Orders)this.ordersService.findById(Orders.class, id);
                if(findorders == null) {
                    callbackData = BjuiJson.json("300", "订单不存在", "", "", "", "true", "", "");
                } else {
                    boolean result = this.ordersService.delete(findorders);
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

    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getFtlFileName() {
        return this.ftlFileName;
    }

    public void setFtlFileName(String ftlFileName) {
        this.ftlFileName = ftlFileName;
    }
}
