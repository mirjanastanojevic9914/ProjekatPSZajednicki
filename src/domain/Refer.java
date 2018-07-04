/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mira
 */
public class Refer implements GenericDomainObject{
    private int id;
    private String description;
    private Date dateValidity;
    private DoctorType doctorType;
    private Examination examination;

    public Refer() {
    }

    public Refer(int id, String description, Date dateValidity, DoctorType doctorType, Examination examination) {
        this.id = id;
        this.description = description;
        this.dateValidity = dateValidity;
        this.doctorType = doctorType;
        this.examination = examination;
    }

  

    public Refer(String description, Date dateValidity, DoctorType doctorType, Examination examination) {
        this.description = description;
        this.dateValidity = dateValidity;
        this.doctorType = doctorType;
        this.examination = examination;
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

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

     @Override
    public String returnTableName() {
        return "Refer";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
               
                int id=rs.getInt("id");
                String description = rs.getString("description");
                Date dateValidity = rs.getDate("dateValidity");
                Refer refer = new Refer(id, description, dateValidity, new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType")), examination);
               
                
                list.add(refer);
                
                
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading refers was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "" + examination.getId() + "," + id + ",'" + description + "','" +  df.format(dateValidity) + "'," + doctorType.getId() + ""; 
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                id = rs.getInt("rf.id");
                description = rs.getString("description");
                dateValidity = rs.getDate("dateValidity");
               
                int doctorTypeID = rs.getInt("dt.id");
                String doctorTypeName = rs.getString("doctorType");
                DoctorType doctorType = new DoctorType(doctorTypeID, doctorTypeName);

                gdo = new Refer(id, description, dateValidity, doctorType, examination);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That refer doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return "WHERE rf.id=" + id;
    }

    @Override
    public String returnID() {
        return "rf.id";
    }

    @Override
    public Object get(String attributeName) {
        switch (attributeName) {
            case "id":
                return getId();
            case "description":
                return getDescription();
            case "dateValidity":
                return getDateValidity();
            case "doctorType":
                return getDoctorType();
            case "examination":
                return getExamination();
           
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
            case "description":
                setDescription(attributeValue.toString());
                break;
            case "dateValidity":
                setDateValidity((Date)attributeValue);
                break;
            case "doctorType":
                setDoctorType((DoctorType)attributeValue);
                break;
            case "examination":
                setExamination((Examination)attributeValue);
                break;
            
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return " JOIN doctorType dt ON(rf.idDoctorType=dt.id)";

    }

    @Override
    public String returnSearchCriteria(String criteria) {
         if (criteria.equals("")) {
            return "";
        }
         
        String[] array = criteria.split("/");
        int idExamination = Integer.parseInt(array[0]);
        int id = Integer.parseInt(array[1]);
       
        return "WHERE rf.id = " + id + " AND idExamination = " + idExamination + "";
    }

    @Override
    public String returnUpdateValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "idExamination=" + examination.getId() + ", id=" + id + ",description='" + description + "', dateValidity='" + df.format(dateValidity) + "', idDoctorType= "+doctorType.getId()+"";
               
    }
    
    
    
}
