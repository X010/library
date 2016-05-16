package com.dssmp.library.controller;

import com.dssmp.library.model.APIBookModel;
import com.dssmp.library.model.Book;
import com.dssmp.library.model.Page;
import com.dssmp.library.service.BookService;
import com.dssmp.library.util.JsonTools;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by b3st9u on 16/4/13.
 */

@Controller
@RequestMapping("/api/book")
public class APIController {

    @Autowired
    private BookService bookService;


    @ResponseBody
    @RequestMapping(value = "getBookDetail.action",method = {RequestMethod.GET, RequestMethod.POST})
    public String queryBookById(HttpServletRequest request, HttpServletRequest response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String callback=request.getParameter("callback");
        Book book=bookService.queryBookById(id);
        APIBookModel<Book> apiBookModel=new APIBookModel<Book>();
        if(book!=null) {
            apiBookModel.setStatus(200);
            apiBookModel.setData(book);
        }else{
            apiBookModel.setStatus(404);
            apiBookModel.setMsg("未查询到图书");
        }
        String result=JsonTools.jsonSer(apiBookModel);
        if(Strings.isNullOrEmpty(callback)){
            result = String.format("%s(%s)", callback, result);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "searchBook.action",method = {RequestMethod.GET, RequestMethod.POST})
    public String searchBook(HttpServletRequest request, HttpServletRequest response) {
        String keyword=request.getParameter("keyword");
        String callback=request.getParameter("callback");
        List<Book> books=bookService.searchBookByKeyword(keyword);
        APIBookModel<List<Book>> apiBookModel=new APIBookModel<List<Book>>();
        if(!books.isEmpty()) {
            apiBookModel.setStatus(200);
            apiBookModel.setData(books);
        }else{
            apiBookModel.setStatus(404);
            apiBookModel.setMsg("未查询到图书");
        }
       String result=JsonTools.jsonSer(apiBookModel);
        if(!Strings.isNullOrEmpty(callback)){
            result = String.format("%s(%s)", callback, result);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "queryBooksByType.action",method = {RequestMethod.GET, RequestMethod.POST})
    public String queryBookByType(HttpServletRequest request, HttpServletRequest response) {
        String release_type=request.getParameter("release_type");
        String callback=request.getParameter("callback");
        List<Book> books=bookService.queryBooksByType(release_type);
        APIBookModel<List<Book>> apiBookModel=new APIBookModel<List<Book>>();
        if(!books.isEmpty()) {
            apiBookModel.setStatus(200);
            apiBookModel.setData(books);
        }else{
            apiBookModel.setStatus(404);
            apiBookModel.setMsg("未查询到图书");
        }
        String result=JsonTools.jsonSer(apiBookModel);
        if(!Strings.isNullOrEmpty(callback)){
            result = String.format("%s(%s)", callback, result);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "getBookTypes.action",method = {RequestMethod.GET, RequestMethod.POST})
    public String getBookTypes(HttpServletRequest request, HttpServletRequest response) {
        String callback=request.getParameter("callback");
        List<String> types=bookService.queryAllReleaseTypes();
        APIBookModel<List<String>> apiBookModel=new APIBookModel<List<String>>();
        if(!types.isEmpty()) {
            apiBookModel.setStatus(200);
            apiBookModel.setData(types);
        }else{
            apiBookModel.setStatus(404);
            apiBookModel.setMsg("没有分类");
        }
        String result=JsonTools.jsonSer(apiBookModel);
        if(!Strings.isNullOrEmpty(callback)){
            result = String.format("%s(%s)", callback, result);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "queryBooks.action",method = {RequestMethod.GET, RequestMethod.POST})
    public String queryBooks(HttpServletRequest request, HttpServletRequest response) {
        int size=Integer.parseInt(request.getParameter("size"));
        int page=Integer.parseInt(request.getParameter("page"));
        String callback=request.getParameter("callback");
        Page<Book> books=bookService.queryBookByPage(page, size);
        APIBookModel<List<Book>> apiBookModel=new APIBookModel<List<Book>>();
        if(books!=null&&books.getRow()>0) {
            apiBookModel.setStatus(200);
            apiBookModel.setData(books.getData());
        }else{
            apiBookModel.setStatus(404);
            apiBookModel.setMsg("没有分类");
        }
        String result=JsonTools.jsonSer(apiBookModel);
        if(!Strings.isNullOrEmpty(callback)){
            result = String.format("%s(%s)", callback, result);
        }
        return result;
    }



}
