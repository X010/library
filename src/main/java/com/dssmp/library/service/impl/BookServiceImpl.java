package com.dssmp.library.service.impl;

import com.dssmp.library.dao.BookDao;
import com.dssmp.library.model.Book;
import com.dssmp.library.model.Page;
import com.dssmp.library.service.BookService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andrew on 16/4/9.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    @Override
    public boolean addBook(Book book) {
        return bookDao.addbook(book) > 0;
    }

    @Override
    public Page<Book> queryBookByPage(int page, int size,String orderby,String sort) {
        Preconditions.checkArgument(page > 0);
        Preconditions.checkArgument(size > 0);
        Page<Book> bookPage = new Page<>();
        int start = (page - 1) * size;
        List<Book> books = this.bookDao.queryBookByPage(start, size,orderby,sort);
        int count = this.bookDao.countBook();
        bookPage.setData(books);
        bookPage.setRow(count);
        if (count % size == 0) bookPage.setPages(count / size);
        else bookPage.setPages(count / size + 1);
        return bookPage;
    }

    @Override
    public List<Book> queryAllBook() {
        return bookDao.queryAllBook();
    }

    @Override
    public List<Book> queryBookByName(String name) {
        return bookDao.queryBookByNanme(name);
    }

    @Override
    public Book queryBookByIsbn(String isbn) {
        return bookDao.queryBookByIsbn(isbn);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> searchBookByKeyword(String keyword,int start,int size) {
        keyword="%"+keyword+"%";
        return bookDao.queryBookByKeyword(keyword,start,size);
    }

    @Override
    public List<Book> queryBooksByType(String release_type,int page,int size) {
        return bookDao.queryBooksByType(release_type,page,size);
    }

    @Override
    public List<String> queryAllReleaseTypes() {
        return bookDao.queryAllType();
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id) > 0;
    }

    @Override
    public boolean modifyBook(Book book) {
        return bookDao.updateBook(book) > 0;
    }

    @Override
    public int countRow() {
        return this.bookDao.countBook();
    }
}
