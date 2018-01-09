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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String, String> jobMap;
    private List<String> jobsStatus;

    public AlumniEditBean() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        student = sessionBean.getLoggedStudent();
        userName = (String) sessionBean.getSession().getAttribute("userName");
        jobMap = new LinkedHashMap<>();
        jobsStatus = new ArrayList<>();
        try {
            student = alumniEditService.getAlumniPersonalInfo(userName);
            jobMap = alumniEditService.getJobStatusMap();
            for (String s : jobMap.keySet()) {
                String status = jobMap.get(s);
                jobsStatus.add(status);
            }
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

    public Map<String, String> getJobMap() {
        return jobMap;
    }

    public void setJobMap(Map<String, String> jobMap) {
        this.jobMap = jobMap;
    }

    public List<String> getJobsStatus() {
        return jobsStatus;
    }

    public void setJobsStatus(List<String> jobsStatus) {
        this.jobsStatus = jobsStatus;
    }

}
