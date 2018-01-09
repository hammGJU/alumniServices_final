/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import edu.gju.alumni.alumniapp.models.Degree;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface DegreeDAO extends ConnectionDAO{
    List<Degree> getAllDegrees() throws SQLException;
    Map<Integer, Degree> degreeMap() throws SQLException;
    
}
