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
import org.stringtemplate.v4.STGroupDir;

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
            STGroup group = new STGroupDir("src/main/resources");


            generateRowMapper(objectToGenerate, group);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void generateRowMapper(ObjectToGenerate objectToGenerate, STGroup group) {

        //generate rowMapper fields
        StringBuffer rowMapperSetters = new StringBuffer();
        ST st = null;

        for (ObjectField field : objectToGenerate.getAllfields()) {

            st = group.getInstanceOf("rowMapperSetField");
            group.verbose=true;

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

                if (line.trim().startsWith("<objectName>")) {

                    line = line.substring(line.indexOf("<objectName>") + "<objectName>".length()).trim();
                    String newObjectName = StringUtils.capitalize(line).trim();
                    log.debug("Overwriting object name, it was:" + result.getObjectName() + " now it will be:" + newObjectName);
                    result.setObjectName(newObjectName);

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
                String[] field = line.split("[ |\t]+");

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
