/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.DegreeDAO;
import edu.gju.alumni.alumniapp.daos.annotations.DegDAO;
import edu.gju.alumni.alumniapp.models.Degree;
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
public class DegreeService {

    @Inject
    @DegDAO
    private DegreeDAO degreeDAO;

    public DegreeService() {
    }

    public List<Degree> getAllDegrees() throws SQLException {
        List<Degree> degrees = new ArrayList<>();
        degrees = degreeDAO.getAllDegrees();
        return degrees;
    }

    public Map<Integer, Degree> getAllDegreesMap() throws SQLException {
        Map<Integer, Degree> degreeMap = new HashMap<>();
        degreeMap = degreeDAO.degreeMap();
        return degreeMap;
    }

}
