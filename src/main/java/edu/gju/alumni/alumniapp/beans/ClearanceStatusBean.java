/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentClearance;
import edu.gju.alumni.alumniapp.services.ClearanceService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
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
@Named(value = "clearanceBean")
public class ClearanceStatusBean implements Serializable {

    private List<StudentClearance> clearances;
    private List<String> clearanceString;
    private String selectedClearance;
    @Inject
    private ClearanceService clearanceService;
    @Inject
    private ContactStudentBean viewStudent;
    private Student student;

    @PostConstruct
    @PostActivate
    public void init() {
        student = new Student();
        student = viewStudent.getStudent();
        try {
            clearanceString = clearanceService.cleranceString();
            clearances = clearanceService.getClearanceStatuses(student);
        } catch (SQLException ex) {
            Logger.getLogger(ClearanceStatusBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editStudent() {
        if (student.getClearance().getDsaClearance().equalsIgnoreCase("CLEARED")) {
            student.getClearance().setDsaClearance("1");
        } else {
            student.getClearance().setDsaClearance("2");
        }
        if (student.getClearance().getRegistClearance().equalsIgnoreCase("CLEARED")) {
            student.getClearance().setRegistClearance("1");
        } else {
            student.getClearance().setRegistClearance("2");
        }
        if (student.getClearance().getAccntClearance().equalsIgnoreCase("CLEARED")) {
            student.getClearance().setAccntClearance("1");
        } else {
            student.getClearance().setAccntClearance("2");
        }
        try {
            clearanceService.editStudent(student);
            student = new Student();
        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StudentClearance> getClearances() {
        return clearances;
    }

    public void setClearances(List<StudentClearance> clearances) {
        this.clearances = clearances;
    }

    public String getSelectedClearance() {
        return selectedClearance;
    }

    public void setSelectedClearance(String selectedClearance) {
        this.selectedClearance = selectedClearance;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<String> getClearanceString() {
        return clearanceString;
    }

    public void setClearanceString(List<String> clearanceString) {
        this.clearanceString = clearanceString;
    }

}
