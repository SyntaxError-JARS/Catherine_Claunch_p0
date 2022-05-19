package com.revature.banking.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking.daos.AccountDao;
import com.revature.banking.daos.UserDao;
import com.revature.banking.models.User;
import com.revature.banking.models.Account;
import com.revature.banking.services.AccountServices;
import com.revature.banking.services.UserServices;
import com.revature.banking.web.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Make our single ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Instantiate all Daos first
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();

        // Instantiate and intialize the services with their dao dependency
        UserServices userServices = new UserServices(userDao);
        AccountServices accountServices = new AccountServices(accountDao);


        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/account/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
