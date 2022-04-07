import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserProfile
{

    static Scanner input = new Scanner(System.in);

    public static void mainMenu() throws IOException {
        System.out.println("Please select one of the options below:");
        System.out.println("1. LOGIN USER");
        System.out.println("2. REGISTER NEW USER");
        System.out.println("3. EXIT");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                operationMenu(UserLogin.loginUser());
                break;
            }
            case 2 -> {
                UserRegistration.registerUser();
                mainMenu();
                break;
            }
            case 3 -> {
                System.out.println("Exiting the application...");
                System.exit(0);
            }
            default -> {
                System.out.println("You entered an invalid input.");
                mainMenu();
            }
        }
    }

    public static void operationMenu(String username)
    {
        System.out.println("\nPlease choose one of the following options:");
        System.out.println("1. WRITE QUERIES");
        System.out.println("2. EXPORT");
        System.out.println("3. DATA MODEL");
        System.out.println("4. ANALYTICS");
        System.out.println("5. SHOW LOGS");
        Scanner scanner1 = new Scanner(System.in);
        int option = scanner1.nextInt();

        switch (option)
        {
            case 1:
            {

            }

            case 2:
            {

            }

            case 3:
            {

            }

            case 4:
            {

            }

            case 5:
            {

            }

            default:
            {
                System.out.println("You entered an invalid input.");
                operationMenu(username);
            }
        }

        System.out.println("Do you want to stay on our application and perform more actions?");
        System.out.println("Type yes or no");
        Scanner scanner2 = new Scanner(System.in);
        String operation = scanner2.nextLine();

        if(operation.equalsIgnoreCase("yes"))
        {
            operationMenu(username);
        }
        else if(operation.equalsIgnoreCase("no"))
        {
            System.out.println("Exiting the application...");
            System.exit(0);
        }
        else
        {
            System.out.println("Invalid entry. Please enter either yes or no to continue!");
        }
    }

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

    public static void main(String args[]) throws IOException {
        createUserProfileFile();
        mainMenu();
    }
}
