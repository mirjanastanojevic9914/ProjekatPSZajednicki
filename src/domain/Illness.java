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
public class Illness implements GenericDomainObject{
    
    private int id;
    private String name;
    private String description;
    private Illness groupIllness;
    private List<Illness> subgroupIllness;

    public Illness() {
        subgroupIllness = new ArrayList<>();
    }

    public Illness(int id, String name, String description, Illness groupIllness) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupIllness = groupIllness;
        this.subgroupIllness = new ArrayList<>();
    }

    public Illness(String name, String description, Illness groupIllness) {
        this.name = name;
        this.description = description;
        this.groupIllness = groupIllness;
        this.subgroupIllness = new ArrayList<>();
    }

    public List<Illness> getSubgroupIllness() {
        return subgroupIllness;
    }

    public void setSubgroupIllness(List<Illness> subgroupIllness) {
        this.subgroupIllness = subgroupIllness;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Illness getGroupIllness() {
        return groupIllness;
    }

    public void setGroupIllness(Illness groupIllness) {
        this.groupIllness = groupIllness;
    }
    
    
    @Override
    public String returnTableName() {
        return "Illness";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                
                Illness illnessGroup = new Illness();
                illnessGroup.setId(rs.getInt("iGroup.id"));
                illnessGroup.setName(rs.getString("iGroup.name"));
                illnessGroup.setDescription(rs.getString("iGroup.description"));
                
                
                
                list.add(new Illness(rs.getInt("i.id"), rs.getString("i.name"), rs.getString("i.description"),
                       illnessGroup));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading illnesses was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        return "" + id + ",'" + name + "','" + description + "', " + groupIllness.getId() + "";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                id = rs.getInt("i.id");
                name = rs.getString("i.name");
                description = rs.getString("i.description");
                
                groupIllness = new Illness();
                groupIllness.setId(rs.getInt("iGroup.id"));
                groupIllness.setName(rs.getString("iGroup.name"));
                groupIllness.setDescription(rs.getString("iGroup.description"));

                gdo = new Illness(id, name, description, groupIllness);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That illness doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return "WHERE i.id=" + id;
    }

    @Override
    public String returnID() {
        return "i.id";
    }

    @Override
    public Object get(String attributeName) {
        switch (attributeName) {
            case "id":
                return getId();
            case "name":
                return getName();
            case "description":
                return getDescription();
            case "groupIllness":
                return getGroupIllness();
            case "subgroupIllness":
                return getSubgroupIllness();
           
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
            case "description":
                setDescription(attributeValue.toString());
                break;
            case "groupIllness":
                setGroupIllness((Illness)attributeValue);
                break;
            case "subgroupIllness":
                setSubgroupIllness((ArrayList<Illness>) attributeValue);
                break;
            
        }
    }

    @Override
    public String returnTableWithJoinCondition() {
        return " JOIN illness iGroup ON(i.id = iGroup.id)";

    }

    @Override
    public String returnSearchCriteria(String criteria) {
         if (criteria.equals("")) {
            return "";
        }
        return "WHERE name = '" + criteria + "'";
    }

    @Override
    public String returnUpdateValues() {
        return "id=" + id + ", name='" + name + "',description='" + description + "',idGroupIllness=" + groupIllness.getId() + "";

    }
}
