package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.Notes;
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
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE noteid = #{id}")
    public Notes findById(int id);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<Notes> findByUserId(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    public int insertNote(@Param("note") Notes note, @Param("userid") int userid);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    public int updateNote(Notes note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    public int deleteNote(int noteid);

}
