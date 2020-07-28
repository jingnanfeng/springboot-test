package cn.com.nanfeng.boot.web.controller;

import cn.com.nanfeng.boot.model.po.Student;
import cn.com.nanfeng.boot.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 19:43
 */
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/getStudent/{studentId}")
    public Student getStudentById(@PathVariable("studentId")String studentId){
        Student student = studentService.getStudentById(studentId);
        return student;
    }

    @PostMapping("/addStudent")
    public String addStudent(Student student){
        int res = studentService.addStudent(student);
        if (res > 0){
            return "添加成功";
        }else {
            throw new RuntimeException("添加失败");
        }
    }

    @PostMapping("/updateStudent")
    public String updateStudent(Student student){
        int res = studentService.updateStudent(student);
        if (res > 0){
            return "修改成功";
        }else {
            throw new RuntimeException("修改失败");
        }
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId")String studentId){
        int res = studentService.deleteStudent(studentId);
        return "删除成功";
    }

}
