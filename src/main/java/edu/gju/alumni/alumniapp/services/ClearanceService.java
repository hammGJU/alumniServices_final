/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.ClearanceDAO;
import edu.gju.alumni.alumniapp.daos.annotations.ClearDAO;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentClearance;
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
public class ClearanceService {

    @Inject
    @ClearDAO
    private ClearanceDAO clearanceDAO;

    public ClearanceService() {
    }

    public List<StudentClearance> getClearanceStatuses(Student student) throws SQLException {
        List<StudentClearance> clearances = new ArrayList<>();
        clearances = clearanceDAO.getClearanceStatuses(student);
        return clearances;
    }

    public List<String> cleranceString() throws SQLException {
        List<String> clearString = new ArrayList<>();
        clearString = clearanceDAO.cleranceString();
        return clearString;
    }

    public int editStudent(Student student) throws SQLException {
        int result = clearanceDAO.editStudentClearance(student);
        return result;
    }

}
