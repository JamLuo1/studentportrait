package com.cjl.test.service;

import com.cjl.test.pojo.Student;

public interface StudentService {
     Student findByName(String name);
     Student findById(String id);
     void reigister(String id,String name, String password);
     void updatepicture(String picurl);
     void updatepwd(String newpwd);
}
