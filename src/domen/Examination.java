/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;


import java.io.Serializable;
import java.util.List;
import java.security.Timestamp;

import java.util.ArrayList;


/**
 *
 * @author Mira
 */
public class Examination implements Serializable{
    
  private int id;
  private Timestamp dateTime;
  private String status;
  private String report;
  private Patient patient;
  private Doctor doctor;
  private List<Refer> listOfRefers;
  private List<Recipe> listOfRecipes;

  
    public Examination() {
        listOfRefers = new ArrayList<>();
        listOfRecipes = new ArrayList<>();
    }

    public Examination(int id, Timestamp dateTime, String status, String report, Patient patient, Doctor doctor) {
        this.id = id;
        this.dateTime = dateTime;
        this.status = status;
        this.report = report;
        this.patient = patient;
        this.doctor = doctor;
        this.listOfRefers = new ArrayList<>();
        this.listOfRecipes = new ArrayList<>();
    }

    public Examination(Timestamp dateTime, String status, String report, Patient patient, Doctor doctor) {
        this.dateTime = dateTime;
        this.status = status;
        this.report = report;
        this.patient = patient;
        this.doctor = doctor;
        this.listOfRefers = new ArrayList<>();
        this.listOfRecipes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Refer> getListOfRefers() {
        return listOfRefers;
    }

    public void setListOfRefers(List<Refer> listOfRefers) {
        this.listOfRefers = listOfRefers;
    }

    public List<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

    public void setListOfRecipes(List<Recipe> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }

   
  
  
}
