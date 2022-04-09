package ExportData;

import Query.GlobalConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExportDataRun {

    static String basePath = GlobalConfig.getBasePath();
    static String filePathSeparator = GlobalConfig.getPathSeparator();
    private static String rowDelimeter = "\u2087";
    private static String delimeter = "\u2088";

    public static boolean dbExists(String dbName){
        boolean dbExists = false;
        File dbFile = new File(basePath);
        String[] dbList = dbFile.list();
        for (String databaseName: dbList) {
            if(databaseName.equals(dbName))
            {
                dbExists=true;
                break;
            }
            else{
                System.out.println("Enter a valid database name");
            }
        }
       return dbExists;
    }


    public static void main (String args[]) throws IOException {
       Scanner sc = new Scanner(System.in);
       String dbName = "trial1";
       System.out.println(dbExists(dbName));
       int ch;
       if(dbExists(dbName)){
           FileReader listOfTables = new FileReader(basePath+dbName+filePathSeparator+dbName+"@tables.txt");
           //System.out.println(listOfTables);
           String fileContent = "";

           while ((ch =listOfTables.read())!=-1){
               fileContent += (char) ch;
           }
           String[] tableNames = fileContent.split(delimeter);

           System.out.println("CREATE DATABASE " + dbName+ ";");
           for (String tableName:
                tableNames) {
               String tableStructureContent ="";

               FileReader tableStructure = new FileReader(basePath+dbName+filePathSeparator+tableName+"@structure.txt");

               while ((ch =tableStructure.read())!=-1){
                   tableStructureContent += (char) ch;
               }
               String[] columnList = tableStructureContent.split(delimeter);
               String columnNames = columnList[0].substring(1, columnList[0].length() - 1);
               String columnTypes = columnList[1].substring(1, columnList[1].length() - 1);
               String[] columnNamesList = columnNames.split(",");
               String[] columnTypesList = columnTypes.split(",");
               System.out.print("CREATE TABLE " + tableName + "(" );
               for (int i =0 ; i<columnNamesList.length;i++) {
                   if(i==columnNamesList.length-1) {
                       System.out.print(columnNamesList[i] + " " + columnTypesList[i]);
                   }else {
                       System.out.print(columnNamesList[i] + " " + columnTypesList[i] + ",");
                   }
               }
               System.out.println(");");


               String tableInsertContent ="";


               FileReader tableInsertFile = new FileReader(basePath+dbName+filePathSeparator+tableName+".txt");

               while ((ch =tableInsertFile.read())!=-1){
                   tableInsertContent += (char) ch;
               }
               String[] columnList1 = tableInsertContent.split(delimeter);
               String columnNames1 = columnList1[0].substring(1, columnList1[0].length() - 1);
               String[] columnNamesList1 = columnNames1.split(rowDelimeter);
               String columnTypes1 = columnList1[1].substring(1, columnList1[1].length() - 1);
               String[] columnTypesList1 = columnTypes1.split(rowDelimeter);
               System.out.print("INSERT INTO " + tableName + " VALUES (" );
               for (int i =0 ; i<columnNamesList1.length;i++) {
                   if(i==columnNamesList1.length-1) {
                       System.out.print(columnNamesList1[i] + " " + columnTypesList1[i]);
                   }else {
                       System.out.print(columnNamesList1[i] + " " + columnTypesList1[i] + ",");
                   }
               }
               System.out.println(");");

           }

       }




    }

}
