/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.StudentService;
import edu.gju.alumni.alumniapp.utils.SendEmail;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

/**
 *
 * @author hesham
 */
@ViewScoped
@Named(value = "viewStudent")
public class ContactStudentBean implements Serializable {

    private Student student;
    List<Student> listOfStudents;
    private List<Email> emails;
    @Inject
    private StudentService studentService;
    @Inject
    private StudentBean studentBean;
    @Inject
    private ClearanceStatusBean clearanceBean;

    private final String emailUserName = "bata@my.localdomain";
    private final String emailPassowrd = "root";
    private String toEmail;
    private String message;
    private String subject;

    public ContactStudentBean() {
    }

    @PostConstruct
    @PostActivate
    public void init() {
        student = studentBean.getSelectedStudent();
        listOfStudents = studentBean.getListOfStudents();
        studentBean.setSelectedStudent(null);
        studentBean.setListOfStudents(null);
        try {
            if (student != null) {
                emails = studentService.getStudentEmails(student.getId());
                int size = emails.size();
                if (size == 2) {
                    student.setEmail1(emails.get(0).getEmail());
                    student.setEmail2(emails.get(1).getEmail());
                } else if (size == 1) {
                    student.setEmail1(emails.get(0).getEmail());
                    student.setEmail2(null);
                } else {
                    student.setEmail1(null);
                    student.setEmail2(null);
                }
            } else {
                for (Student s : listOfStudents) {
                    emails = studentService.getStudentEmails(s.getId());
                    int size = emails.size();
                    if (size == 2) {
                        s.setEmail1(emails.get(0).getEmail());
                        s.setEmail2(emails.get(1).getEmail());
                    } else if (size == 1) {
                        s.setEmail1(emails.get(0).getEmail());
                        s.setEmail2(null);
                    } else {
                        s.setEmail1(null);
                        s.setEmail2(null);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContactStudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void sendEmail() {
        List<String> emailsToRecieve = new ArrayList<>();
        SendEmail sendEmail = new SendEmail();
        if (listOfStudents != null) {
            for (Student s : listOfStudents) {
                String e1 = s.getEmail1();
                String e2 = s.getEmail2();
                if (e1 == null && e2 == null) {
                    e1 = "ce743project.hamm@gmail.com";
                    e2 = "ce743project.hamm@gmail.com";
                } else if (e2 == null) {
                    e2 = "ab.alhusseini@gju.edu.jo";
                }
                emailsToRecieve.add(e1);
                emailsToRecieve.add(e2);
            }

        } else {
            String e1 = student.getEmail1();
            String e2 = student.getEmail2();
            if (e1 == null && e2 == null) {
                e1 = "ce743project.hamm@gmail.com";
                e2 = "ce743project.hamm@gmail.com";
            } else if (e2 == null) {
                e2 = "ab.alhusseini@gju.edu.jo";
            }
            emailsToRecieve.add(e1);
            emailsToRecieve.add(e2);
        }
        try {
            sendEmail.sendEmail("bata@my.localdomain", emailPassowrd, emailUserName, emailsToRecieve,
                    subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(ContactStudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
