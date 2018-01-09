/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.StudentDAO;
import edu.gju.alumni.alumniapp.daos.annotations.StdDAO;
import edu.gju.alumni.alumniapp.beans.UserSessionBean;
import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentJob;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.Remove;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(StudentDAO.class)
@Stateless(name = "studentDAOImpl")
@StdDAO
public class StudentDAOImpl extends ConnectionDAOImpl implements StudentDAO, Serializable {

   
    private Connection connection;
//    @Resource(lookup = "jdbc/Alumni_GJU")
//    private DataSource dataSource;
//    @Inject
    private UserSessionBean sessionBean;

    public StudentDAOImpl() throws Exception {
        
    }

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            if (connection == null) {
                connection = super.getConnection();
            } else {

            }

        } catch (Exception ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> allStudents = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_STUDENTS_DSA_NOT_CLEARED.toString());
        ResultSet rs = ps.executeQuery();
//        ps= connection.prepareStatement(AlumniServEnum.GET_STUDENT_JOB.toString());
//        ResultSet rs2= ps.executeQuery();
        while (rs.next()) {
//            StudentJob sJob= new StudentJob();
//            while(rs2.next()){
//                
//            }
            Student s = PopulateModels.populateStudent(rs);
            allStudents.add(s);
        }

        rs.close();
        ps.close();
        return allStudents;
    }

    @Override
    public List<Email> getStudentEmail(String studentId) throws SQLException {
        List<Email> emails = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_STUDENT_EMAIL.toString());
        ps.setString(1, studentId);
        ResultSet rs = ps.executeQuery();
        emails = PopulateModels.populateEmail(rs);
        return emails;

    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_STUDENT_BY_ID.toString());
        ps.setString(1, Integer.toString(id));
        ResultSet rs = ps.executeQuery();
        Student student = new Student();
        while (rs.next()) {
            student = PopulateModels.populateStudent(rs);
        }
        rs.close();
        ps.close();
        return student;

    }

    @Override
    public int addStudent(String student) throws SQLException {
        
         int result = 0;
        boolean committed = true;
        PreparedStatement ps;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(AlumniServEnum.INSERT_USER_GROUPS.toString());
            ps.setString(1, student);
            ps.setString(2, "Alumni");
            result = ps.executeUpdate();

          ps = connection.prepareStatement(AlumniServEnum.INSERT_STUDENT.toString());
          ps.setString(1, student);
          result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.INSERT_EMPLOYEE_USER.toString());
            ps.setString(1, student);
            ps.setString(2, student);
            ps.setString(3, "123456");
            result = ps.executeUpdate();
            ps.close();
            connection.commit();
        } catch (SQLException ex) {
            committed = false;
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!committed) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
        
//        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.INSERT_STUDENT.toString());
        
      
        
        
//        ps.setString(1, student.getFirstName());
//        ps.setString(2, student.getLastName());
//        ps.setDate(3, new Date(student.getDateOfBirth().getTime()));
//        ps.setString(4, student.getNationality());
//        ps.setString(5, student.getSchool().getId());
//        ps.setString(6, student.getDepartment().getId());
//        ps.setInt(7, student.getDegree().getId());
//        ps.setDouble(8, student.getGpa());
//        ps.setString(9, student.getGender().getId());
//        ps.setString(10, student.getStatus().getId());
//        ps.setDouble(11, student.getYearsExperience());
//        ps.setString(12, student.getFacebookLink());
//        ps.setString(13, student.getLinkedInLink());
//        ps.setString(14, student.getGradYear().getId());
//        ps.setString(15, student.getGradSemester().getId());

//        int result = ps.executeUpdate();
//        ps.close();
//        return result;
    }

    @Override
    public int deleteStudent(int studentId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.DELETE_STUDENT.toString());
        ps.setString(1, Integer.toString(studentId));
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public UserSessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(UserSessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    @PreDestroy
    @Remove
    public void destroyConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
