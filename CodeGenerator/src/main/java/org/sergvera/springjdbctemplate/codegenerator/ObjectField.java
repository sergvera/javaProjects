/*

 */
package org.sergvera.springjdbctemplate.codegenerator;

import org.apache.commons.lang.StringUtils;

/**
 *
 * 
 */
public class ObjectField {
    
    private String fieldName;
    private String fieldType;

    public ObjectField( String fieldType, String fieldName) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    
    public String getFieldGetter() {
        return "get"+getCapitalizedFieldName()+"()";
    }
    
    public String getFieldSetter() {
        return "set"+getCapitalizedFieldName()+"("+getTranslatedFieldType()+" value)";
    }
    
    
    
    
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    

    public String getCapitalizedFieldName()
    {
        return StringUtils.capitalize(fieldName.toLowerCase()).trim();
    }
    
    public String getResultSetFieldType()
    {
        if(fieldType.trim().equalsIgnoreCase("VARCHAR") || fieldType.trim().equalsIgnoreCase("string") )
        {
            return "String";
        }
        
        if(fieldType.trim().equalsIgnoreCase("DATE") )
        {
            return "Date";
        }
        
        
        if(fieldType.trim().equalsIgnoreCase("DECIMAL") )
        {
            return "BigDecimal";
        }
        
        if(fieldType.trim().equalsIgnoreCase("FLOAT") )
        {
            return "BigDecimal";
        }
        
        if(fieldType.trim().equalsIgnoreCase("INT") )
        {
            return "Int";
        }
        
        
        if(fieldType.trim().equalsIgnoreCase("CHAR") )
        {
            return "String";
        }
        
        throw new RuntimeException("Result set Field type:"+fieldType + " has no available translation");
    }
    
    public String getTranslatedFieldType()
    {
        if(fieldType.trim().equalsIgnoreCase("VARCHAR") || fieldType.trim().equalsIgnoreCase("string") )
        {
            return "String";
        }
        
        if(fieldType.trim().equalsIgnoreCase("DATE") )
        {
            return "Date";
        }
        
        if(fieldType.trim().equalsIgnoreCase("DECIMAL") )
        {
            return "BigDecimal";
        }
        
        if(fieldType.trim().equalsIgnoreCase("FLOAT") )
        {
            return "BigDecimal";
        }
        
        if(fieldType.trim().equalsIgnoreCase("INT") )
        {
            return "Integer";
        }
        
        
        if(fieldType.trim().equalsIgnoreCase("CHAR") )
        {
            return "Character";
        }
        
        throw new RuntimeException("Field type:"+fieldType + " has no available translation");
    }
    
    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
