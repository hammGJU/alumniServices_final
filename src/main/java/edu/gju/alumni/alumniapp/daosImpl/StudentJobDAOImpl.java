/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.StudentJobDAO;
import edu.gju.alumni.alumniapp.daos.annotations.StJobDAO;
import java.io.Serializable;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(StudentJobDAO.class)
@Stateless(name = "studentJobDAO")
@StJobDAO
public class StudentJobDAOImpl extends ConnectionDAOImpl implements StudentJobDAO, Serializable{
    
}
