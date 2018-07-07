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
public class Doctor implements GenericDomainObject {

    private int id;
    private String name;
    private String surname;
    private String password;
    private String gender;
    private Date dateBirth;
    private String mobileNumber;
    private Date dateEmployment;
    private double salary;
    private DoctorType doctorType;

    public Doctor() {
    }

    public Doctor(int id, String name, String surname, String password, String gender, Date dateBirth, String mobileNumber, Date dateEmployment, double salary, DoctorType doctorType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
        this.dateEmployment = dateEmployment;
        this.salary = salary;
        this.doctorType = doctorType;
    }

    public Doctor(String name, String surname, String password, String gender, Date dateBirth, String mobileNumber, Date dateEmployment, double salary, DoctorType doctorType) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.mobileNumber = mobileNumber;
        this.dateEmployment = dateEmployment;
        this.salary = salary;
        this.doctorType = doctorType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    
    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
   public boolean equals(Object obj) {
        if (this.id == ((Doctor) obj).getId()) {
            return true;
        }
        return false;
    }

   

    @Override
    public String returnTableName() {
        return "Doctor ";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Doctor(rs.getInt("doctor.id"), rs.getString("name"), rs.getString("surname"),rs.getString("password"),
                        rs.getString("gender"), rs.getDate("dateBirth"), rs.getString("mobileNumber"),
                        rs.getDate("dateEmployment"), rs.getDouble("salary"), new DoctorType(rs.getInt("dt.id"), rs.getString("doctorType"))));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading doctors was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + name + "','" + surname + "','" + password + "','" + gender + "','" + df.format(dateBirth) + "','"
                + mobileNumber + "','" + df.format(dateEmployment) + "', " + salary + ", " + doctorType.getId() + "";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                id = rs.getInt("doctor.id");
                name = rs.getString("name");
                surname = rs.getString("surname");
                password = rs.getString("password");

                gender = rs.getString("gender");
                dateBirth = rs.getDate("dateBirth");
                mobileNumber = rs.getString("mobileNumber");
                dateEmployment = rs.getDate("dateEmployment");
                salary = rs.getDouble("salary");
                int doctorTypeID = rs.getInt("dt.id");
                String doctorTypeName = rs.getString("doctorType");
                DoctorType doctorType = new DoctorType(doctorTypeID, doctorTypeName);

                gdo = new Doctor(id, name, surname, password,gender, dateBirth, mobileNumber, dateEmployment, salary, doctorType);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That doctor doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return " doctor.id = " + id;
    }

    @Override
    public String returnID() {
        return "doctor.id";
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
            case "password":
                return getPassword();
            case "gender":
                return getGender();
            case "dateBirth":
                return getDateBirth();
            case "mobileNumber":
                return getMobileNumber();
            case "dateEmployment":
                return getDateEmployment();
            case "salary":
                return getSalary();
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
                break;
            case "name":
                setName(attributeValue.toString());
                break;
            case "surname":
                setSurname(attributeValue.toString());
                break;
            case "password":
                setPassword(attributeValue.toString());
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
            case "dateEmployment":
                setDateEmployment((Date) attributeValue);
                break;
            case "salary":
                setSalary((double) attributeValue);
                break;
            case "doctorType":
                setDoctorType((DoctorType) attributeValue);
                break;
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return " doctor JOIN doctorType dt ON(doctor.idDoctorType = dt.id)";

    }

    @Override
    public String returnSearchCriteria(String criteria) {
       if (criteria.equals("")) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String criteriaDate;
            Double criteriaSalary;
            try {
                Date date = df.parse(criteria);
                df = new SimpleDateFormat("yyyy-MM-dd");
                criteriaDate = df.format(date);
                criteriaSalary = Double.parseDouble(criteria);
                
            } catch (ParseException ex) {
                criteriaDate = criteria;
                criteriaSalary = -1.0;
            }
            return "WHERE name LIKE '%" + criteria + "%' OR surname LIKE '%" + criteria + "%' OR gender LIKE '%" + criteria + "%' OR dateBirth LIKE '%" + criteriaDate + "%' OR mobileNumber LIKE '%" + criteria + "%' OR dateEmployment LIKE '%" + criteriaDate + "%' OR salary =" + criteriaSalary;
        }
    }

    @Override
    public String returnUpdateValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "id=" + id + ", name='" + name + "',surname='" + surname + "', password='" + password + "',gender='" + gender + "', dateBirth='" + df.format(dateBirth)
                + "',mobileNumber='" + mobileNumber + "', dateEmployment='" + df.format(dateEmployment) + "', salary=" + salary + ", idDoctorType=" + doctorType.getId() + "";
    }

    @Override
    public String returnNameOfAtributtesForInsert() {
        return "(name, surname, password, gender, dateBirth, mobileNumber, dateEmployment, salary, idDoctorType)";
    }
    public String returnSearchCriteriaForLogin(String criteria) {
         if (criteria.equals("")) {
            return "";
        }
       
        String[] array = criteria.split("/");
        String name = array[0];
        String surname = array[1];
        String password = array[2];
        
        return "WHERE name = '" + name + "' AND surname = '" + surname + "' AND password = '" + password + "'";
    }
}
