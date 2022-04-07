import java.io.IOException;
import java.util.Scanner;

public class user {

   static String username;
   static String password;
   static String securityAnswer;

    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Press 1 for Logging into the system ");
        System.out.println("Press 2 for User Registration");
        int option = scanner.nextInt();
        if (option == 1){
            System.out.println("Enter username: ");
            username=scanner.next();
            System.out.println("Enter password: ");
            password=scanner.next();
            System.out.println("Security Question1: What is the name of your favourite pet?");
            securityAnswer = scanner.next();
            int loginClass = userLogin.login(username,password,securityAnswer);
            if(loginClass == 0)
            {
                System.out.println("No User exists!");
                System.out.println("Do you want to signup?(y/n)");
                String choice =scanner.next();
                if(choice.contentEquals("y")||choice.contentEquals("Y"))
                {
                    int signUpClass= userRegistration.signUp(username,password,securityAnswer);
                    if(signUpClass == 0)
                    {
                        System.out.println("UserName already exists");    //username exists
                    }
                    else
                    {
                        System.out.println("New User Created");
                        //query.parse(username);
                    }
                }
                else
                {
                    System.out.println("Terminated!");
                }
            }
            else  //user successfully logged in
            {
                //Link user to sql processing in a new class
            }

        }
        else if (option == 2){

            int signUpClass= userRegistration.signUp(username,password,securityAnswer);
            if(signUpClass == 0)
            {
                System.out.println("UserName already exists");    //username exists
            }
            else
            {
                System.out.println("New User Created");
                //query.parse(username);
            }
            signUpClass= userRegistration.signUp(username,password,securityAnswer);
        }
    }
}