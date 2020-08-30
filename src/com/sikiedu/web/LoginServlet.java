package com.sikiedu.web;

import com.sikiedu.domain.User;
import com.sikiedu.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID=1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserService();
        //传递数据，判断数据库中是否有user
        boolean success = false;

        success = userService.findUser(user);

        if(success)
        {
            //有,存在(index.html),转发到index.html
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
        else{
            request.setAttribute("error","用户名或密码错误");
            //没有,不存在(login.html),转发到login.html
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }
}
