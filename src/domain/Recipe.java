/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mira
 */
public class Recipe implements GenericDomainObject{
    
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
    
    @Override
    public String returnTableName() {
        return "Recipe";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                int examinationId = rs.getInt("e.id");
                Timestamp dateTime = (Timestamp)rs.getTimestamp("dateTime");
                String status = rs.getString("e.status");
                String report = rs.getString("report");
                Doctor doctor = new Doctor(rs.getInt("d.id"), rs.getString("d.name"), rs.getString("d.surname"), rs.getString("d.password"),
                        rs.getString("d.gender"), rs.getDate("d.dateBirth"), rs.getString("d.mobileNumber"),
                        rs.getDate("dateEmployment"), rs.getDouble("salary"), new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType")));
                Patient patient = new Patient(rs.getInt("p.id"), rs.getString("p.name"), rs.getString("p.surname"),
                        rs.getString("p.gender"), rs.getDate("p.dateBirth"), rs.getString("p.mobileNumber"));
                Examination examination = new Examination(examinationId, dateTime, status, report, patient, doctor);
                Medication medication = new Medication(rs.getInt("m.id"), rs.getString("m.name"),
                        rs.getString("effect"), rs.getString("quantity"), rs.getString("shape"),
                        rs.getDate("dateValidity"), rs.getString("producer"));
                Illness illness = new Illness(rs.getInt("i.id"), rs.getString("i.name"), rs.getString("i.description"),null);
                list.add(new Recipe(rs.getInt("r.id"), rs.getString("dose"),examination, illness, medication));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading recipes was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
       
        return "" + id + ",'" + dose + "', " + examination.getId() + " , " + illness.getId() + " , " + medication.getId() + "";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                int examinationId = rs.getInt("e.id");
                Timestamp dateTime = (Timestamp)rs.getTimestamp("dateTime");
                String status = rs.getString("e.status");
                String report = rs.getString("report");
                Doctor doctor = new Doctor(rs.getInt("d.id"), rs.getString("d.name"), rs.getString("d.surname"), rs.getString("d.password"),
                        rs.getString("d.gender"), rs.getDate("d.dateBirth"), rs.getString("d.mobileNumber"),
                        rs.getDate("dateEmployment"), rs.getDouble("salary"), new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType")));
                Patient patient = new Patient(rs.getInt("p.id"), rs.getString("p.name"), rs.getString("p.surname"),
                        rs.getString("p.gender"), rs.getDate("p.dateBirth"), rs.getString("p.mobileNumber"));
                examination = new Examination(examinationId, dateTime, status, report, patient, doctor);
                medication = new Medication(rs.getInt("m.id"), rs.getString("m.name"),
                        rs.getString("effect"), rs.getString("quantity"), rs.getString("shape"),
                        rs.getDate("dateValidity"), rs.getString("producer"));
                illness = new Illness(rs.getInt("i.id"), rs.getString("i.name"), rs.getString("i.description"),null);
                
                id = rs.getInt("r.id");
                dose = rs.getString("dose");
                
                Recipe recipe = new Recipe(id, dose, examination, illness, medication);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That recipe doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return "WHERE r.id=" + id;
    }

    @Override
    public String returnID() {
        return "r.id";
    }

    @Override
    public Object get(String attributeName) {
        switch (attributeName) {
            case "id":
                return getId();
            case "dose":
                return getDose();
            case "examination":
                return getExamination();
            case "illness":
                return getIllness();
            case "medication":
                return getMedication();
            default:
                return "N/A";
        }
    }

    @Override
    public void set(String attributeName, Object attributeValue) {
        switch (attributeName) {

            case "id":
                setId((int) attributeValue);
                break;
            case "dose":
                setDose(attributeValue.toString());
                break;
            case "examination":
                setExamination((Examination)attributeValue);
                break;
            case "illness":
                setIllness((Illness)attributeValue);
                break;
            case "medication":
                setMedication((Medication)attributeValue);
                break;
            
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return " JOIN medication m ON(r.idMedication = m.id) JOIN illness i ON(r.idIllness=i.id)"
                + " JOIN examination e ON(r.idExamination = e.id) JOIN patient p ON(e.idPatient = p.id)"
                + "JOIN doctor d ON(e.idDoctor = d.id) JOIN doctorType dt ON(d.idDoctorType = dt.id)" ;

    }

    @Override
    public String returnSearchCriteria(String criteria) {
         if (criteria.equals("")) {
            return "";
        }
        return "";
    }

    @Override
    public String returnUpdateValues() {
        
        return "id=" + id + ", dose='" + dose + "',examination=" + examination.getId() +
                ", illness= " + illness.getId() + ", medication= " + medication.getId() + "";
               
    }

    
    
}
