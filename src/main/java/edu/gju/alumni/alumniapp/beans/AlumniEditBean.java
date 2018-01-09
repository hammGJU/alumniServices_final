/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.AlumniEditService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@ViewScoped
@Named(value = "editAlumniBean")
public class AlumniEditBean implements Serializable {

    @Inject
    private AlumniEditService alumniEditService;
    @Inject
    private UserSessionBean sessionBean;
    private Student student;
    private String userName;

    public AlumniEditBean() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        student = sessionBean.getLoggedStudent();
        userName = (String) sessionBean.getSession().getAttribute("userName");
        try {
            student = alumniEditService.getAlumniPersonalInfo(userName);
        } catch (SQLException ex) {
            Logger.getLogger(AlumniEditBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editAlumniInfo() {
        try {
            int result = alumniEditService.editALumniInfo(student);
        } catch (SQLException ex) {
            Logger.getLogger(AlumniEditBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AlumniEditService getAlumniEditService() {
        return alumniEditService;
    }

    public void setAlumniEditService(AlumniEditService alumniEditService) {
        this.alumniEditService = alumniEditService;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
