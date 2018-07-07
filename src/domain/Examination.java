/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mira
 */
public class Examination implements GenericDomainObject {

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
    public void addRecipe(Recipe recipe){
        listOfRecipes.add(recipe);
    }
    public void addRefer(Refer refer){
        listOfRefers.add(refer);
    }
   
    
    @Override
    public String returnTableName() {
        return "Examination";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("e.id");
                dateTime = (Timestamp)rs.getTimestamp("dateTime");
                status = rs.getString("status");
                report = rs.getString("report");
                Doctor doctor = new Doctor(rs.getInt("d.id"), rs.getString("d.name"), rs.getString("d.surname"), rs.getString("d.password"),
                        rs.getString("d.gender"), rs.getDate("d.dateBirth"), rs.getString("d.mobileNumber"),
                        rs.getDate("dateEmployment"), rs.getDouble("salary"), new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType")));
                Patient patient = new Patient(rs.getInt("p.id"), rs.getString("p.name"), rs.getString("p.surname"),
                        rs.getString("p.gender"), rs.getDate("p.dateBirth"), rs.getString("p.mobileNumber"));

                Examination examination = new Examination(id, dateTime, status, report, patient, doctor);

                if (!list.contains(examination)) {
                    list.add(examination);
                }

            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(Examination.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading examination was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "" + id + ",'"
                + df.format(dateTime) + "','"
                + status + "',' " + report + "', " + patient.getId() + ", " + doctor.getId();
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        Examination examination = null;
        try {
            
            while (rs.next()) {
                id = rs.getInt("e.id");
                dateTime = (Timestamp)rs.getTimestamp("dateTime");
                status = rs.getString("status");
                report = rs.getString("report");
                doctor = new Doctor(rs.getInt("d.id"), rs.getString("d.name"), rs.getString("d.surname"),rs.getString("d.password"),
                        rs.getString("d.gender"), rs.getDate("d.dateBirth"), rs.getString("d.mobileNumber"),
                        rs.getDate("dateEmployment"), rs.getDouble("salary"), new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType")));
                patient = new Patient(rs.getInt("p.id"), rs.getString("p.name"), rs.getString("p.surname"),
                        rs.getString("p.gender"), rs.getDate("p.dateBirth"), rs.getString("p.mobileNumber"));
                
                int idRefer = rs.getInt("rf.id");
                String description = rs.getString("rf.description");
                Date dateValidity = rs.getDate("rf.dateValidity");
                
                
                Refer refer = new Refer(idRefer, description, dateValidity, new DoctorType(rs.getInt("dt.id"), rs.getString("dt.doctorType")),examination);
                if (examination == null) {
                    examination = new Examination(id, dateTime, status, report, patient, doctor);
                    refer.setExamination(examination);
                    examination.addRefer(refer);
                } else {
                    examination.addRefer(refer);
                }
                /*Recipe recipe = new Recipe(id, report, this, illness, medication);
                if (examination == null) {
                    examination = new Examination(id, dateTime, status, report, patient, doctor);
                    recipe.setExamination(this);
                    examination.addRecipe(recipe);
                } else {
                    examination.addRecipe(recipe);
                }*/
                


            }
            rs.close();
            return examination;
        } catch (SQLException ex) {
            Logger.getLogger(Examination.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Loading examination was unsuccessful!", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return "WHERE e.id=" + id;
    }

    @Override
    public String returnID() {
        return "e.id";
    }

    @Override
    public Object get(String attributeName) {
        switch (attributeName) {
            case "id":
                return getId();
            case "dateTime":
                return getDateTime();
            case "status":
                return getStatus();
            case "report":
                return getReport();
            case "patient":
                return getPatient();
            case "doctor":
                return getDoctor();
            case "listOfRefers":
                return getListOfRefers();
            case "listOfRecipes":
                return getListOfRecipes();
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
            case "dateTime":
                setDateTime((Timestamp) attributeValue);
                break;
            case "status":
                setStatus(attributeValue.toString());
                break;
            case "report":
                setReport(attributeValue.toString());
                break;
            case "patient":
                setPatient((Patient) attributeValue);
                break;
            case "doctor":
                setDoctor((Doctor) attributeValue);
                break;
            case "listOfRefers":
                setListOfRefers((ArrayList<Refer>) attributeValue);
                break;
            case "listOfRecipes":
                setListOfRecipes((ArrayList<Recipe>) attributeValue);
                break;    
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return " JOIN patient p ON(e.idPatient = p.id) JOIN doctor d ON(e.idDoctor = d.id) JOIN doctorType dt ON(d.idDoctorType = dt.id) "
                + "JOIN refer rf ON(e.id = rf.idExamination) JOIN doctorType dt ON(rf.idDoctorType = dt.id)";

    }

    @Override
    public String returnSearchCriteria(String criteria) {
        if (criteria.equals("")) {
            return "";
        }
        return "WHERE dateTime = '" + criteria ;
    }

    @Override
    public String returnUpdateValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "id=" + id + ",dateTime='"
                + df.format(dateTime) + "',status='"
                + status + "',report=' " + report + "', idPatient= " + patient.getId() + ", idDoctor= " + doctor.getId();
    }

    @Override
    public String returnNameOfAtributtesForInsert() {
        return "";
    }

    @Override
    public String returnSearchCriteriaForLogin(String criteria) {
        return"";
    }

}
