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
import java.util.Objects;

/**
 *
 * @author Mira
 */
public class DoctorType implements GenericDomainObject{
    
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

    @Override
    public String toString() {
        return doctorType;
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
        final DoctorType other = (DoctorType) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.doctorType, other.doctorType)) {
            return false;
        }
        return true;
    }
    
    
    
     @Override
    public String returnTableName() {
        return "DoctorType ";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new DoctorType(rs.getInt("id"), rs.getString("doctorType")));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading doctor types was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
       
        return "" + id + ",'" +doctorType + "'";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
               
                id = rs.getInt("id");
                doctorType = rs.getString("doctorType");

                gdo =  new DoctorType(id, doctorType);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That doctor type doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return " id=" + id;
    }

    @Override
    public String returnID() {
        return "id";
    }

    @Override
    public Object get(String attributeName) {
        switch (attributeName) {
            case "id":
                return getId();
            
            case "doctorType":
                return getDoctorType();
            default:
                return "N/A";
        }
    }

    @Override
    public void set(String attributeName, Object attributeValue) {
        switch (attributeName) {

            case "id":
                setId((int) attributeValue);
            
            case "doctorType":
                setDoctorType(attributeValue.toString());
                break;
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return "";

    }

    @Override
    public String returnSearchCriteria(String criteria) {
         if (criteria.equals("")) {
            return "";
        }
        return "WHERE doctorType = '" + criteria;
    }

    @Override
    public String returnUpdateValues() {
        return "id=" + id + ", doctorType='" + doctorType + "'";
    }

    @Override
    public String returnNameOfAtributtesForInsert() {
        return "";
    }

    @Override
    public String returnSearchCriteriaForLogin(String criteria) {
        return "";
    }
    
}
