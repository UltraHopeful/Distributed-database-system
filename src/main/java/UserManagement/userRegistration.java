package UserManagement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class userRegistration {

    static String username;
    static String password;
    static String securityAnswer;


    public static int signUp(String username, String password, String securityAnswer) throws IOException {

        try {
            File file = new File("User_Profile.txt");
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            FileWriter fileWriter = new FileWriter(file, true);   //true means while is appended
            Scanner scanner=new Scanner(System.in);
            String line=bufferedReader.readLine();
            while(line!=null)
            {
                if(username.equalsIgnoreCase(line))
                {
                    return 0;
                }
                line= bufferedReader.readLine();
            }

            fileWriter.append(username);
            fileWriter.append("\n");
            fileWriter.append(password);
            fileWriter.append("\n");
            fileWriter.append(securityAnswer);
            fileWriter.append("\n");
            fileWriter.append("\n");
            fileWriter.close();
            bufferedReader.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return 1;
    }
}