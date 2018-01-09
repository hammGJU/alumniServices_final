/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Degree;
import edu.gju.alumni.alumniapp.models.Department;
import edu.gju.alumni.alumniapp.models.School;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.DegreeService;
import edu.gju.alumni.alumniapp.services.DepartmentService;
import edu.gju.alumni.alumniapp.services.SchoolService;
import edu.gju.alumni.alumniapp.services.StudentService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hesham
 */
@Named(value = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {

    private List<Student> studentList;
    private Student selectedStudent;
    private List<Student> listOfStudents;
    private Student student;
    private Degree degree;
    private School school;
    private List<School> schools;
    private Department department;
    private List<Department> departments;
    private boolean disabled;
    int counter;
    @Inject
    private StudentService studentService;
    @Inject
    private DegreeService degreeService;
    @Inject
    private DepartmentService depService;
    @Inject
    private SchoolService schoolService;
    @Inject
    private UserSessionBean sessionBean;
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, String> schoolMap;
    private Map<String, String> depMap;
    private String student_id;
    public StudentBean() {
        student_id = null;
    }

    
      public void setStudentId (String id)
    {
        student_id = id;
    }
    public String getStudentId ()
    {
        return student_id;
    }
    
    @PostConstruct
    public void populateStudents() {
        disabled = true;
        counter = 0;
        schoolMap = new HashMap<>();
        school = new School();
        department = new Department();
        Map<String, String> map = new HashMap<String, String>();

        try {
            studentList = studentService.getAllStudents();
            schools = schoolService.fetchAllSchools();
            for (School s : schools) {

                schoolMap.put(s.getShcoolName(), s.getShcoolName());
                departments = depService.getAllDepartments(Integer.parseInt(s.getId()));
                for (Department d : departments) {
                    map.put(d.getDepartmentName(), d.getDepartmentName());
                }
                data.put(s.getShcoolName(), map);
                map = new HashMap<>();
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

    public Student getStudentById(int id) {
        Student student = new Student();
        try {
            student = studentService.getStudentById(id);
        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public void insertStudent() {
        try {
            studentService.addStudent(student_id);
            //student = new Student();
        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Degree> fetchAllDegrees() {
        List<Degree> degrees = new ArrayList<>();
        try {
            degrees = degreeService.getAllDegrees();
        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return degrees;

    }

    public void fetchAllDepartments() {
//        List<Department> departments = new ArrayList<>();
        try {
            if (school != null) {
                int id = Integer.parseInt(school.getId());

                departments = depService.getAllDepartments(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<School> fetchAllSchools() {
        List<School> schools = new ArrayList<>();
        try {
            schools = schoolService.fetchAllSchools();
        } catch (SQLException ex) {
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schools;
    }

    public void saveSelectedItemId() {
        sessionBean.setSelectedItemId(selectedStudent.getId());
    }

//    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Student Selected", ((Student) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Student Unselected", ((Student) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void onCountryChange() {
        if (school.getShcoolName() != null && !school.getShcoolName().equals("")) {

            depMap = data.get(school.getShcoolName());

        } else {
            depMap = new HashMap<>();
        }
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Map<String, String> getSchoolMap() {
        return schoolMap;
    }

    public void setSchoolMap(Map<String, String> schoolMap) {
        this.schoolMap = schoolMap;
    }

    public Map<String, String> getDepMap() {
        return depMap;
    }

    public void setDepMap(Map<String, String> depMap) {
        this.depMap = depMap;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void onRowSelect() {

        disabled = false;
        counter += 1;

    }

    public void disableButton() {
        if (counter > 0) {
            counter = 0;
            disabled = true;
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
