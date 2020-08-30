package com.sikiedu.service;

import com.sikiedu.dao.UserDao;
import com.sikiedu.domain.User;

import java.sql.SQLException;

public class UserService {

    public boolean findUser(User user){

        UserDao userDao=new UserDao();
        User temp=null;
        try {
            temp = userDao.findUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp==null?false:true;
    }
}
