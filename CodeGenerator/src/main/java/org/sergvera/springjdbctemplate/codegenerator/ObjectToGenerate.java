/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sergvera.springjdbctemplate.codegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author svera
 */
public class ObjectToGenerate {
    
    private String tableName;
    
    private String objectName;
    
    private List<ObjectField> pkfields = new ArrayList<ObjectField>();
    
    private List<ObjectField> fields = new ArrayList<ObjectField>();

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    

    public List<ObjectField> getFields() {
        return fields;
    }

    public void setFields(List<ObjectField> fields) {
        this.fields = fields;
    }

    /**
     * @return the pkfields
     */
    public List<ObjectField> getPkfields() {
        return pkfields;
    }

    /**
     * @param pkfields the pkfields to set
     */
    public void setPkfields(List<ObjectField> pkfields) {
        this.pkfields = pkfields;
    }
    
    
    
    /**
     * @return the pkfields
     */
    public List<ObjectField> getAllfields() {
        
        List<ObjectField> allFields = new ArrayList<ObjectField>();
        
        allFields.addAll(this.pkfields);
        allFields.addAll(this.fields);
        
        return allFields;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
}
