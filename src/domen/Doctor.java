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
public class Doctor implements Serializable{
    
    private int id;
    private String name;
    private String surname;
    private String gender;
    private Date dateBirth;
    private String mobileNumber;
    private Date dateEmployment;
    private double salary;
    private DoctorType doctorType;

    public Doctor() {
    }

    public Doctor(int id, String name, String surname, String gender, Date dateBirth, String mobileNumber, Date dateEmployment, double salary, DoctorType doctorType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
        this.dateEmployment = dateEmployment;
        this.salary = salary;
        this.doctorType = doctorType;
    }

    public Doctor(String name, String surname, String gender, Date dateBirth, String mobileNumber, Date dateEmployment, double salary, DoctorType doctorType) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
        this.dateEmployment = dateEmployment;
        this.salary = salary;
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

    public Date getDateEmployment() {
        return dateEmployment;
    }

    public void setDateEmployment(Date dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    
    
    
}
