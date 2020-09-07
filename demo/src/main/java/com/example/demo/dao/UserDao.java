package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface UserDao {
    // 自定义sql
    @Select("select * from user where id=#{id}")
    List<User> selectUserListById(@Param("id") Long id);

    @Select("select * from user where sex=#{sex}")
    List<User> selectUserListBySex(@Param("sex") String sex);

    @Select("select mobile from user where mobile=#{mobile}")
    List<User> selectUserListByMobile(@Param("mobile") String mobile);

    @InsertProvider(type=UserDaoProvider.class, method="insertSql")
    @Options(useGeneratedKeys=true, keyProperty="user.id", keyColumn="id")
    int insert(@Param("user") User user);

    // 动态sql
    class UserDaoProvider {
        public String insertSql(User user) {
            return new SQL() {
                {
                    INSERT_INTO("user");
                    if(user.getNickname() != null) {
                        VALUES("nickname","#{user.nickname}");
                    }
                    if(user.getMobile() != null) {
                        VALUES("mobile", "#{user.mobile}");
                    }
                    if(user.getPassword() != null) {
                        VALUES("password", "#{user.password}");
                    }
                    if(user.getSex() != null) {
                        VALUES("sex", "#{user.sex}");
                    }
                    if(user.getRole() != null) {
                        VALUES("role", "#{user.role}");
                    }
                }
            }.toString();
        }
    }
}