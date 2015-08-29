 package com.gashli.wshop.filter;


 import com.gashli.wshop.entity.User;

 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

 public class UserLoginFilter
   implements Filter
 {
   public void destroy()
   {
   }

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
     throws IOException, ServletException
   {
     HttpServletRequest request = (HttpServletRequest)req;
     HttpServletResponse response = (HttpServletResponse)res;
     HttpSession session = request.getSession();
     User loginUser = null;
     if (!"".equals(session.getAttribute("loginUser"))) {
       loginUser = (User)session.getAttribute("loginUser");
     }
     if ((loginUser == null) || (loginUser.getId() == null))
       response.sendRedirect(request.getContextPath() + "/login.jsp");
     else
       chain.doFilter(request, response);
   }

   public void init(FilterConfig arg0)
     throws ServletException
   {
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.UserLoginFilter
 * JD-Core Version:    0.6.2
 */