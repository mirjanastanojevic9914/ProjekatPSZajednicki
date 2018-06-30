/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mira
 */
public class Illness implements Serializable{
    
    private int id;
    private String name;
    private String description;
    private Illness groupIllness;
    private List<Illness> subgroupIllness;

    public Illness() {
        subgroupIllness = new ArrayList<>();
    }

    public Illness(int id, String name, String description, Illness groupIllness, List<Illness> subgroupIllness) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupIllness = groupIllness;
        this.subgroupIllness = subgroupIllness;
    }

    public Illness(String name, String description, Illness groupIllness, List<Illness> subgroupIllness) {
        this.name = name;
        this.description = description;
        this.groupIllness = groupIllness;
        this.subgroupIllness = subgroupIllness;
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
    
    
    
    
    
    
     
}
