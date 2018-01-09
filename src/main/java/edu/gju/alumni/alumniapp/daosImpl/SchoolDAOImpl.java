/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.SchoolDAO;
import edu.gju.alumni.alumniapp.daos.annotations.SchlDAO;
import edu.gju.alumni.alumniapp.models.School;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import edu.gju.alumni.alumniapp.utils.PopulateModels;
import java.io.Serializable;
import java.sql.Connection;
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
@Local(SchoolDAO.class)
@Stateless(name = "schoolDAO")
@SchlDAO
public class SchoolDAOImpl extends ConnectionDAOImpl implements SchoolDAO, Serializable {

    private Connection connection;

    @PostConstruct
    @PostActivate
    public void init() {
        try {
            this.connection = super.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(SchoolDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<School> getAllSchools() throws SQLException {
        List<School> schools = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(AlumniServEnum.GET_ALL_SCHOOLS.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            School school = PopulateModels.populateSchool(rs);
            schools.add(school);
        }
        rs.close();
        ps.close();

        return schools;
    }

}
