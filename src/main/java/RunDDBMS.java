import DataModel.DataModelRun;
import ExportData.ExportDataRun;
import Query.QueryCheck;
import UserManagement.UserLogin;
import UserManagement.UserRegistration;

import java.io.IOException;
import java.util.Scanner;

public class RunDDBMS {

	static Scanner scanner = new Scanner(System.in);

	static QueryCheck queryParse = new QueryCheck();
	static DataModelRun dataModelRun = new DataModelRun();
	public static void main(String[] args) throws IOException {
		userManage();
	}

	public static void userManage() throws IOException {
		System.out.println("Please select one of the options below:");
		System.out.println("1. LOGIN USER");
		System.out.println("2. REGISTER NEW USER");
		System.out.println("3. EXIT");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();

		switch (option) {
			case 1 -> {
				if(UserLogin.loginUser()){
					operationMenu();
				}
				userManage();
				break;
			}
			case 2 -> {
				UserRegistration.registerUser();
				userManage();
				break;
			}
			case 3 -> {
				System.out.println("Exiting the application...");
				System.exit(0);
				break;
			}
			default -> {
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
		Scanner scanner1 = new Scanner(System.in);
		int option = scanner1.nextInt();

		switch (option)
		{
			case 1:
			{
				queryParse.queryFormatPrint();
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

				queryParse.queryCheck(queryString);
				operationMenu();
				break;
			}

			case 2:
			{
				System.out.println("Enter a Database you want to Export");
				String dbName = scanner.nextLine();
				ExportDataRun.createDB(dbName);
				break;
			}

			case 3:
			{
				String erdString = scanner.nextLine();
				dataModelRun.generateERD(erdString);
				break;
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
				operationMenu();
				break;
			}
		}

		System.out.println("Do you want to stay on our application and perform more actions?");
		System.out.println("Type yes or no");
		Scanner scanner2 = new Scanner(System.in);
		String operation = scanner2.nextLine();

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
	}
}
