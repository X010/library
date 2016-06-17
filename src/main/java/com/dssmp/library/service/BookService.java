package com.dssmp.library.service;

import com.dssmp.library.model.Book;
import com.dssmp.library.model.Page;

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
public interface BookService {
    /**
     * 新增图书
     *
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 分页读取
     *
     * @param page
     * @param size
     * @return
     */
    Page<Book> queryBookByPage(int page, int size,String orderby,String order);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> queryAllBook();

    /**
     * 按名字查询图书
     *
     * @param name
     * @return
     */
    List<Book> queryBookByName(String name);

    /**
     * 按isbn查询图书
     *
     * @param isbn
     * @return
     */
    Book queryBookByIsbn(String isbn);

    /**
     * 根据图书ID查询
     * @param id
     * @return
     */
    Book queryBookById(int id);

    /**
     * 根据关键字搜索图书
     * @param keyword
     * @return
     */
    List<Book> searchBookByKeyword(String keyword,int start,int size);

    /**
     * 获取分类下的图书
     * @param release_type
     * @return
     */
    List<Book> queryBooksByType(String release_type,int start,int size);

    /**
     * 获取所有分类
     * @return
     */
    List<String> queryAllReleaseTypes();

    /**
     * 删除图书
     *
     * @param id
     * @return
     */
    boolean deleteBook(int id);

    /**
     * 修改图书
     *
     * @param book
     * @return
     */
    boolean modifyBook(Book book);

    /**
     * 统计记录数
     * @return
     */
    int countRow();


}
