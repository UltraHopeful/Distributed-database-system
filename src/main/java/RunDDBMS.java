import java.util.Scanner;

public class RunDDBMS {
	static RunDDBMS runDDBMS = new RunDDBMS();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
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
			queryInit(input);
			break;
		case "2":
			// SQL Dump
			break;
		case "3":
			// Data Model
			break;
		case "4":
			// Analytics
			break;
		case "5":
			// Logout
			break;
		default:
			//invalid
			break;
		}
	}

	public void queryInit(String input) {
		String[] queryType = input.trim().split(" ");
		switch (queryType[0]) {
		case "create":
			//createParser
			break;
		case "update":
			//updateParser
			break;
		case "select":
			//selectParser
			break;
		case "insert":
			//insertParser
			break;
		case "delete":
			//deleteParser
			break;
		case "drop":
			//dropParser
			break;
		default:
			//invalid
			break;
		}
	}
	
}
