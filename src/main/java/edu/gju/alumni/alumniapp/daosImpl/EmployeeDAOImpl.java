/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.EmployeeDAO;
import edu.gju.alumni.alumniapp.daos.annotations.empDAO;
import edu.gju.alumni.alumniapp.models.Employee;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Local(EmployeeDAO.class)
@Stateless
@empDAO
public class EmployeeDAOImpl extends ConnectionDAOImpl implements EmployeeDAO, Serializable {

    private Connection connection;

    @PostConstruct
    @PostActivate
    public void init() {
        try {
//            this.connection = super.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.GET_ALL_EMPLOYEES.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Employee e = PopulateModels.populateEmployee(rs);
            employees.add(e);
        }
        rs.close();
        ps.close();
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.GET_EMPLOYEE_BY_ID.toString());
        ps.setString(1, Integer.toString(id));
        ResultSet rs = ps.executeQuery();
        Employee employee = new Employee();
        while (rs.next()) {
            employee = PopulateModels.populateEmployee(rs);
        }
        rs.close();
        ps.close();
        return employee;
    }

    @Override
    public int addEmployee(Employee employee) {
        int result = 0;
        boolean committed = true;
        PreparedStatement ps;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(AlumniServEnum.INSERT_USER_GROUPS.toString());
            ps.setString(1, employee.getDepartment().getUserId());
            ps.setString(2, employee.getDepartment().getGroupdId());
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.INSERT_EMPLOYEE.toString());
            ps.setString(1, employee.getId());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
//        ps.setString(4, employee.getDepartment().getGroupdId());
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.INSERT_EMPLOYEE_USER.toString());
            ps.setString(1, employee.getId());
            ps.setString(2, employee.getUserName());
            ps.setString(3, employee.getPassword());
            result = ps.executeUpdate();
            ps.close();
            connection.commit();
        } catch (SQLException ex) {
            committed = false;
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!committed) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    @Override
    public int editEmployee(Employee employee) {
        int result = 0;
        boolean committed = true;
        PreparedStatement ps;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(AlumniServEnum.UPDATE_EMP_USER_GROUP.toString());
            ps.setString(1, employee.getDepartment().getGroupName());
            ps.setString(2, employee.getId());
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.UPDATE_EMPLOYEE.toString());
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getId());
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.UPDATE_EMP_USERS.toString());
            ps.setString(1, employee.getUserName());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getId());
            result = ps.executeUpdate();
            ps.close();
            connection.commit();
        } catch (SQLException ex) {
            committed = false;
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (!committed) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    @Override
    public int deleteEmployee(String employeeId) {
        int result = 0;
        boolean committed = true;
        PreparedStatement ps;
        Connection connection = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(AlumniServEnum.DELETE_EMP_USERS.toString());
            ps.setString(1, employeeId);
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.DELETE_EMP_EMPLOYEE.toString());
            ps.setString(1, employeeId);
            result = ps.executeUpdate();

            ps = connection.prepareStatement(AlumniServEnum.DELETE_EMP_USER_GROUP.toString());
            ps.setString(1, employeeId);
            result = ps.executeUpdate();

            connection.setAutoCommit(false);
            ps.close();
            connection.commit();

        } catch (SQLException ex) {
            committed = false;
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (!committed) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return result;
    }

    @Override
    public Map<Integer, String> getGroupsMap() throws SQLException {
        Map<Integer, String> groups = new HashMap<>();
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.SELECT_GROUPS.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            groups.put(rs.getInt(AlumniServEnum.GROUP_ID.toString()), rs.getString(AlumniServEnum.EMPLOYEE_DEPARTMENT.toString()));
        }
        return groups;
    }

    public int maxEmployeeId() throws SQLException {
        int maxId = 0;
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.GENERATE_EMPLOYEE_ID.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            maxId = rs.getInt(AlumniServEnum.EMPLOYEE_ID.toString());
        }
        return maxId;
    }

}
