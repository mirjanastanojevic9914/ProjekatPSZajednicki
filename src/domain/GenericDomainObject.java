/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Stefana
 */
public interface GenericDomainObject extends Serializable{

    public String returnTableName();

    public List<GenericDomainObject> fill(ResultSet rs) throws Exception;

    public String returnInsertValues();

    public GenericDomainObject convert(ResultSet rs) throws Exception;

    public String returnConditionWithID();

    public String returnID();
    
    public Object get(String attributeName);
    
    public void set(String attributeName, Object attributeValue);

    public String returnTableWithJoinCondition();
    
    public String returnSearchCriteria(String criteria);

    public String returnUpdateValues();

    
    
}
