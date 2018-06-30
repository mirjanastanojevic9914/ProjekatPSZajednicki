/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mira
 */
public class Refer implements Serializable{
    private int id;
    private String description;
    private Date dateValidity;
    private DoctorType doctorType;

    public Refer() {
    }

    public Refer(int id, String description, Date dateValidity, DoctorType doctorType) {
        this.id = id;
        this.description = description;
        this.dateValidity = dateValidity;
        this.doctorType = doctorType;
    }

    public Refer(String description, Date dateValidity, DoctorType doctorType) {
        this.description = description;
        this.dateValidity = dateValidity;
        this.doctorType = doctorType;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(Date dateValidity) {
        this.dateValidity = dateValidity;
    }
    
    
    
}
