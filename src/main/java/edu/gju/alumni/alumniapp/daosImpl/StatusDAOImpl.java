/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.daosImpl;

import edu.gju.alumni.alumniapp.Idaos.StatusDAO;
import edu.gju.alumni.alumniapp.daos.annotations.JStatusDAO;
import java.io.Serializable;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author hesham
 */
@Local(StatusDAO.class)
@Stateless(name = "statusDAO")
@JStatusDAO
public class StatusDAOImpl extends ConnectionDAOImpl implements StatusDAO, Serializable{
    
}
