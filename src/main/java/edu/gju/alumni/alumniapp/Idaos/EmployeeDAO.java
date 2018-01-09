/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.Idaos;

import edu.gju.alumni.alumniapp.models.Employee;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author hesham
 */
@Local(ConnectionDAO.class)
public interface EmployeeDAO extends ConnectionDAO {

    public List<Employee> getAllEmployees() throws SQLException;

    public Map<Integer, String> getGroupsMap() throws SQLException;

    public Employee getEmployeeById(int id) throws SQLException;

    public int addEmployee(Employee employee);

    public int editEmployee(Employee employee);

    public int deleteEmployee(String employeeId);

    public int maxEmployeeId() throws SQLException;

}
