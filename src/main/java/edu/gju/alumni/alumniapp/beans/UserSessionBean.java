/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.beans;

import edu.gju.alumni.alumniapp.models.Student;
import edu.gju.alumni.alumniapp.services.ConnectionService;
import edu.gju.alumni.alumniapp.utils.AlumniServEnum;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hesham
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserSessionBean implements Serializable {

    private String userName;
    private String sessionName;
    private String userPassword;
    private Connection connection;
    private String selectedItemId;
    private Student loggedStudent;
    private HttpServletRequest request;
    private HttpSession session;
    private int menuIndex = 0;

    @Inject
    private ConnectionService connService;
//    @Inject
//    private AlumniEditService alumniEditService;
    private FacesContext facesContext;

    public UserSessionBean() {
    }

    @PostConstruct
    public void init() {
        facesContext = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        session = request.getSession();
//        loggedStudent = new Student();
    }

    public void login() {

        NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
        boolean success = false;
        Map<String, String> uGroup = new HashMap<>();
        try {
            uGroup = connService.login(this.userName, this.userPassword);
        } catch (SQLException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (uGroup != null) {
            success = true;
            for (String s : uGroup.keySet()) {
                userName = s;
            }
            sessionName = (String) session.getAttribute("userName");
            if (sessionName == null) {
                setSessionName(userName);
            }
            session.setAttribute("userName", sessionName);
        }

        if (success) {

            if (facesContext != null) {

                if (uGroup.get(userName).equals(AlumniServEnum.ADMIN.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/admin/admin_first_page?faces-redirect=true");
                } else if (uGroup.get(userName).equals(AlumniServEnum.ALUMNI.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/alumni/alumni_first_page?faces-redirect=true");
                } else if (uGroup.get(userName).equals(AlumniServEnum.REGISTRAR.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/registrar/registrar_first_page?faces-redirect=true");
                } else if (uGroup.get(userName).equals(AlumniServEnum.DSA.toString())) {
                    navHandler.handleNavigation(facesContext, null, "/dsa/dsa_employee_first_page?faces-redirect=true");
                } else {
                    navHandler.handleNavigation(facesContext, null, "/accountant/accountant_first_page?faces-redirect=true");
                }
            }
        }

    }

    @PreDestroy
    public void logout() {
        try {
            connService.logout();
        } catch (SQLException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUserName(null);
        setUserPassword(null);
        facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();

    }

    public ConnectionService getConnService() {
        return connService;
    }

    public void setConnService(ConnectionService connService) {
        this.connService = connService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

    public void setSelectedItemId(String selectedItemId) {
        this.selectedItemId = selectedItemId;
    }

    public int getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }

}
