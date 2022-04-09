package Query.Parser;

import Query.GlobalConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimeter = globalConfig.getDelimiter();

    public boolean check(String queryString) {
        boolean isValidQuery = false;

        List<String> tableColumnName = new ArrayList<>();
        List<String> tableColumnType = new ArrayList<>();
        List<String> primaryKeys = new ArrayList<>();
        List<String> foreignKeys = new ArrayList<>();
        List<String> foreignRefKeys = new ArrayList<>();

        String currentDataBase;

        String createParseRegex = "(?:create)\\s+(?:table|database)(?:\\s+(?:IF NOT EXISTS))?\\s+(\\w*)(;|([^;]*))";

        String structureValueRegex = "(?>(\\w*|(?>primary key)|(?>foreign key))\\s+(int|varchar|text|boolean|float|(?:[(](\\w*)[)]))(?:\\s+(?:references)\\s+(\\w*)\\s+(?:[(](\\w*)[)])|,))";

        Pattern createParsePattern = Pattern.compile(createParseRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        Pattern structureParsePattern = Pattern.compile(structureValueRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        // group 1 - database name or table name
        // group 2 - if ; then it's valid for database
        // group 3 - if it's contains (table structure)
        Matcher createParseMatcher = createParsePattern.matcher(queryString);

        if (queryString.toLowerCase().contains("database")) {
            System.out.println("database detected");
            if (createParseMatcher.find()) {
                if (createParseMatcher.group(2).equals(";")) {
                    String dbName = createParseMatcher.group(1).toLowerCase();
                    if(!dbName.isBlank() && !dbName.isEmpty()) {
                        System.out.println("Database Name " + dbName);
                        File folder = new File(basePath + dbName);
                        if (folder.exists()) {
                            System.out.println("Database named " + dbName + " already exists");
                        } else {
                            folder.mkdirs();
                            System.out.println("Database Created successfully");
                        }
                        isValidQuery = true;
                    }
                    else{
                        System.out.println("Db name is empty.");
                        isValidQuery = false;
                    }
                } else {
                    System.out.println("invalid database query");
                    isValidQuery = false;
                }
            }
        }
        //
        // table key structure text file
//        primaryKey=id
//        foreigeKey=[salaryId]
//        foreigeKeyRef=[salary.id]
        else if (queryString.toLowerCase().contains("table")) {
            currentDataBase = globalConfig.getGlobalDatabase();
            System.out.println("table detected");
            System.out.println("currentDataBase = " + currentDataBase);
            if (currentDataBase != null) {
                if (createParseMatcher.find()) {
                    if (!createParseMatcher.group(2).equals(";")) {
                        System.out.println("valid table query");
                        String tableName = createParseMatcher.group(1).toLowerCase();
                        System.out.println("Table Name " + tableName);
                        String structure = createParseMatcher.group(3).toLowerCase();
                        structure = structure.trim();
                        structure = structure.substring(1, structure.length() - 1);
                        System.out.println("structure = " + structure);
                        String[] structureList = structure.split(",");
                        Matcher structureParseMatcher = structureParsePattern.matcher(structure);
                        System.out.println(structureList.length);
//                    System.out.println(structureParseMatcher.results().count());
                        if (structureList.length == structureParseMatcher.results().count()) {
                            System.out.println("valid");
                            structureParseMatcher.reset();
                            while (structureParseMatcher.find()) {
                                if (structureParseMatcher.group(1).equals("primary key")) {
                                    System.out.println("Primary Key");
                                    String primaryKey = structureParseMatcher.group(3).trim();
                                    System.out.println("structureParseMatcher.group(3) = " + primaryKey);
                                    primaryKeys.add(primaryKey);
                                } else if (structureParseMatcher.group(1).equals("foreign key")) {
                                    System.out.println("Foreign Key");
                                    String foreignKey= structureParseMatcher.group(3).trim();
                                    String foreignRefKey = structureParseMatcher.group(4)+"."+structureParseMatcher.group(5).trim();
                                    System.out.println("structureParseMatcher.group(3) = " + foreignKey);
                                    foreignKeys.add(foreignKey);
                                    System.out.println("structureParseMatcher.group(4) = " + foreignRefKey);
                                    foreignRefKeys.add(foreignRefKey);
                                } else {
                                    System.out.println("Data column value");
                                    String columnName = structureParseMatcher.group(1).trim();
                                    String columnType = structureParseMatcher.group(2).trim();
                                    System.out.println("columnName = " + columnName);
                                    System.out.println("columnType = " + columnType);
                                    tableColumnName.add(columnName);
                                    tableColumnType.add(columnType);
                                }
                                writeTable(currentDataBase,tableName,tableColumnName);
                                writeStructure(currentDataBase,tableName,tableColumnName,tableColumnType);
                                writeKey(currentDataBase,tableName,primaryKeys,foreignKeys,foreignRefKeys);

                            }
                            isValidQuery = true;
                        } else {
                            System.out.println("Invalid query");
                            isValidQuery = false;
                        }
//                    for(String structureValue:structureList){
//                        structureValue = structureValue.trim();
//                        System.out.println("structureValue = " + structureValue);
//                        String[] structureColumn = structureValue.trim().split("\s");
//                        if(structureColumn.length > 1){
//                            if(structureColumn[1].matches("int|varchar|text|boolean|float")){
//                                System.out.println(structureColumn[1]+" "+structureColumn[2]);
//                            } else if(structureColumn[0].equals("primary") && structureColumn[1].equals("key")){
//                                System.out.println(structureColumn[2]);
//                            } else if(structureColumn[0].equals("foreign") && structureColumn[1].equals("key")){
//                                System.out.println("column"+structureColumn[2]+" table"+structureColumn[4]+" column"+structureColumn[5]);
//                            }
//                        }
//                        else{
//                            System.out.println("Invalid query");
//                            isValidQuery = false;
//                            break;
//                        }
//
//                    }
                    } else {
                        System.out.println("invalid table query");
                        isValidQuery = false;
                    }
                } else {
                    isValidQuery = false;
                }
            } else {
                System.out.println("Please set default schema/database first");
                System.out.println("enter command : \n use <database_name>;");
            }

        }
        return isValidQuery;
    }

    public boolean writeTable(String dbName, String tableName, List<String> tableColumnName) {
        boolean isWritten = false;

        String fileName = basePath + dbName + filePathSeparator + "" + tableName + ".txt";
        System.out.println("fileName = " + fileName);

        try {
            PrintWriter fileWriter = new PrintWriter(new File(fileName), "UTF-8");
            fileWriter.print(tableColumnName.toString()+delimeter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isWritten;
    }

    public boolean writeStructure(String dbName, String tableName, List<String> tableColumnName, List<String> tableColumnType) {
        boolean isWritten = false;

        String fileName = basePath + dbName + filePathSeparator + "" + tableName + "@structure.txt";
        System.out.println("fileName = " + fileName);

        try {
            PrintWriter fileWriter = new PrintWriter(new File(fileName), "UTF-8");
            fileWriter.print(tableColumnName.toString()+delimeter+tableColumnType.toString());
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isWritten;
    }

    public boolean writeKey(String dbName, String tableName, List<String> primaryKeys, List<String> foreignKeys, List<String>
            foreignRefKeys) {
        boolean isWritten = false;

        String fileName = basePath + dbName + filePathSeparator + "" + tableName + "@key.txt";
        System.out.println("fileName = " + fileName);

        try {
            PrintWriter printWriter = new PrintWriter(new File(fileName), "UTF-8");
            printWriter.print(primaryKeys.toString()+delimeter+foreignKeys.toString()+delimeter+foreignRefKeys.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return isWritten;
    }

}
