package com.university.servlet;



import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    /**
     * Initialize log4j when the application is being started
     */

	@Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        String prefix =  ctx.getRealPath("/");
        String file = "WEB-INF"+System.getProperty("file.separator")+"classes"+System.getProperty("file.separator")+"log4j.properties";

        if(file != null) {
        	PropertyConfigurator.configure(prefix+file);
          	System.out.println("Log4J Logging started for application: " + prefix+file);
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // do nothing
    }
}
