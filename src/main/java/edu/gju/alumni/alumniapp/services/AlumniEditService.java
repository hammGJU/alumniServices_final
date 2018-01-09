/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.services;

import edu.gju.alumni.alumniapp.Idaos.AlumniEditDAO;
import edu.gju.alumni.alumniapp.daos.annotations.AlumniEdit;
import edu.gju.alumni.alumniapp.models.Student;
import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author hesham
 */
@Stateful
public class AlumniEditService implements Serializable {

    @AlumniEdit
    @Inject
    private AlumniEditDAO alumniDAO;

    public AlumniEditService() {
    }

    public Student getAlumniPersonalInfo(String studentId) throws SQLException {
        Student student = new Student();
        student = alumniDAO.getAlumniInformation(studentId);
        return student;
    }

    public int editALumniInfo(Student student) throws SQLException {
        int result = alumniDAO.editAlumniInfo(student);
        return result;
    }

    public AlumniEditDAO getAlumniDAO() {
        return alumniDAO;
    }

    public void setAlumniDAO(AlumniEditDAO alumniDAO) {
        this.alumniDAO = alumniDAO;
    }

}
