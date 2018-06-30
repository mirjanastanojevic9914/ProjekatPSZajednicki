/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Mira
 */
public class DoctorType implements Serializable{
    
    private int id;
    private String doctorType;

    public DoctorType() {
    }

    public DoctorType(int id, String doctorType) {
        this.id = id;
        this.doctorType = doctorType;
    }

    public DoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
