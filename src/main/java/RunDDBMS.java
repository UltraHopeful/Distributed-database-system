import DataModel.DataModelRun;
import ExportData.ExportDataRun;
import Query.QueryCheck;
import Query.Transaction;
import UserManagement.UserLogin;
import UserManagement.UserRegistration;

import java.io.IOException;
import java.util.Scanner;

public class RunDDBMS {

	static Scanner scanner = new Scanner(System.in);

	static QueryCheck queryParse = new QueryCheck();
	static DataModelRun dataModelRun = new DataModelRun();
	static Transaction transaction = new Transaction();
	static ExportDataRun exportDataRun = new ExportDataRun();
	static UserLogin userLogin = new UserLogin();
	static UserRegistration userRegistration = new UserRegistration();
	public static void main(String[] args) throws IOException {
		userManage();
	}

	public static void userManage() throws IOException {
		System.out.println("Please select one of the options below:");
		System.out.println("1. LOGIN USER");
		System.out.println("2. REGISTER NEW USER");
		System.out.println("3. EXIT");
//		Scanner sc = new Scanner(System.in);
		int option = scanner.nextInt();

		switch (option) {
			case 1 : {
				if(userLogin.loginUser()){
					operationMenu();
				}
				userManage();
				break;
			}
			case 2 : {
				userRegistration.registerUser();
				userManage();
				break;
			}
			case 3 : {
				System.out.println("Exiting the application...");
				System.exit(0);
				break;
			}
			default : {
				System.out.println("You entered an invalid input.");
				userManage();
				break;
			}
		}
	}

	public static void operationMenu() throws IOException {
		System.out.println("\nPlease choose one of the following options:");
		System.out.println("1. WRITE QUERIES");
		System.out.println("2. EXPORT");
		System.out.println("3. DATA MODEL");
		System.out.println("4. ANALYTICS");
		System.out.println("5. SHOW LOGS");
		System.out.println("6. TRANSACTION");
//		Scanner scanner1 = new Scanner(System.in);
		int option = scanner.nextInt();

		switch (option)
		{
			case 1:
			{
				queryParse.queryFormatPrint();
				scanner = new Scanner(System.in);
				String queryString = scanner.nextLine();
//			while(true){
//				if(queryParse.queryCheck(queryString)){
//					break;
//				}
//				else{
//					System.out.println("Another Try");
//					queryParse.queryFormatPrint();
//					queryString = scanner.nextLine();
//					queryParse.queryCheck(queryString);
//				}
//			}

				queryParse.queryCheck(queryString, true);
				operationMenu();
				break;
			}

			case 2:
			{
				System.out.println("Enter a Database you want to dump as sql");
				scanner = new Scanner(System.in);
				String dbName = scanner.nextLine();
				exportDataRun.generateDump(dbName);
				break;
			}

			case 3:
			{
				System.out.println("Enter a Database name to generate entity relation diagram");
				scanner = new Scanner(System.in);
				String erdString = scanner.nextLine();
				dataModelRun.generateERD(erdString);
				break;
			}

			case 4:

			case 5: {
				break;
			}

			case 6:
			{
				transaction.getTransaction();
				break;
			}

			default:
			{
				System.out.println("You entered an invalid input.");
				operationMenu();
				break;
			}
		}
//		scanner1.close();
		System.out.println("Do you want to stay on our application and perform more actions?");
		System.out.println("Type yes or no");
//		Scanner scanner2 = new Scanner(System.in);
		scanner = new Scanner(System.in);
		String operation = scanner.nextLine();

		if(operation.equalsIgnoreCase("yes"))
		{
			operationMenu();
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
		
//		scanner2.close();
		
	}
}
