/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.GraduationSemesterDAO;
import edu.gju.alumni.alumniapp.daos.annotations.GradSemDAO;
import java.io.Serializable;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(GraduationSemesterDAO.class)
@Stateless(name = "gradSemDAO")
@GradSemDAO
public class GraduationSemesterDAOImpl extends ConnectionDAOImpl implements GraduationSemesterDAO, Serializable{
    
}
