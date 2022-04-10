package UserManagement;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class userLogin {

    public static int login(String username, String password, String securityQuestion) {
        try {

            File file = new File("User_Profile.txt");  //default login text file
            if (file.createNewFile())   //if no file exists, we create one
            {
                System.out.println("New login file created!");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));

            String line = bufferedReader.readLine();   //read the text file line by line
            int flag = 0;  //if 0 after the loop, no UserManagement.user found
            while (line != null) {
                if (username.contentEquals(line))
                {

                    line = bufferedReader.readLine();
                    if (password.contentEquals(line))
                    {
                        flag =1;
                        line = bufferedReader.readLine();

                        
                        if (securityQuestion.contentEquals(line))
                        {
                            System.out.println("Successful login");
                        }
                        else{
                            System.out.println("Wrong Answer");
                        }
                    }
                    else {
                        System.out.println("Wrong Password!Try again!");
                    }
                }
                line = bufferedReader.readLine();
            }
            if (flag == 0)
            {
                return 0;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

}


