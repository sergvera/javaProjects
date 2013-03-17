/*
 *
 */
package org.sergvera.springjdbctemplate.codegenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 *
 * @
 */
public class CodeGenerator {

    protected final Log log = LogFactory.getLog(getClass());
    private static String INPUT_FILE = "input.txt";

    public void generateCode() {
        try {
            //Read input file and put into a list
            ObjectToGenerate objectToGenerate = readInputFile();

            //code generation 


            log.info("Printing: Domain object");

            generateDomainObject(objectToGenerate);

            log.info("Printing: Row Mapper");

            generateRowMapper(objectToGenerate);

            log.info("Printing: Dao Interface");

            generateDaoInterface(objectToGenerate);

            log.info("Printing: Dao ");

            generateDaoObject(objectToGenerate);


            log.info("Printing: Controller ");

            generateObjectController(objectToGenerate);

            log.info("Printing: Form elements ");

            generateHtmlForm(objectToGenerate);

             log.info("Printing: jqGrid form definition ");

            generateJqGrid(objectToGenerate);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
     public void generateJqGrid(ObjectToGenerate objectToGenerate) {

        STGroup group = new STGroupFile("src/main/resources/HtmlForm.stg");

        group.verbose = true;
        ST st = null;

        //constructor content
        StringBuffer constructorContent = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("jqGridField");
            st.add("fieldNameUppercased", field.getCapitalizedFieldName());
            st.add("fieldName", field.getFieldName());

            constructorContent.append(st.render());
        }
        
        //erases last comma
        constructorContent.deleteCharAt(constructorContent.lastIndexOf(","));
        
        //generate code for row mapper

        
        
        st = group.getInstanceOf("jqGrid");
        st.add("jqGridFields", constructorContent);


        System.out.println(st.render(120));
    }

    public void generateHtmlForm(ObjectToGenerate objectToGenerate) {

        STGroup group = new STGroupFile("src/main/resources/HtmlForm.stg");

        group.verbose = true;
        ST st = null;

        //constructor content
        StringBuffer constructorContent = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("formField");
            st.add("fieldNameUppercased", field.getCapitalizedFieldName());
            st.add("fieldName", field.getFieldName());

            constructorContent.append(st.render());
        }
        //generate code for row mapper

        st = group.getInstanceOf("form");
        st.add("formFields", constructorContent);


        System.out.println(st.render(120));
    }

    public void generateObjectController(ObjectToGenerate objectToGenerate) {

        STGroup group = new STGroupFile("src/main/resources/ObjectController.stg");

        group.verbose = true;
        ST st = null;


        //generate code for row mapper

        st = group.getInstanceOf("objectController");
        st.add("controllerPackage", objectToGenerate.getServicePackage());
        st.add("daoPackage", objectToGenerate.getDaoPackage());
        st.add("modelPackage", objectToGenerate.getDomainPackage());
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("instanceName", objectToGenerate.getInstanceName());
        st.add("webPath", objectToGenerate.getWebPath());
        st.add("objectPkFields", objectToGenerate.getPKFields_InstanceDotGetFieldList_CommaSeparated());

        System.out.println(st.render(120));
    }

    public void generateDaoObject(ObjectToGenerate objectToGenerate) {

        STGroup group = new STGroupFile("src/main/resources/ObjectDao.stg");

        group.verbose = true;
        ST st = null;


        //generate code for row mapper

        st = group.getInstanceOf("objectDao");
        st.add("packageName", objectToGenerate.getDaoPackage());
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("objectNameLowerCase", objectToGenerate.getObjectName().toLowerCase());
        st.add("instanceName", objectToGenerate.getInstanceName());
        st.add("instanceDotGetPK", objectToGenerate.getPKFields_InstanceDotGetFieldList_CommaSeparated());
        st.add("instanceDotGetAllFields", objectToGenerate.getAllFields_InstanceDotGetFieldList_CommaSeparated());



        System.out.println(st.render(120));
    }

    public void generateDaoInterface(ObjectToGenerate objectToGenerate) {

        STGroup group = new STGroupFile("src/main/resources/ObjectDao.stg");

        group.verbose = true;
        ST st = null;


        //generate code for row mapper

        st = group.getInstanceOf("objectDaoInterface");
        st.add("packageName", objectToGenerate.getDaoPackage());
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("instanceName", objectToGenerate.getInstanceName());

        System.out.println(st.render(120));
    }

    public void generateDomainObject(ObjectToGenerate objectToGenerate) {
        STGroup group = new STGroupFile("src/main/resources/DomainObject.stg");
        group.verbose = true;

        ST st = null;

        //constructor content
        StringBuffer constructorContent = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("objectConstructorContent");
            st.add("fieldName", field.getFieldName());

            constructorContent.append(st.render());
        }

        //constructor
        StringBuffer constructors = new StringBuffer();
        st = group.getInstanceOf("objectConstructors");
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("allFieldsCommaSeparated", objectToGenerate.getAllFieldsAndType_commaSeparated());
        st.add("constructorContent", constructorContent);

        constructors.append(st.render());

        //object fields
        StringBuffer objectFields = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("objectFields");
            st.add("fieldType", field.getTranslatedFieldType());
            st.add("fieldName", field.getFieldName());

            objectFields.append(st.render());
        }

        //object getters
        StringBuffer objectGetters = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("fieldGetter");
            st.add("fieldType", field.getTranslatedFieldType());
            st.add("capitalizedfieldName", field.getCapitalizedFieldName());
            st.add("fieldName", field.getFieldName());

            objectGetters.append(st.render());
        }

        //object setters
        StringBuffer objectSetters = new StringBuffer();
        for (ObjectField field : objectToGenerate.getAllfields()) {
            st = group.getInstanceOf("fieldSetter");
            st.add("fieldType", field.getTranslatedFieldType());
            st.add("capitalizedfieldName", field.getCapitalizedFieldName());
            st.add("fieldName", field.getFieldName());

            objectSetters.append(st.render());
        }

        //final object
        st = group.getInstanceOf("objectDomain");

        st.add("domainPackage", objectToGenerate.getDomainPackage());
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("fields", objectFields);
        st.add("constructors", constructors);
        st.add("getters", objectGetters);
        st.add("setters", objectSetters);


        System.out.println(st.render(120));
    }

    public void generateRowMapper(ObjectToGenerate objectToGenerate) {
        //STGroup group = new STGroupDir("src/main/resources");
        STGroup group = new STGroupFile("src/main/resources/RowMapper.stg");

        group.verbose = true;
        //generate rowMapper fields
        StringBuffer rowMapperSetters = new StringBuffer();
        ST st = null;

        for (ObjectField field : objectToGenerate.getAllfields()) {

            st = group.getInstanceOf("rowMapperSetField");


            if (field.getFieldType().equalsIgnoreCase("char")) {
                st = group.getInstanceOf("rowMapperSetFieldChar");
            }

            st.add("fieldName", field.getCapitalizedFieldName());
            st.add("resultSetFieldType", field.getResultSetFieldType());
            rowMapperSetters.append(st.render());
        }


        //generate code for row mapper

        st = group.getInstanceOf("rowMapper");
        st.add("objectName", objectToGenerate.getObjectName());
        st.add("fields", rowMapperSetters);

        //st.format("rowMapper", objectToGenerate.getObjectName(),rowMapperSetters);

        System.out.println(st.render(120));
    }

    protected ObjectToGenerate readInputFile() throws FileNotFoundException, IOException {

        ObjectToGenerate result = new ObjectToGenerate();
        File inputFile = new File(INPUT_FILE);


        BufferedReader br = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
        try {

            String line = br.readLine();

            while (line != null) {
                log.debug("Processing line:[" + line + "]");

                //ignores empty lines or lines that start with //
                if (StringUtils.isBlank(line) || line.trim().startsWith("//")) {
                    log.debug("Ignored comment line:" + line);
                    line = br.readLine();
                    continue;
                }

                //first line should be the table/object name
                if (StringUtils.isBlank(result.getTableName())) {

                    if (!line.trim().startsWith("<tableName>")) {
                        throw new RuntimeException("The first valid line should start with <tableName>");
                    }
                    line = line.substring(line.indexOf("<tableName>") + "<tableName>".length()).trim();

                    String objectName = StringUtils.capitalize(line).trim();
                    log.debug("The table and object name will be:" + objectName);
                    result.setObjectName(objectName);
                    result.setTableName(objectName);

                    //reads next line
                    line = br.readLine();

                    continue;

                }
                //reads object name
                if (line.trim().startsWith("<objectName>")) {

                    line = line.substring(line.indexOf("<objectName>") + "<objectName>".length()).trim();
                    String newObjectName = StringUtils.capitalize(line).trim();
                    log.debug("Overwriting object name, it was:" + result.getObjectName() + " now it will be:" + newObjectName);
                    result.setObjectName(newObjectName);

                    //reads next line
                    line = br.readLine();

                    continue;
                }

                //package name
                if (line.trim().startsWith("<basePackage>")) {

                    line = line.substring(line.indexOf("<basePackage>") + "<basePackage>".length()).trim();
                    String basePackage = line.trim();
                    log.debug("Base package name:" + basePackage);
                    result.setBasePackage(basePackage);

                    //reads next line
                    line = br.readLine();

                    continue;
                }



                //dao name
                if (line.trim().startsWith("<webPath>")) {

                    line = line.substring(line.indexOf("<webPath>") + "<webPath>".length()).trim();
                    String webPath = line.trim();
                    log.debug("WebPath:" + webPath);
                    result.setWebPath(webPath);

                    //reads next line
                    line = br.readLine();

                    continue;
                }

                //dao name
                if (line.trim().startsWith("<daoPackage>")) {

                    line = line.substring(line.indexOf("<daoPackage>") + "<daoPackage>".length()).trim();
                    String daoPackage = line.trim();
                    log.debug("dao package name:" + daoPackage);
                    result.setDaoPackage(daoPackage);

                    //reads next line
                    line = br.readLine();

                    continue;
                }

                //domain name
                if (line.trim().startsWith("<domainPackage>")) {

                    line = line.substring(line.indexOf("<domainPackage>") + "<domainPackage>".length()).trim();
                    String domainPackage = line.trim();
                    log.debug("domain package name:" + domainPackage);
                    result.setDomainPackage(domainPackage);

                    //reads next line
                    line = br.readLine();

                    continue;
                }

                //service name
                if (line.trim().startsWith("<servicePackage>")) {

                    line = line.substring(line.indexOf("<servicePackage>") + "<servicePackage>".length()).trim();
                    String servicePackage = line.trim();
                    log.debug("service package name:" + servicePackage);
                    result.setServicePackage(servicePackage);

                    //reads next line
                    line = br.readLine();

                    continue;
                }



                //PK or normal field
                Boolean isPk = false;

                if (line.trim().startsWith("<PK>")) {
                    isPk = true;
                    line = line.substring(line.indexOf("<PK>") + "<PK>".length()).trim();
                }

                //afterwards, we need to split and have faith people read the instructions...    
                String[] field = line.trim().split("[ |\t]+");

                if (field.length > 2) {
                    throw new RuntimeException("Error, field line was nto split into to 2 elements" + line);
                }

                log.debug("Creating field with type/name:[" + field[0] + "]  [" + field[1] + "]. The field is a PK?" + isPk);

                String fieldType = field[0].trim();
                String fieldName = field[1].trim();


                if (isPk) {
                    result.getPkfields().add(new ObjectField(fieldType, fieldName));
                } else {
                    result.getFields().add(new ObjectField(fieldType, fieldName));
                }

                //reads next line
                line = br.readLine();

                continue;
            }


        } finally {
            br.close();
        }

        return result;
    }

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generateCode();

    }
}
