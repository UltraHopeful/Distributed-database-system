package DataModel;

import Query.GlobalConfig;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class DataModelRun {
    GlobalConfig globalConfig = new GlobalConfig();
    String basePath = globalConfig.getBasePath();
    String filePathSeparator = globalConfig.getPathSeparator();
    String delimeter = globalConfig.getDelimeter();

    public void generateERD(String dbName) {

        try {
            String path = basePath + dbName;
            String erdFilename= basePath + dbName + "@ERD.txt";
            PrintWriter printWriter = new PrintWriter(new File(erdFilename));
            File folder = new File(path);

            if (folder.exists()) {
                FileReader fileReader = new FileReader(path + filePathSeparator + dbName + "@tables.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String tables = bufferedReader.readLine();

                String[] tableList = tables.split(delimeter);
                bufferedReader.close();
                fileReader.close();

                printWriter.println("#######################DATABASE NAME#######################");
                printWriter.println(dbName);
                for (String table : tableList) {
                    String structure = path + filePathSeparator + table + "@structure.txt";
                    String key = path + filePathSeparator + table + "@key.txt";
                    fileReader = new FileReader(structure);
                    printWriter.println(" ");
                    printWriter.println(" ");
                    printWriter.println("#######################TABLE NAME#######################");
                    printWriter.println(table);
                    printWriter.println("");
                    bufferedReader = new BufferedReader(fileReader);
                    String columns = bufferedReader.readLine();

                    String[] columnList = columns.split(delimeter);
                    String columnNames = columnList[0].substring(1, columnList[0].length() - 1);
                    String columnTypes = columnList[1].substring(1, columnList[1].length() - 1);
                    String[] columnNamesList = columnNames.split(",");
                    String[] columnTypesList = columnTypes.split(",");

                    printWriter.println("######STRUCTURE######\n");
                    for (int i = 0; i < columnNamesList.length; i++) {
                        printWriter.println("column name: " + columnNamesList[i]);
                        printWriter.println("column type: " + columnTypesList[i]);
                        printWriter.println(" ");
                    }

                    bufferedReader.close();
                    fileReader.close();

                    fileReader = new FileReader(key);
                    bufferedReader = new BufferedReader(fileReader);
                    String keyName = bufferedReader.readLine();

                    if (keyName.length() > 1) {
                        String[] keynames = keyName.split(delimeter);
                        printWriter.println("");
                        printWriter.println("######KEY######\n");
                        String primaryKey1 = keynames[0].substring(1, keynames[0].length() - 1);
                        printWriter.println("primary key: " + primaryKey1);
                        if (keynames.length > 1) {
                            String foreignKey = keynames[1].substring(1, keynames[1].length() - 1);
                            String foreignRefKey = keynames[2].substring(1, keynames[2].length() - 1);
                            String[] foreignKeyList = foreignKey.split(",");
                            String[] foreignRefKeyList = foreignRefKey.split(",");
                            for (int i = 0; i < foreignKeyList.length; i++) {
                                printWriter.println("foreign key: " + foreignKeyList[i] + " to " + foreignRefKeyList[i]);
                            }

                        }

                    }
                    printWriter.println("\n####################################################");
                }

                printWriter.close();
                File file = new File(erdFilename);
                System.out.println("File successfully saved at " + file.getAbsolutePath());

            } else {
                System.out.println("Database is not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
