package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<User> findAll();

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    public User findById(int id);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public User findByUsername(String username);

    @Insert("INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
    public int insertUser(User user);

    @Update("UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    public int updateUser(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    public int deleteUser(String username);
}
