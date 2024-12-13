package com.cjl.test.mapper;

import com.cjl.test.pojo.Student;
import com.cjl.test.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select * from student where name=#{name}")
    Student findByName(String name);

    @Select("select * from student where id=#{id}")
    Student findById(String id);

    @Insert("insert into student(id,name,pwd)"+
    "values(#{id},#{name},#{password})")
    void add(String id,String name, String password);

    @Update("update student set pic=#{picurl} where id=#{id}")
    void updatepicture(String picurl,String id);

    @Update("update student set pwd=#{newpwd} where id=#{id}")
    void updatepwd(String newpwd, String id);
}
