package cn.com.nanfeng.boot.service;

import cn.com.nanfeng.boot.model.po.Student;
import org.apache.logging.log4j.util.Strings;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 19:35
 */
public interface StudentService {
    /**
     * 根据id查询学生信息
     * @param studentId
     * @return
     */
    Student getStudentById(String studentId);

    /**
     * 新增学生
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 更新学生
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除学生
     * @param studentId
     * @return
     */
    int deleteStudent(String studentId);

}
