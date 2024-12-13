package com.cjl.test.service.Impl;

import com.cjl.test.mapper.StudentMapper;
import com.cjl.test.pojo.Student;
import com.cjl.test.service.StudentService;
import com.cjl.test.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student findByName(String name) {
        Student stu = studentMapper.findByName(name);
        return stu;
    }
    @Override
    public Student findById(String id) {
        Student stu = studentMapper.findById(id);
        return stu;
    }

    @Override
    public void reigister(String id,String name, String password) {
        studentMapper.add(id,name,password);
    }

    @Override
    public void updatepicture(String picurl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id =(String) map.get("id");
        studentMapper.updatepicture(picurl,id);
    }

    @Override
    public void updatepwd(String newpwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id =(String) map.get("id");
        studentMapper.updatepwd(newpwd,id);
    }

}
