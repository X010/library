package com.dssmp.library.controller;

import com.dssmp.library.model.User;
import com.dssmp.library.service.UserService;
import com.dssmp.library.util.CONST;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by b3st9u on 16/4/6.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.action")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password)) {
                User user = this.userService.login(username, password);
                if (user != null) {
                    request.getSession().setAttribute(CONST.LOGIN_FLAG, user);
                    model.setViewName("redirect:mainframe.action");
                    return model;
                }
            }
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "addUser.action")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String message = "";
        int status = userService.addUser(user);
        if (status == -1) {
            message = "用户名已存在。";
        } else if (status == 0) {
            message = "新增用户失败.";
        }
        if (!Strings.isNullOrEmpty(message)) {
            model.addObject("message", message);
        }
        //回到userlist页面
        List<User> users = userService.queryAllUser();
        model.addObject("users", users);
        model.addObject("username", username);
        model.setViewName("user_m");
        return model;
    }

    @RequestMapping(value = "delUser.action")
    public ModelAndView delUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        boolean isSuccess = userService.delete(Integer.parseInt(id));
        model.addObject("isSuccess", isSuccess);
        model.setViewName("redirect:/user_m.action");
        return model;
    }

    @RequestMapping(value = "logout.action")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        request.getSession().removeAttribute(CONST.LOGIN_FLAG);
        model.setViewName("login");
        return model;
    }
}
