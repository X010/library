package com.dssmp.library.dao;

import com.dssmp.library.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface UserDao {

    @Insert("insert into t_user(username,password) values (#{username},#{password})")
    public int addUser(User user);

    @Select("select count(*) from t_user where username=#{username} and password=#{password}")
    public int login(User user);

    /**
     * 根据用户名与密码获取用户
     *
     * @param user
     * @return
     */
    @Select("select * from t_user where username=#{username} and password=#{password} limit 1")
    public User findUserByUserNameAndPassword(User user);

    @Select("select count(*) from t_user where username=#{username}")
    public int checkUsername(@Param(value = "username") String username);

    @Select("select id,username,password from t_user")
    public List<User> queryAllUser();

    @Delete("delete from t_user where id=#{id}")
    public int deleteUser(@Param(value = "id") int id);

    /**
     * 统计记录条数
     *
     * @return
     */
    @Select("select count(1) from t_user")
    public int countRow();
}
