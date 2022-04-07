package Query.Parser;

import java.awt.*;
import java.io.File;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    public boolean check(String queryString){
        boolean result = false;
        String pathSeparator = File.separator;

        String createParseRegex = "(?:create)\\s+(?:table|database)(?:\\s+(?:IF NOT EXISTS))?\\s+(\\w*)(;|([^;]*))";

        String structureValueRegex = "(?>(\\w*|(?>primary key)|(?>foreign key))\\s+(int|varchar|text|boolean|float|(?:[(](\\w*)[)]))(?:\\s+(?:references)\\s+(\\w*)\\s+(?:[(](\\w*)[)])|,))";

        Pattern createParsePattern = Pattern.compile(createParseRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        Pattern structureParsePattern = Pattern.compile(structureValueRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

        // group 1 - database name or table name
        // group 2 - if ; then it's valid for database
        // group 3 - if it's contains (table structure)
        Matcher createParseMatcher = createParsePattern.matcher(queryString);

        if(queryString.toLowerCase().contains("database")){
            System.out.println("database detected");
            if(createParseMatcher.find()) {
                if (createParseMatcher.group(2).equals(";")) {
                    String dbName = createParseMatcher.group(1).toLowerCase();
                    System.out.println("Database Name " + dbName);
                    File folder = new File("system"+pathSeparator+""+dbName);
                    if(dbName=="" && folder.exists()){
                        System.out.println("Database named "+dbName+" already exists");
                    }
                    else{
                        folder.mkdirs();
                        System.out.println("Database Created successfully");
                    }
                    result = true;
                } else {
                    System.out.println("invalid database query");
                    result = false;
                }
            }
        }
        //
        // table key structure text file
//        primaryKey=id
//        foreigeKey=[salaryId]
//        foreigeKeyRef=[salary.id]
        else if(queryString.toLowerCase().contains("table")){
            System.out.println("table detected");
            if(createParseMatcher.find()) {
                if (!createParseMatcher.group(2).equals(";")) {
                    System.out.println("valid table query");
                    String tableName = createParseMatcher.group(1).toLowerCase();
                    System.out.println("Table Name " + tableName);
                    String structure = createParseMatcher.group(3).toLowerCase();
                    structure = structure.trim();
                    structure = structure.substring(1,structure.length()-1);
                    System.out.println("structure = " + structure);
                    String[] structureList = structure.split(",");
                    Matcher structureParseMatcher = structureParsePattern.matcher(structure);
                    System.out.println(structureList.length);
//                    System.out.println(structureParseMatcher.results().count());
                    if(structureList.length == structureParseMatcher.results().count()){
                        System.out.println("valid");
                          structureParseMatcher.reset();
                          while(structureParseMatcher.find()){
                              if(structureParseMatcher.group(1).equals("primary key")){
                                  System.out.println("structureParseMatcher.group(3) = " + structureParseMatcher.group(3));
                              } else if(structureParseMatcher.group(1).equals("foreign key")){
                                  System.out.println("structureParseMatcher.group(3) = " + structureParseMatcher.group(3));
                              } else{
                                  System.out.println("structureParseMatcher.group(3) = " + structureParseMatcher.group(1));
                                  System.out.println("structureParseMatcher.group(2) = " + structureParseMatcher.group(2));
                              }
                          }
                    }
                    else{
                        System.out.println("Invalid query");
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
//                            result = false;
//                            break;
//                        }
//
//                    }
                    result = true;
                } else {
                    System.out.println("invalid table query");
                    result = false;
                }
            }
        }
        else{
            result = false;
        }


        return  result;
    }
}
