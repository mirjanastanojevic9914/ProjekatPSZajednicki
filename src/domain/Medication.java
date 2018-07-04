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
public class Medication implements GenericDomainObject {

    private int id;
    private String name;
    private String effect;
    private String quantity;
    private String shape;
    private Date dateValidity;
    private String producer;

    public Medication() {
    }

    public Medication(int id, String name, String effect, String quantity, String shape, Date dateValidity, String producer) {
        this.id = id;
        this.name = name;
        this.effect = effect;
        this.quantity = quantity;
        this.shape = shape;
        this.dateValidity = dateValidity;
        this.producer = producer;
    }

    public Medication(String name, String effect, String quantity, String shape, Date dateValidity, String producer) {
        this.name = name;
        this.effect = effect;
        this.quantity = quantity;
        this.shape = shape;
        this.dateValidity = dateValidity;
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Date getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(Date dateValidity) {
        this.dateValidity = dateValidity;
    }

    @Override
    public String returnTableName() {
        return "Medication";
    }

    @Override
    public List<GenericDomainObject> fill(ResultSet rs) throws Exception {
        try {
            List<GenericDomainObject> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Medication(rs.getInt("id"), rs.getString("name"),
                        rs.getString("effect"), rs.getString("quantity"), rs.getString("shape"),
                        rs.getDate("dateValidity"), rs.getString("producer")));
            }
            rs.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Loading medications was unsuccessful!", ex);
        }
    }

    @Override
    public String returnInsertValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "" + id + ",'" + name + "', '" + effect + "', '" + quantity + "', '" + shape + "', '"
                + df.format(dateValidity) + "', '" + producer + "'";
    }

    @Override
    public GenericDomainObject convert(ResultSet rs) throws Exception {
        GenericDomainObject gdo = null;
        try {
            if (rs.next()) {
                
                id = rs.getInt("id");
                        name= rs.getString("name");
                        effect=rs.getString("effect");
                        quantity= rs.getString("quantity");
                        shape=rs.getString("shape");
                        dateValidity= rs.getDate("dateValidity");
                        producer= rs.getString("producer");

                gdo = new Medication(id, name, effect, quantity, shape, dateValidity, producer);
            }
            return gdo;
        } catch (SQLException ex) {
            throw new Exception("That medication doesn't exist", ex);
        }
    }

    @Override
    public String returnConditionWithID() {
        return "WHERE id=" + id;
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
            case "effect":
                return getEffect();
            case "quantity":
                return getQuantity();
            case "shape":
                return getShape();
            case "dateValidity":
                return getDateValidity();
            case "producer":
                return getProducer();
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
            case "effect":
                setEffect(attributeValue.toString());
                break;
            case "quantity":
                setQuantity(attributeValue.toString());
                break;
            case "shape":
                setShape(attributeValue.toString());
                break;
            case "dateValidity":
                setDateValidity((Date) attributeValue);
                break;
            case "producer":
                setProducer(attributeValue.toString());
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
        return "WHERE name = '" + criteria;
    }

    @Override
    public String returnUpdateValues() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "id=" + id + ", name='" + name + "', effect='" + effect + "', quantity='" + quantity + "', shape='" + shape + "', dateValidity='"
                + df.format(dateValidity) + "', producer='" + producer + "'";
    }

}
