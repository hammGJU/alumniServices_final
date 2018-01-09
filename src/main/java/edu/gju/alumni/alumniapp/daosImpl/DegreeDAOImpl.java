/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.DegreeDAO;
import edu.gju.alumni.alumniapp.daos.annotations.DegDAO;
import edu.gju.alumni.alumniapp.models.Degree;
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
@Local(DegreeDAO.class)
@Stateless(name = "degreeDAOImpl")
@DegDAO
public class DegreeDAOImpl extends ConnectionDAOImpl implements DegreeDAO, Serializable {

    private Connection connection;

    @PostConstruct
    @PostActivate
    public void init() {
        try {
//            this.connection = super.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DegreeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Degree> getAllDegrees() throws SQLException {
        List<Degree> degrees = new ArrayList<>();
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.GET_ALL_DEGREES.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Degree d = PopulateModels.populateDegrees(rs);
            degrees.add(d);
        }
        rs.close();
        ps.close();
        return degrees;
    }

    @Override
    public Map<Integer, Degree> degreeMap() throws SQLException {
        Map<Integer, Degree> degreeMap = new HashMap<>();
        PreparedStatement ps = getConnection().prepareStatement(AlumniServEnum.GET_ALL_DEGREES.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Degree d = PopulateModels.populateDegrees(rs);
            degreeMap.put(d.getId(), d);
        }
        return degreeMap;
    }

}
