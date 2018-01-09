/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author hesham
 */
public class StudentClearance implements Serializable {

    private String id;
    private String dsaClearance;
    private String accntClearance;
    private String registClearance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDsaClearance() {
        return dsaClearance;
    }

    public void setDsaClearance(String dsaClearance) {
        this.dsaClearance = dsaClearance;
    }

    public String getAccntClearance() {
        return accntClearance;
    }

    public void setAccntClearance(String accntClearance) {
        this.accntClearance = accntClearance;
    }

    public String getRegistClearance() {
        return registClearance;
    }

    public void setRegistClearance(String registClearance) {
        this.registClearance = registClearance;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.dsaClearance);
        hash = 97 * hash + Objects.hashCode(this.accntClearance);
        hash = 97 * hash + Objects.hashCode(this.registClearance);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentClearance other = (StudentClearance) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dsaClearance, other.dsaClearance)) {
            return false;
        }
        if (!Objects.equals(this.accntClearance, other.accntClearance)) {
            return false;
        }
        if (!Objects.equals(this.registClearance, other.registClearance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentClearance{" + "id=" + id + ", dsaClearance=" + dsaClearance + ", accntClearance=" + accntClearance + ", registClearance=" + registClearance + '}';
    }

}
