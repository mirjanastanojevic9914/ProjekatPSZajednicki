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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mira
 */
public class Patient implements GenericDomainObject{
    private int id;
    private String name;
    private String surname;
    private String gender;
    private Date dateBirth;
    private String mobileNumber;

    public Patient() {
    }

    public Patient(int id, String name, String surname, String gender, Date dateBirth, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
    }

    public Patient(String name, String surname, String gender, Date dateBirth, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return name + " " + surname;
    }
    
    
    public boolean equals(Object obj) {
        if (this.id == ((Patient) obj).getId()) {
            return true;
        }
        return false;
    }
 
    @Override
    public String returnTableName() {
        return "Patient ";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("gender"), rs.getDate("dateBirth"), rs.getString("mobileNumber")));
                        
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading patients was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + name + "','" + surname + "','" + gender + "','" + df.format(dateBirth) + "','"
                + mobileNumber + "'";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                 id = rs.getInt("id");
                 name = rs.getString("name");
                 surname = rs.getString("surname");
                 gender = rs.getString("gender");
                 dateBirth = rs.getDate("dateBirth");
                 mobileNumber = rs.getString("mobileNumber");
                

                gdo = new Patient(id, name, surname, gender, dateBirth, mobileNumber);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That patient doesn't exist", ex);
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
            case "name":
                return getName();
            case "surname":
                return getSurname();
            case "gender":
                return getGender();
            case "dateBirth":
                return getDateBirth();
            case "mobileNumber":
                return getMobileNumber(); 
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
            case "name":
                setName(attributeValue.toString());
                break;
            case "surname":
                setSurname(attributeValue.toString());
                break;
            case "gender":
                setGender(attributeValue.toString());
                break;
            case "dateBirth":
                setDateBirth((Date) attributeValue);
                break;
            case "mobileNumber":
                setMobileNumber((String) attributeValue);
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
        } else {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String criteriaDate;
            try {
                Date date = df.parse(criteria);
                df = new SimpleDateFormat("yyyy-MM-dd");
                criteriaDate = df.format(date);
            } catch (ParseException ex) {
                criteriaDate = criteria;
            }
            return "WHERE name LIKE '%" + criteria + "%' OR surname LIKE '%" + criteria + "%' OR gender LIKE '%" + criteria + "%' OR dateBirth LIKE '%" + criteriaDate + "%' OR mobileNumber LIKE '%" + criteria + "%'";
        }
    }

    @Override
    public String returnUpdateValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "name='" + name + "',surname='" + surname + "',gender='" + gender + "', dateBirth='" + df.format(dateBirth)
                + "',mobileNumber='" + mobileNumber + "'";
    }

    @Override
    public String returnNameOfAtributtesForInsert() {
        return "(name, surname, gender, dateBirth, mobileNumber)";
    }

    @Override
    public String returnSearchCriteriaForLogin(String criteria) {
        return "";
    }
}
