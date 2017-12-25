/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daos;

import edu.gju.alumni.alumniapp.models.Student;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface StudentDAO extends ConnectionDAO{

    public List<Student> getAllStudents() throws SQLException;

    public Student getStudentById(int id) throws SQLException;

    public int addStudent(Student student) throws SQLException;

    public int editStudent(Student student) throws SQLException;

    public int deleteStudent(int studentId) throws SQLException;

}