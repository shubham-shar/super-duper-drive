package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    List<Files> findAll();

    @Select("SELECT * FROM FILES WHERE fileid = #{id}")
    public Files findById(int id);

    @Select("SELECT * FROM FILES WHERE filename = #{name}")
    public Files findByFileName(String name);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    public List<Files> findByUserId(int userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    public int insertFile(@Param("file") Files file, @Param("userid") int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    public int deleteFile(int fileid);
}
