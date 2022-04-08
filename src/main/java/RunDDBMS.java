import Query.QueryCheck;

import java.util.Scanner;

public class RunDDBMS {
	static RunDDBMS runDDBMS = new RunDDBMS();
	static Scanner scanner = new Scanner(System.in);

	static QueryCheck queryParse = new QueryCheck();
	static DataModelRun dataModelRun = new DataModelRun();
	public static void main(String[] args) {
		userManage();
	}

	public static void userManage(){
		try {
			System.out.println("#### Welcome ####");
			System.out.println("Choose from one of the operation");
			System.out.println("1. Login");
			System.out.println("2. Registration");
			String input = scanner.nextLine();
			switch (input) {
				case "1":
					runDDBMS.afterLogin();
					break;
				case "2":
					// registration
					break;
				default:
					// invalid
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void afterLogin() {
		System.out.println("1. Write Query");
		System.out.println("2. Export SQL Dump");
		System.out.println("3. Generate Data Model");
		System.out.println("4. Analytics");
		System.out.println("5. Logout");
		String input = scanner.nextLine();
		switch (input) {
		case "1":
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
					operationMenu(username);

				}
			}

				case 2:
				{
					// SQL Dump
					break;
				}

				case 3:
				{
					String erdString = scanner.nextLine();
					dataModelRun.generateERD(erdString);
					// Data Model
					break;
				}

				case 4:
				{
					// Analytics
					break;
				}

				case 5:
				{
					userManage();
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
	}

