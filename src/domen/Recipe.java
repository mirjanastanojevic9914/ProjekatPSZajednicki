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
public class Recipe implements Serializable{
    
    private int id;
    private String dose;
    private Examination examination;
    private Illness illness;
    private Medication medication;

    public Recipe() {
    }

    public Recipe(int id, String dose, Examination examination, Illness illness, Medication medication) {
        this.id = id;
        this.dose = dose;
        this.examination = examination;
        this.illness = illness;
        this.medication = medication;
    }

    public Recipe(String dose, Examination examination, Illness illness, Medication medication) {
        this.dose = dose;
        this.examination = examination;
        this.illness = illness;
        this.medication = medication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public Illness getIllness() {
        return illness;
    }

    public void setIllness(Illness illness) {
        this.illness = illness;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
    
    

    
    
}
