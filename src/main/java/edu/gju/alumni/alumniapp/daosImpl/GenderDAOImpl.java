/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.GenderDAO;
import edu.gju.alumni.alumniapp.daos.annotations.GendrDAO;
import java.io.Serializable;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(GenderDAO.class)
@Stateless(name = "genderDAO")
@GendrDAO
public class GenderDAOImpl extends ConnectionDAOImpl implements GenderDAO, Serializable{
    
}
