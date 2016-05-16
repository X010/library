package com.dssmp.library.controller;

import com.dssmp.library.model.Book;
import com.dssmp.library.model.Page;
import com.dssmp.library.model.User;
import com.dssmp.library.service.BookService;
import com.dssmp.library.service.UserService;
import com.dssmp.library.util.CONST;
import com.dssmp.library.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "login.action")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (CONST.HTTP_METHOD_POST.equals(request.getMethod())) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            boolean loginSuccess = userService.login(user);
            if (loginSuccess) {
                request.getSession().setAttribute(CONST.LOGIN_FLAG, user);
                model.setViewName("redirect:mainframe.action");
            } else {
                model.addObject("message", "登陆失败！用户名或密码不正确。");
                model.setViewName("login");
            }
            return model;
        }
        model.setViewName("login");
        return model;
    }


    @RequestMapping(value = "mainframe.action")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.addObject("books", this.bookService.countRow());
        model.addObject("users", this.userService.countRow());
        model.setViewName("main");
        return model;
    }


    @RequestMapping(value = "user_m.action")
    public ModelAndView user_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.queryAllUser();
        model.addObject("users", users);
        model.setViewName("user_m");
        return model;
    }

    @RequestMapping(value = "book_m.action")
    public ModelAndView book_m(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        int page = RequestUtil.getInt(request, "page", 1);
        int size = RequestUtil.getInt(request, "size", CONST.DEFAULT_SIZE);
        Page<Book> bookPage = bookService.queryBookByPage(page, size);
        model.addObject("books", bookPage.getData());
        model.addObject("pages", bookPage.getPages());
        model.setViewName("book_m");
        return model;
    }


    @RequestMapping(value = "book_ae.action")
    public ModelAndView book_ae(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.setViewName("book_ae");
        return model;
    }
}
