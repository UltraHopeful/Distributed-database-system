package UserManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserProfile
{
    public static void createUserProfileFile() throws IOException {

            File fileWriter = new File(System.getProperty("user.dir") + "/User_Profile.txt");
            if (!fileWriter.exists() && !fileWriter.isFile()) {
                boolean writerNewFile = fileWriter.createNewFile();
                if (writerNewFile)
                {
                    System.out.println();
                    System.out.println("Location of User_Profile file is at " + fileWriter.getCanonicalPath());
                    System.out.println();
                }
            }
    }

}
