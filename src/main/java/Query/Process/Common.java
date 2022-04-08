package Query.Process;

import Query.GlobalConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Common {

    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimiter = globalConfig.getDelimiter();
    String rowDelimiter = globalConfig.getRowDelimiter();


    public boolean tableCheck(String tableName) {
        boolean isTableExists = false;

        String currentDataBase = globalConfig.getGlobalDatabase();
        File tableFile = new File(basePath + currentDataBase + filePathSeparator + tableName + ".txt");
        if (tableFile.exists()) {
            isTableExists = true;
        }

        return isTableExists;
    }

    public List<String[]> getStructure(String tableName) {

        List<String[]> structureList = new ArrayList<>();

        String currentDataBase = globalConfig.getGlobalDatabase();
        String structureFile = basePath + currentDataBase + filePathSeparator + tableName + "@structure.txt";

        try {
            FileReader fileReader = new FileReader(structureFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String columns = bufferedReader.readLine();

            String[] columnList = columns.split(delimiter);
            String columnNames = columnList[0].substring(1, columnList[0].length() - 1);
            String columnTypes = columnList[1].substring(1, columnList[1].length() - 1);
            String[] columnNamesList = columnNames.split(",");
            String[] columnTypesList = columnTypes.split(",");

            structureList.add(columnNamesList);
            structureList.add(columnTypesList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException IoE) {
            IoE.printStackTrace();
        }


        return structureList;

    }

    public int getPrimaryIndex(String tableName) {

        List<String> columnNameList = new ArrayList<>();

        String currentDataBase = globalConfig.getGlobalDatabase();
        String structureFile = basePath + currentDataBase + filePathSeparator + tableName + "@structure.txt";

        try {
            FileReader fileReader = new FileReader(structureFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String columns = bufferedReader.readLine();

            String[] columnList = columns.split(delimiter);
            String columnNames = columnList[0].substring(1, columnList[0].length() - 1);
            List<String> columnNamesList = Arrays.asList(columnNames.split(","));

            String primaryKey = getPrimaryKey(tableName);

            return columnNamesList.indexOf(primaryKey);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException IoE) {
            IoE.printStackTrace();
            return -1;
        }

    }

    public String getPrimaryKey(String tableName) {

        String primaryKey = "";

        String currentDataBase = globalConfig.getGlobalDatabase();
        String keyFile = basePath + currentDataBase + filePathSeparator + tableName + "@key.txt";

        try {
            FileReader fileReader = new FileReader(keyFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String keyNames = bufferedReader.readLine();

            if (keyNames.length() > 1) {
                String[] keyNamesList = keyNames.split(delimiter);
                primaryKey = keyNamesList[0].substring(1, keyNamesList[0].length() - 1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException IoE) {
            IoE.printStackTrace();
        }

        return primaryKey;
    }

    public List<String[]> getForeignKeys(String tableName) {

        List<String[]> foreignKeyList = new ArrayList<>();

        String currentDataBase = globalConfig.getGlobalDatabase();
        String keyFile = basePath + currentDataBase + filePathSeparator + tableName + "@key.txt";

        try {
            FileReader fileReader = new FileReader(keyFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String keyNames = bufferedReader.readLine();

            if (keyNames.length() > 1) {
                String[] keyNamesList = keyNames.split(delimiter);
                if (keyNamesList.length > 1) {
                    String foreignKey = keyNamesList[1].substring(1, keyNamesList[1].length() - 1);
                    String foreignRefKey = keyNamesList[2].substring(1, keyNamesList[2].length() - 1);
                    String[] tableForeignKeyList = foreignKey.split(",");
                    String[] tableForeignRefKeyList = foreignRefKey.split(",");
                    foreignKeyList.add(tableForeignKeyList);
                    foreignKeyList.add(tableForeignRefKeyList);
                    for (int i = 0; i < tableForeignKeyList.length; i++) {
                        System.out.println("foreign key: " + tableForeignKeyList[i] + " to " + tableForeignRefKeyList[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException IoE) {
            IoE.printStackTrace();
        }

        return foreignKeyList;
    }

    public List<Object> getPrimaryKeyList(String tableName) {

        List<Object> primaryKeyList = new ArrayList<>();

        String currentDataBase = globalConfig.getGlobalDatabase();
        String keyFile = basePath + currentDataBase + filePathSeparator + tableName + ".txt";

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(keyFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String primaryKeyValues = bufferedReader.readLine();
            String[] primaryKeys = primaryKeyValues.split(delimiter);
            System.out.println("primaryKeys.length = " + primaryKeys.length);
            for(int i=1;i<primaryKeys.length;i++){
                System.out.println("primaryKeys[i] = " + primaryKeys[i]);
                String rowValuesString = primaryKeys[i];
                rowValuesString = rowValuesString.substring(1,rowValuesString.length()-1);
                String[] rowValues = rowValuesString.split(rowDelimiter);
                primaryKeyList.add(rowValues[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException IoE) {
            IoE.printStackTrace();
        }

        return primaryKeyList;
    }

}
