package com.du.controller;

import com.alibaba.fastjson.JSONObject;
import com.du.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserSelectServlet" , urlPatterns = "/UserSelectServlet")
public class UserSelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正 编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错

        // 2. 接收  2个参数 page limit
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        String real_name = req.getParameter("real_name");
        System.out.println("real_name = " + real_name);
        String type = req.getParameter("type");
        System.out.println("type = " + type);
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // 把收到的参数封装到  map 中
        Map map1 = new HashMap();
        map1.put("page",page);
        map1.put("limit",limit);
        map1.put("real_name",real_name);
        map1.put("type",type);
        map1.put("username",username);




        // 3 .调用service
        UserService userService = new UserService();
        Map map = userService.selectAllByParam(map1);
        // 4. 把 map 变为json
        String s = JSONObject.toJSONString(map);
        // 5. 使用 流 输出
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.close();
    }
}
