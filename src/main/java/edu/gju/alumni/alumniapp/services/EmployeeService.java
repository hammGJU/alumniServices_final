/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.EmployeeDAO;
import edu.gju.alumni.alumniapp.daos.annotations.empDAO;
import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@Stateful
public class EmployeeService {

    @empDAO
    @Inject
    private EmployeeDAO employeeDAO;

    public EmployeeService() {
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        employees = employeeDAO.getAllEmployees();
        return employees;
    }
    

    public Employee getEmployeeById(int id) throws SQLException {
        Employee employee = new Employee();
        employee = employeeDAO.getEmployeeById(id);
        return employee;
    }

    public Map<Integer, String> getGroupsMap() throws SQLException {
        Map<Integer, String> groups = new HashMap<>();
        groups = employeeDAO.getGroupsMap();
        return groups;
    }

    public int maxEmployeeId() throws SQLException {
        return employeeDAO.maxEmployeeId();
    }

    public int addEmployee(Employee employee) throws SQLException {
        int result = employeeDAO.addEmployee(employee);
        return result;
    }

    public int editEMployee(Employee employee) throws SQLException {
        int result = employeeDAO.editEmployee(employee);
        return result;
    }

    public int deleteEmployee(String employeeId) throws SQLException {
        int result = employeeDAO.deleteEmployee(employeeId);
        return result;
    }

}
