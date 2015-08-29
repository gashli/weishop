 package com.gashli.wshop.listener;



 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;

 import com.gashli.wshop.entity.Config;
 import org.springframework.web.context.WebApplicationContext;
 import org.springframework.web.context.support.WebApplicationContextUtils;
 import com.gashli.wshop.service.IConfigService;

 public class InitListener
   implements ServletContextListener
 {
   public void contextDestroyed(ServletContextEvent arg0)
   {
   }

   public void contextInitialized(ServletContextEvent arg0)
   {
     WebApplicationContext rwp = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());

     IConfigService configService = (IConfigService)rwp.getBean("configService");
     Config config = (Config)configService.findById(Config.class, 1);
     arg0.getServletContext().setAttribute("config", config);
   }
 }

/* Location:           /Users/gaoshiliang/myworkspace/WeFenxiao_v1.0.0/Fenxiao/WEB-INF/classes/
 * Qualified Name:     com.lxinet.fenxiao.InitListener
 * JD-Core Version:    0.6.2
 */