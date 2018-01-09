/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.DepartmentDAO;
import edu.gju.alumni.alumniapp.daos.annotations.DeptDAO;
import edu.gju.alumni.alumniapp.models.Department;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@Stateful
public class DepartmentService {

    @Inject
    @DeptDAO
    private DepartmentDAO depDAO;

    public DepartmentService() {
    }

    public List<Department> getAllDepartments(int schoolId) throws SQLException {
        List<Department> departments = new ArrayList<>();
        departments = depDAO.getAllDepartments(schoolId);
        return departments;
    }

}
