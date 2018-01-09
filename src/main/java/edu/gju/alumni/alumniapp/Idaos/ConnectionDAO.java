/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import edu.gju.alumni.alumniapp.models.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.ejb.Local;
import javax.enterprise.inject.Default;

/**
 *
 * @author hesham
 */
@Local
@Default
public interface ConnectionDAO {

    public Connection getConnection() throws SQLException;

    public void closeConnection() throws SQLException;

    public Map<String, String> login(String userName, String userPassword) throws SQLException;

    public void logout() throws SQLException;

}
