package com.dssmp.library.model;

import java.io.Serializable;
import java.util.Date;

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
public class Book implements Serializable{
    private int id;
    private String name;
    private String isbn;
    private String release_type;
    private String price;
    private Date release_time;
    private String author;
    private String book_size;
    private String pages;
    private String cover;
    private String recomment_word;
    private String author_describe;
    private String book_describe;
    private int hot;
    private int recommend;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getRelease_type() {
        return release_type;
    }

    public void setRelease_type(String release_type) {
        this.release_type = release_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_size() {
        return book_size;
    }

    public void setBook_size(String book_size) {
        this.book_size = book_size;
    }

    public String getRecomment_word() {
        return recomment_word;
    }

    public void setRecomment_word(String recomment_word) {
        this.recomment_word = recomment_word;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor_describe() {
        return author_describe;
    }

    public void setAuthor_describe(String author_describe) {
        this.author_describe = author_describe;
    }

    public String getBook_describe() {
        return book_describe;
    }

    public void setBook_describe(String book_describe) {
        this.book_describe = book_describe;
    }
}
