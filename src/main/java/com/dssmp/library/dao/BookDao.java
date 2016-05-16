package com.dssmp.library.dao;

import com.dssmp.library.model.Book;
import org.apache.ibatis.annotations.*;

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
public interface BookDao {

    @Insert("insert into t_book(name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word," +
            "author_describe,book_describe,hot,recommend) values(#{name},#{isbn},#{release_type},#{price},#{release_time},#{author}," +
            "#{book_size},#{cover},#{recomment_word},#{author_describe},#{book_describe},#{hot},#{recommend})")
    public int addbook(Book book);

    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book order by id desc")
    public List<Book> queryAllBook();

    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book order by id desc limit #{start},#{size} ")
    public List<Book> queryBookByPage(@Param("start") int start, @Param("size") int size);

    /**
     * 统计记录数
     *
     * @return
     */
    @Select("select count(1)  from t_book")
    public int countBook();

    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book where name=#{name} order by id desc")
    public List<Book> queryBookByNanme(@Param(value = "name") String name);


    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book where isbn=#{isbn} limit 1")
    public Book queryBookByIsbn(@Param(value = "isbn") String isbn);


    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book where id=#{id} limit 1")
    public Book queryBookById(@Param(value = "id") int id);

    @Delete("delete from t_book where id=#{id}")
    public int deleteBook(@Param(value = "id") int id);

    @Update("update t_book set name=#{name},isbn=#{isbn},release_type=#{release_type},price=#{price},release_time=#{release_time}," +
            "author=#{author},book_size=#{book_size},cover=#{cover},recomment_word=#{recomment_word},author_describe=#{author_describe}," +
            "book_describe=#{book_describe},hot=#{hot},recommend=#{recommend} where id=#{id}")
    public int updateBook(Book book);


    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book where name like #{keyword} or author like #{keyword} or isbn=#{keyword} order by id desc")
    public List<Book> queryBookByKeyword(@Param(value = "keyword") String keyword);

    @Select("select distinct release_type from t_book")
    public List<String> queryAllType();


    @Select("select id,name,isbn,release_type,price,release_time,author,book_size,cover,recomment_word,author_describe," +
            "book_describe,hot,recommend from t_book where release_type=#{release_type}")
    public List<Book> queryBooksByType(String release_type);


}
