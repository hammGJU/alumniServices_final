/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.utils;

import edu.gju.alumni.alumniapp.models.Degree;
import edu.gju.alumni.alumniapp.models.Department;
import edu.gju.alumni.alumniapp.models.Email;
import edu.gju.alumni.alumniapp.models.Employee;
import edu.gju.alumni.alumniapp.models.Gender;
import edu.gju.alumni.alumniapp.models.GraduationSemester;
import edu.gju.alumni.alumniapp.models.GraduationYear;
import edu.gju.alumni.alumniapp.models.School;
import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.models.StudentClearance;
import edu.gju.alumni.alumniapp.models.StudentStatus;
import edu.gju.alumni.alumniapp.models.UserGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hesham
 */
public class PopulateModels {

    public static Student populateStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        Degree degree = new Degree();
        School school = new School();
        Department department = new Department();
        Gender gender = new Gender();
        StudentStatus status = new StudentStatus();
        StudentClearance clearance = new StudentClearance();
        GraduationYear gradYear = new GraduationYear();
        GraduationSemester gradSemester = new GraduationSemester();
        student.setId(rs.getString(AlumniServEnum.STUDENT_ID.toString()));
        student.setFirstName(rs.getString(AlumniServEnum.STUDENT_FIRST_NAME.toString()));
        student.setLastName(rs.getString(AlumniServEnum.STUDENT_LAST_NAME.toString()));
        student.setDateOfBirth(rs.getDate(AlumniServEnum.DATE_OF_BIRTH.toString()));
        student.setNationality(rs.getString(AlumniServEnum.NATIONALITY.toString()));
        school.setId(rs.getString(AlumniServEnum.SCHOOL_ID.toString()));
        school.setShcoolName(rs.getString(AlumniServEnum.SCHOOL_NAME.toString()));
        student.setSchool(school);
        department.setId(rs.getString(AlumniServEnum.DEPARTMENT_ID.toString()));
        department.setDepartmentName(rs.getString(AlumniServEnum.DEPARTMENT_NAME.toString()));
        student.setDepartment(department);
        degree.setId(rs.getInt(AlumniServEnum.DEGREE_ID.toString()));
        degree.setDegreeName(rs.getString(AlumniServEnum.DEGREE_NAME.toString()));
        student.setDegree(degree);
        student.setGpa(rs.getDouble(AlumniServEnum.GPA.toString()));
        gender.setId(rs.getString(AlumniServEnum.GENDER_ID.toString()));
        gender.setGenderName(rs.getString(AlumniServEnum.GENDER_NAME.toString()));
        student.setGender(gender);
        status.setId(rs.getString(AlumniServEnum.STATUS_ID.toString()));
        status.setStatusName(rs.getString(AlumniServEnum.STATUS_NAME.toString()));
        student.setStatus(status);
        student.setYearsExperience(rs.getDouble(AlumniServEnum.YEARS_OF_EXPERIENCE.toString()));
        student.setFacebookLink(rs.getString(AlumniServEnum.FACEBOOK.toString()));
        student.setLinkedInLink(rs.getString(AlumniServEnum.LINKEDIN.toString()));
        gradYear.setId(rs.getString(AlumniServEnum.GRADUATION_YEAR_ID.toString()));
        gradYear.setYearName(rs.getString(AlumniServEnum.YEAR_NAME.toString()));
        student.setGradYear(gradYear);
        gradSemester.setId(rs.getString(AlumniServEnum.GRADUATION_SEMESTER_ID.toString()));
        gradSemester.setSemesterName(rs.getString(AlumniServEnum.SEMESTER_NAME.toString()));
        student.setGradSemester(gradSemester);
        clearance.setId(rs.getString(AlumniServEnum.STUDENT_ID.toString()));
        clearance.setDsaClearance(rs.getString(AlumniServEnum.DSA_STATUS_ID.toString()));
        clearance.setAccntClearance(rs.getString(AlumniServEnum.ACCOUNTANT_STATUS_ID.toString()));
        clearance.setRegistClearance(rs.getString(AlumniServEnum.REGISTRAR_STATUS_ID.toString()));
        student.setClearance(clearance);
        return student;
    }

    public static List<Email> populateEmail(ResultSet rs) throws SQLException {
        List<Email> emails = new ArrayList<>();
        Email email = new Email();

        while (rs.next()) {
            email.setId(rs.getString(AlumniServEnum.STUDENT_ID.toString()));
            email.setEmail(rs.getString(AlumniServEnum.STUDENT_EMAIL.toString()));
            emails.add(email);

        }
        return emails;
    }

    public static Employee populateEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        UserGroup group = new UserGroup();
        employee.setId(rs.getString(AlumniServEnum.EMPLOYEE_ID.toString()));
        employee.setFirstName(rs.getString(AlumniServEnum.EMPLOYEE_FIRST_NAME.toString()));
        employee.setLastName(rs.getString(AlumniServEnum.EMPLOYEE_LAST_NAME.toString()));
        employee.setUserName(rs.getString(AlumniServEnum.USER_NAME.toString()));
        employee.setPassword(rs.getString(AlumniServEnum.PASSWORD.toString()));
        group.setGroupdId(rs.getString(AlumniServEnum.GROUP_ID.toString()));
        group.setUserId(rs.getString(AlumniServEnum.USER_ID.toString()));
        employee.setDepartment(group);
        return employee;
    }

    public static Degree populateDegrees(ResultSet rs) throws SQLException {
        Degree degree = new Degree();
        degree.setId(rs.getInt(AlumniServEnum.DEGREE_ID.toString()));
        degree.setDegreeName(rs.getString(AlumniServEnum.DEGREE_NAME.toString()));
        return degree;

    }

    public static Department populateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getString(AlumniServEnum.DEPARTMENT_ID.toString()));
        department.setDepartmentName(rs.getString(AlumniServEnum.DEPARTMENT_NAME.toString()));
        School school = new School();
        school.setId(rs.getString(AlumniServEnum.SCHOOL_ID.toString()));
        school.setShcoolName(rs.getString(AlumniServEnum.SCHOOL_NAME.toString()));
        department.setSchool(school);
        return department;
    }

    public static School populateSchool(ResultSet rs) throws SQLException {
        School school = new School();
        school.setId(rs.getString(AlumniServEnum.SCHOOL_ID.toString()));
        school.setShcoolName(rs.getString(AlumniServEnum.SCHOOL_NAME.toString()));
        return school;
    }

    public static StudentClearance populateClearance(ResultSet rs) throws SQLException {
        StudentClearance clearance = new StudentClearance();
        clearance.setId(rs.getString(AlumniServEnum.STUDENT_ID.toString()));
        clearance.setDsaClearance(rs.getString(AlumniServEnum.DSA_STATUS_ID.toString()));
        clearance.setAccntClearance(rs.getString(AlumniServEnum.ACCOUNTANT_STATUS_ID.toString()));
        clearance.setRegistClearance(rs.getString(AlumniServEnum.REGISTRAR_STATUS_ID.toString()));
        return clearance;
    }

}
