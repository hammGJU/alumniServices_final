/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.daos.annotations.StdDAO;
import edu.gju.alumni.alumniapp.Idaos.StudentDAO;
import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@Stateless
public class StudentService {

    @Inject
    @StdDAO
    private StudentDAO studentDAO;

    public StudentService() {
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        students = studentDAO.getAllStudents();
        return students;
    }

    public List<Email> getStudentEmails(String studentId) throws SQLException {
        return studentDAO.getStudentEmail(studentId);
    }

    public Student getStudentById(int id) throws SQLException {
        Student student = studentDAO.getStudentById(id);
        return student;
    }

    public int addStudent(String student) throws SQLException {
        int result = studentDAO.addStudent(student);
        return result;
    }

    public int deleteStudent(int studentId) throws SQLException {
        int result = studentDAO.deleteStudent(studentId);
        return result;
    }

}
