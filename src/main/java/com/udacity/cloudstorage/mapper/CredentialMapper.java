package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface CredentialMapper {@Select("SELECT * FROM CREDENTIALS")
List<Credentials> findAll();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{id}")
    public Credentials findById(int id);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    public List<Credentials> findByUserId(int userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userid})")
    public int insertCredentials(@Param("credential") Credentials credential, @Param("userid") int userid);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    public int updateCredentials(Credentials credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    public int deleteCredentials(int credentialid);

}
