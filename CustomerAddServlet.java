package com.du.controller;

import com.alibaba.fastjson.JSONObject;
import com.du.bean.Customer;
import com.du.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "CustomerAddServlet",urlPatterns = "/CustomerAddServlet")
public class CustomerAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正 编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //接收前端传过来的参数
        String create_time = req.getParameter("create_time");
        String cust_birth = req.getParameter("cust_birth");
        String cust_company = req.getParameter("cust_company");
        String cust_desc = req.getParameter("cust_desc");
        String cust_name = req.getParameter("cust_name");
        String cust_phone = req.getParameter("cust_phone");
        String cust_position = req.getParameter("cust_position");
        String cust_sex = req.getParameter("cust_sex");
        String user_id = req.getParameter("user_id");
        System.out.println("user_id = " + user_id);
        
        Customer customer = new Customer();
        customer.setCust_name(cust_name);
        customer.setCust_company(cust_company);
        customer.setCust_position(cust_position);
        customer.setCust_phone(cust_phone);
        customer.setCust_birth(cust_birth);
        customer.setCust_sex(Integer.parseInt(cust_sex));
        customer.setCust_desc(cust_desc);
        customer.setUser_id(Integer.parseInt(user_id));
        customer.setCreate_time(create_time);
        customer.setModify_time(create_time);

        CustomerService customerService = new CustomerService();
        Map map = customerService.insertCustomer(customer);
        System.out.println("map = " + map);
        
        PrintWriter writer = resp.getWriter();
        String s = JSONObject.toJSONString(map);
        writer.println(s);//
        writer.close();
    }
}
