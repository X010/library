package com.dssmp.library.controller;

import com.dssmp.library.model.Book;
import com.dssmp.library.service.BookService;
import com.dssmp.library.util.FileUtil;
import com.dssmp.library.util.JsonTools;
import com.dssmp.library.util.RequestUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by andrew on 16/4/9.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "addBook.action")
    public ModelAndView addBook(@RequestParam("cover") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String photo = null;
        String name = request.getParameter("name");
        String isbn = request.getParameter("code");
        String release_type = request.getParameter("ctype");
        String price = request.getParameter("price");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date release_time = null;
        try {
            release_time = sdf.parse(request.getParameter("time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String author = request.getParameter("author");
        String pages = request.getParameter("pages");//印数
//        int size =RequestUtil.getInt(request,"size",0);
        String book_size=request.getParameter("book_size");//开本
        String recomment_word = request.getParameter("keyword");
        String author_describe = request.getParameter("authorDesc");
        String book_describe = request.getParameter("desc");
        int hot=RequestUtil.getInt(request, "hot", 0);
        int recommend=RequestUtil.getInt(request,"recommend",0);

        Book book = new Book();
        book.setName(name);
        book.setIsbn(isbn);
        book.setRelease_type(release_type);
        book.setPrice(price);
        book.setRelease_time(release_time);
        book.setAuthor(author);
        book.setHot(hot);
        book.setRecommend(recommend);
        book.setPages(pages);
        book.setBook_size(book_size);
        //设置Photo地址
        if (file != null) {
            photo = FileUtil.saveFileUtil(file);
            if (!Strings.isNullOrEmpty(photo))
                book.setCover(photo);
        }
        book.setAuthor_describe(author_describe);
        book.setRecomment_word(recomment_word);
        book.setBook_describe(book_describe);

        boolean isSuccess = bookService.addBook(book);
        model.addObject("isSuccess", isSuccess);
        model.setViewName("redirect:/book_m.action");

        return model;
    }

    @RequestMapping(value = "delBook.action")
    public ModelAndView delBook(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        boolean isSuccess = bookService.deleteBook(Integer.parseInt(id));
        model.addObject("isSuccess", isSuccess);
        model.setViewName("redirect:/book_m.action");
        return model;
    }

    @RequestMapping(value = "modifyBook.action")
    public ModelAndView modifyBook(@RequestParam("cover") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String photo = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String isbn = request.getParameter("code");
        String release_type = request.getParameter("ctype");
        String price = request.getParameter("price");
        String pages = request.getParameter("pages");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date release_time = null;
        try {
            release_time = sdf.parse(request.getParameter("time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String author = request.getParameter("author");
        String book_size = request.getParameter("book_size");
        String recomment_word = request.getParameter("keyword");
        String author_describe = request.getParameter("authorDesc");
        String book_describe = request.getParameter("desc");

        int hot=RequestUtil.getInt(request, "hot", 0);
        int recommend=RequestUtil.getInt(request, "recommend", 0);

        Book book = new Book();
        book.setName(name);
        book.setIsbn(isbn);
        book.setRelease_type(release_type);
        book.setPrice(price);
        book.setRelease_time(release_time);
        book.setHot(hot);
        book.setRecommend(recommend);
        //设置Photo地址
        if (file != null) {
            photo = FileUtil.saveFileUtil(file);
            if (!Strings.isNullOrEmpty(photo))
                book.setCover(photo);
        }
        book.setAuthor(author);
        book.setBook_size(book_size);
        book.setAuthor_describe(author_describe);
        book.setRecomment_word(recomment_word);
        book.setBook_describe(book_describe);
        book.setPages(pages);
        book.setId(id);
        boolean isSuccess = bookService.modifyBook(book);
        model.addObject("isSuccess", isSuccess);
        model.setViewName("redirect:/book_m.action");
        return model;
    }

    @RequestMapping(value = "querybyname.action")
    public ModelAndView queryByName(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String name = request.getParameter("name");
        List<Book> books = bookService.queryBookByName(name);
        model.addObject("books", books);
        model.setViewName("book_m");
        return model;
    }

    @RequestMapping(value = "querybyisbn.action")
    public ModelAndView queryByIsbn(HttpServletRequest request, HttpServletRequest response) {
        ModelAndView model = new ModelAndView();
        String isbn = request.getParameter("isbn");
        Book book = bookService.queryBookByIsbn(isbn);
        model.addObject("book", book);
        model.setViewName("book_ae");
        //获取所有分类
        List<String> types = this.bookService.queryAllReleaseTypes();
        if (types != null) {
            model.addObject("types", JsonTools.jsonSer(types));
        }
        return model;
    }

}
