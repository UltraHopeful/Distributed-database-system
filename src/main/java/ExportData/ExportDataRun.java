package ExportData;

import Query.GlobalConfig;

import java.io.*;
import java.util.Scanner;

public class ExportDataRun {

    static Scanner sc = new Scanner(System.in);
    static String dbName = sc.nextLine();
    static String basePath = GlobalConfig.getBasePath();
    static String filePathSeparator = GlobalConfig.getPathSeparator();
    private static String rowDelimeter = "\u2087";
    private static String delimeter = "\u2088";

    static String path = basePath + dbName;
    static String dataExport= basePath + dbName + "@dump.sql";
    static FileWriter fileWriter;
    {
        try {
            fileWriter = new FileWriter(dataExport,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDB(String dbName) throws IOException {

        File dbFile = new File(basePath);
        String[] dbList = dbFile.list();

        for (String databaseName : dbList) {

            if (databaseName.equals(dbName)) {
                fileWriter.write("\nCREATE DATABASE " + dbName + ";");
                createTable();
                break;
            }
            else {
                System.out.println("Enter a valid database name");
            }
        }
    }
    public static void createTable() throws IOException {
        String tableStructureContent = "";
        int ch;

        String [] tableNames = generateTableName();

        for (String tableName : tableNames) {
            FileReader tableStructure = new FileReader(basePath + dbName + filePathSeparator + tableName + "@structure.txt");

            while ((ch = tableStructure.read()) != -1) {
                tableStructureContent += (char) ch;
            }

            String[] columnList = tableStructureContent.split(delimeter);
            String columnNames = columnList[0].substring(1, columnList[0].length() - 1);
            String columnTypes = columnList[1].substring(1, columnList[1].length() - 1);
            String[] columnNamesList = columnNames.split(",");
            String[] columnTypesList = columnTypes.split(",");
            fileWriter.write("\nCREATE TABLE " + tableName + "(");
            for (int i = 0; i < columnNamesList.length; i++) {
                if (i == columnNamesList.length - 1) {
                    fileWriter.write(columnNamesList[i] + " " + columnTypesList[i]);
                } else {
                   fileWriter.write(columnNamesList[i] + " " + columnTypesList[i] + ",");
                }
            }
            fileWriter.write(");");
        }
        insertInto();
    }

    public static String[] generateTableName() throws IOException {
        int ch;
        FileReader listOfTables = new FileReader(basePath + dbName + filePathSeparator + dbName + "@tables.txt");
        String fileContent = "";
        while ((ch = listOfTables.read()) != -1) {
            fileContent += (char) ch;
        }
        String[] tableNames = fileContent.split(delimeter);
        return tableNames;
    }

    public static void insertInto() throws IOException {
        String tableInsertContent = "";

        String [] tableNames = generateTableName();

        int ch;

        for (String tableName : tableNames) {
            FileReader tableInsertFile = new FileReader(basePath + dbName + filePathSeparator + tableName + ".txt");

            while ((ch = tableInsertFile.read()) != -1) {
                tableInsertContent += (char) ch;
            }
            String[] columnList1 = tableInsertContent.split(delimeter);
            for (String insertValues : columnList1) {
                String columnTypes1 = insertValues.substring(1, insertValues.length() - 1);
                String[] columnTypesList1 = columnTypes1.split(rowDelimeter);
                fileWriter.write("\nINSERT INTO " + tableName + " VALUES (");
                for (int i = 0 ; i < columnTypesList1.length; i++) {
                    if (i == columnTypesList1.length - 1) {
                        fileWriter.write(columnTypesList1[i]);
                    } else {
                        fileWriter.write(columnTypesList1[i] + ",");
                    }
                }
                fileWriter.write(");");
            }
        }
        fileWriter.close();
    }
}
