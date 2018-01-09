/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.SchoolDAO;
import edu.gju.alumni.alumniapp.daos.annotations.SchlDAO;
import edu.gju.alumni.alumniapp.models.School;
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
public class SchoolService {

    @Inject
    @SchlDAO
    private SchoolDAO schoolDAO;

    public SchoolService() {
    }

    public List<School> fetchAllSchools() throws SQLException {
        List<School> schools = new ArrayList<>();
        schools = schoolDAO.getAllSchools();
        return schools;
    }

}
