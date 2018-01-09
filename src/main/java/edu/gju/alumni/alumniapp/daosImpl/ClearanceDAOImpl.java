/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.ClearanceDAO;
import edu.gju.alumni.alumniapp.daos.annotations.ClearDAO;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentClearance;
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
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(ClearanceDAO.class)
@Stateless
@ClearDAO
public class ClearanceDAOImpl extends ConnectionDAOImpl implements ClearanceDAO, Serializable {

    private Connection connection;

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            this.connection = super.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DegreeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<StudentClearance> getClearanceStatuses(Student student) throws SQLException {
        List<StudentClearance> clearances = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_CLEARANCE.toString());
        ps.setString(1, student.getId());
        ResultSet rs = ps.executeQuery();
        StudentClearance clearance = new StudentClearance();
        while (rs.next()) {
            clearance = PopulateModels.populateClearance(rs);
            clearances.add(clearance);
        }
        return clearances;
    }

    @Override
    public List<String> cleranceString() throws SQLException {
        List<String> clearStrings = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_CLEARANCE_STRING.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            clearStrings.add(rs.getString(AlumniServEnum.CLEARANCE_STATUS_NAME.toString()));
        }
        return clearStrings;
    }

    @Override
    public int editStudentClearance(Student student) throws SQLException{
        PreparedStatement ps;
        int result = 0;
        boolean committed = true;

//        try {
//            connection.setAutoCommit(false);

            ps = connection.prepareStatement(AlumniServEnum.UPDATE_STUDENT_DSA_CLEARANCE.toString());
            ps.setString(1, student.getClearance().getDsaClearance());
            ps.setString(2, student.getClearance().getRegistClearance());
            ps.setString(3, student.getClearance().getAccntClearance());
            ps.setString(4, student.getId());
            result = ps.executeUpdate();

//            ps = connection.prepareStatement(AlumniServEnum.EDIT_STUDENT.toString());
//            ps.setString(1, student.getFirstName());
//            ps.setString(2, student.getLastName());
//            ps.setDate(3, (Date) student.getDateOfBirth());
//            ps.setString(4, student.getNationality());
//            ps.setString(5, student.getSchool().getId());
//            ps.setString(6, student.getDepartment().getId());
//            ps.setInt(7, student.getDegree().getId());
//            ps.setDouble(8, student.getGpa());
//            ps.setString(9, student.getGender().getId());
//            ps.setString(10, student.getStatus().getId());
//            ps.setDouble(11, student.getYearsExperience());
//            ps.setString(12, student.getFacebookLink());
//            ps.setString(13, student.getLinkedInLink());
//            ps.setString(14, student.getGradYear().getId());
//            ps.setString(15, student.getGradSemester().getId());
//            ps.setString(16, student.getId());
//            result = ps.executeUpdate();
            ps.close();
//            connection.commit();
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            committed = false;
//        } finally {
//            if (!committed) {
//                try {
//                    connection.rollback();
//                    connection.setAutoCommit(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        return result;
    }

}
