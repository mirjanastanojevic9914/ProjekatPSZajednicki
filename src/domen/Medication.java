/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Mira
 */
public class Medication implements Serializable{
    
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
    
    
    
    
}
