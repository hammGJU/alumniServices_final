/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.AlumniEditDAO;
import edu.gju.alumni.alumniapp.daos.annotations.AlumniEdit;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author hesham
 */
@Local(AlumniEditDAO.class)
@Stateless(name = "alumniEditDAO")
@AlumniEdit
public class AlumniEditDAOImpl extends ConnectionDAOImpl implements Serializable, AlumniEditDAO {

    private Connection connection;

    @PostConstruct
    @Override
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
    public Student getAlumniInformation(String studentId) throws SQLException {
        Student student = new Student();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALUMNI.toString());
        ps.setString(1, studentId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            student = PopulateModels.populateStudent(rs);
        }
        rs.close();
        ps.close();
        return student;

    }

    @Override
    public int editAlumniInfo(Student student) {
        int result = 0;
        boolean committed = true;
        PreparedStatement ps;
        Connection connection = null;
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String apptDay = df.format(student.getDateOfBirth());
        
        try {
            connection = getConnection();
//            connection.setAutoCommit(false);

            ps = connection.prepareStatement(AlumniServEnum.EDIT_STUDENT.toString());
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, apptDay);
            ps.setString(4, student.getNationality());
            ps.setString(5, student.getSchool().getId());
            ps.setString(6, student.getDepartment().getId());
            ps.setInt(7, student.getDegree().getId());
            ps.setDouble(8, student.getGpa());
            ps.setString(9, student.getGender().getId());
            ps.setString(10, student.getStatus().getId());
            ps.setDouble(11, student.getYearsExperience());
            ps.setString(12, student.getFacebookLink());
            ps.setString(13, student.getLinkedInLink());
            ps.setString(14, student.getGradYear().getId());
            ps.setString(15, student.getGradSemester().getId());
            ps.setString(16, student.getId());
            result = ps.executeUpdate();

//            ps = connection.prepareStatement(AlumniServEnum.UPDATE_STUDENT_JOB.toString());
//            ps.setString(1, student.getStudentJob().getCompanyName());
//            ps.setString(2, student.getStudentJob().getStudentPosition());
//            ps.setString(3, student.getStudentJob().getConntry());
//            ps.setString(4, student.getStudentJob().getCity());
//            ps.setString(5, student.getId());
//            result = ps.executeUpdate();
            ps.close();
//            connection.commit();
        } catch (SQLException ex) {
            committed = false;
            Logger.getLogger(AlumniEditDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                //            if (!committed) {
//                try {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlumniEditDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
     @Override
    public Map<String, String> getJobStatusMap() throws SQLException {
        Map<String, String> statusMap = new HashMap<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.SELECT_STUDENT_JOB_STATUS.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            statusMap.put((rs.getString(AlumniServEnum.STATUS_ID.toString())), rs.getString(AlumniServEnum.STATUS_NAME.toString()));
        }
        return statusMap;
    }

}
