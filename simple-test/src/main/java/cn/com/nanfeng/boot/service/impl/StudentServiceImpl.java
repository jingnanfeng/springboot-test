package cn.com.nanfeng.boot.service.impl;

import cn.com.nanfeng.boot.mapper.StudentMapper;
import cn.com.nanfeng.boot.model.po.Student;
import cn.com.nanfeng.boot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 19:35
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;



    @Override
    public Student getStudentById(String studentId) {
        Student student = studentMapper.selectByPrimaryKey(studentId);
        return student;
    }

    @Override
    public int addStudent(Student student) {
        int res = studentMapper.insertSelective(student);
        return res;
    }

    @Override
    public int updateStudent(Student student) {
        int res = studentMapper.updateByPrimaryKeySelective(student);
        return res;
    }

    @Override
    public int deleteStudent(String studentId) {
        //查询student是否存在
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null){
            throw new RuntimeException("学生不存在");
        }
        int res = studentMapper.deleteByPrimaryKey(studentId);
        return res;
    }
}
