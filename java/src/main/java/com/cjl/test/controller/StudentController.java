package com.cjl.test.controller;

import com.cjl.test.pojo.Result;
import com.cjl.test.pojo.Student;
import com.cjl.test.service.StudentService;
import com.cjl.test.utils.AliOddUtil;
import com.cjl.test.utils.JwtUtil;
import com.cjl.test.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/register")
    public Result reigister(String id,String name, String password) {
        Student stu = studentService.findByName(name);
        if (stu == null) {
            studentService.reigister(id,name, password);
            return Result.success();
        }
        else{
            return Result.error("用户已被占用");
        }
    }
    @PostMapping("/login")
    public Result<String> login(String id, String password) {
        Student stu = studentService.findById(id);
        if (stu == null) {
            return Result.error("学号不存在");
        }
        else{
            if(stu.getPwd().equals(password)){
                //生成Token
                Map<String,Object> claims = new HashMap<String,Object>();
                claims.put("id",stu.getId());
                claims.put("name",stu.getName());
                String token=JwtUtil.genToken(claims);
                return Result.success(token);
            }
            else {
                return Result.error("密码错误");
            }
        }
    }
    @GetMapping("/studentinfo")
    public Result<Student> studentinfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Student stu = studentService.findByName(name);
        if (stu.getSex().equals("M")) {
            stu.setSex("男");
        }
        else {
            stu.setSex("女");
        }
        if (stu.getSchool().equals("GP")) {
            stu.setSchool("学校1");
        }
        else {
            stu.setSchool("学校2");
        }
        return Result.success(stu);
    }
    @PatchMapping("/updatepicture")
    public Result updatePicture(@RequestParam String picurl) {
        studentService.updatepicture(picurl);
        return Result.success();
    }
    @PatchMapping("/updatepwd")
    public Result updatePwd(@RequestBody Map<String,String>params) {
        String oldpwd = params.get("old_pwd");
        String newpwd = params.get("new_pwd");
        String repwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldpwd)||!StringUtils.hasLength(newpwd)||!StringUtils.hasLength(repwd)){
            return Result.error("缺少必要参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Student stu=studentService.findByName(name);
        if(!stu.getPwd().equals(oldpwd)){
            return  Result.error("原密码不正确");
        }
        if(!repwd.equals(newpwd)){
            return Result.error("两次填写的新密码不一样");
        }
        studentService.updatepwd(newpwd);
        return Result.success();
    }
    @GetMapping("/portrait")
    public Result<String> protrait() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getportrait(id);
        return Result.success(url);
    }
    @GetMapping("/radar")
    public Result<String> radar() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getradar(id);
        return Result.success(url);
    }
    @GetMapping("/warn")
    public Result<String> warn() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getwarn(id);
        return Result.success(url);
    }
    @GetMapping("/portrait_life")
    public Result<String> protrait_life() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getportrait_life(id);
        return Result.success(url);
    }
    @GetMapping("/radar_life")
    public Result<String> radar_life() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getradar_life(id);
        return Result.success(url);
    }
    @GetMapping("/warn_life")
    public Result<String> warn_life() throws Exception {
        Map<String,Object> map = ThreadLocalUtil.get();
        String id = (String) map.get("id");
        String url = AliOddUtil.getwarn_life(id);
        return Result.success(url);
    }
}
