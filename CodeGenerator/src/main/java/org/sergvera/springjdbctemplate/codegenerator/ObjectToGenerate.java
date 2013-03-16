/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sergvera.springjdbctemplate.codegenerator;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author svera
 */
public class ObjectToGenerate {

    private String tableName;
    private String objectName;
    private String basePackage;
    private String daoPackage;
    private String domainPackage;
    private String servicePackage;
    
    private String webPath;


    private List<ObjectField> pkfields = new ArrayList<ObjectField>();
    private List<ObjectField> fields = new ArrayList<ObjectField>();

    /**
     * When this object is used, the instance name will be the object all
     * uppercase
     *
     * @return
     */
    public String getInstanceName() {
        return objectName.toLowerCase();
    }

    public String getObjectName() {
        return StringUtils.capitalize(objectName.toLowerCase());
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

    public String getPKFields_InstanceDotGetFieldList_CommaSeparated() {
        return getPKFields_InstanceDotGetFieldList_CommaSeparated(true);
    }

    public String getPKFields_InstanceDotGetFieldList_CommaSeparated(Boolean removeLAstComma) {
        StringBuilder sb = new StringBuilder();

        for (ObjectField field : this.pkfields) {
            sb.append("" + this.getInstanceName() + "." + field.getFieldGetter() + ",");
        }
        if (removeLAstComma) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }

    public String getAllFields_InstanceDotGetFieldList_CommaSeparated() {

        StringBuilder sb = new StringBuilder(getPKFields_InstanceDotGetFieldList_CommaSeparated(false));

        for (ObjectField field : this.getFields()) {
            sb.append("" + this.getInstanceName() + "." + field.getFieldGetter() + ",");
        }

        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public String getPKColumns_CommaSeparated(Boolean removeLAstComma) {
         StringBuilder sb = new StringBuilder();

        for (ObjectField field : this.pkfields) {
            sb.append( field.getFieldName()+ ",");
        }
        if (removeLAstComma) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        return sb.toString();
    }
    
    public String getAllColumns_CommaSeparated() {
         StringBuilder sb = new StringBuilder(getPKColumns_CommaSeparated(false));

        for (ObjectField field : this.getFields()) {
            sb.append( field.getFieldName() + ",");
        }

        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    
     /**
     * @return the pkfields
     */
    public String getAllFieldsAndType_commaSeparated() {

        List<ObjectField> allFields = new ArrayList<ObjectField>();

        allFields.addAll(this.pkfields);
        allFields.addAll(this.fields);

         StringBuilder sb = new StringBuilder();

        for (ObjectField field : allFields) {
            sb.append( ""+ field.getTranslatedFieldType()+" "+ field.getFieldName() + ",");
        }

        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
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
    
    
    public String getBasePackage() {
        if(!basePackage.trim().endsWith("."))
        {
            basePackage=basePackage.trim()+".";
        }
        return basePackage.trim();
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoPackage() {
        return  getBasePackage()+daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getDomainPackage() {
        return  getBasePackage()+domainPackage;
    }

    public void setDomainPackage(String domainPackage) {
        this.domainPackage = domainPackage;
    }

    public String getServicePackage() {
        return  getBasePackage()+servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }
    
    
    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String webPath) {
        this.webPath = webPath;
    }
}
