import Query.QueryCheck;

import java.util.Scanner;

public class RunDDBMS {
	static RunDDBMS runDDBMS = new RunDDBMS();
	static Scanner scanner = new Scanner(System.in);

	static QueryCheck queryParse = new QueryCheck();

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
			afterLogin();
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
			userManage();
		default:
			//invalid
			break;
		}
	}
}
