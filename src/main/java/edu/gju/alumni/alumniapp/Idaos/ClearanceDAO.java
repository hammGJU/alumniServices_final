/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentClearance;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface ClearanceDAO extends ConnectionDAO {

    List<StudentClearance> getClearanceStatuses(Student student) throws SQLException;

    List<String> cleranceString() throws SQLException;

    public int editStudentClearance(Student student) throws SQLException;

}
