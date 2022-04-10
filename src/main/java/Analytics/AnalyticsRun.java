package Analytics;

import java.io.*;
import java.util.*;
import Query.GlobalConfig;

public class AnalyticsRun {
	List<String[]> query_analytics_data = new ArrayList<>();

	private GlobalConfig globalConfig = new GlobalConfig();
	private String delimiter = globalConfig.getDelimiter();
	private String valuedelimiter = globalConfig.getRowDelimiter();

	public List<String[]> QueriesCount() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			FileReader file = new FileReader("./system/@Log/query_log.txt");
			reader = new BufferedReader(file);
			String data = reader.readLine();
			String[] dataValueArray = data.split(delimiter);
			List<String> dataValues = new ArrayList<>();
			FileWriter analysisWriter = new FileWriter("./system/@Analytics/Analysis.txt");
			writer = new BufferedWriter(analysisWriter);

			for (String row : dataValueArray) {
				dataValues.add(row);
			}
			for (String row : dataValues) {
				query_analytics_data.add(row.split(valuedelimiter));
			}
			List<String> b = new ArrayList<>();
			Map<String, List<Map<String, Integer>>> map = new HashMap<>();
			for (int i = 0; i < query_analytics_data.size(); i++) {

				String a = Arrays.toString(query_analytics_data.get(i));
				a = a.substring(1, a.length() - 1);
				String[] query_list = a.split(",");

				b = Arrays.asList(query_list);

				if (map.containsKey(query_list[1])) {
					List<Map<String, Integer>> userListM = map.get(query_list[1]);
					for (int k = 0; k < userListM.size(); k++) {
						Map<String, Integer> user = userListM.get(k);
						if (user.containsKey(query_list[0])) {
							user.replace(query_list[0], user.get(query_list[0]) + 1);
						} else {
							user.put(query_list[0], 1);
						}
					}
				} else {
					List<Map<String, Integer>> userList = new ArrayList<>();
					Map<String, Integer> user = new HashMap<>();
					user.put(query_list[0], 1);
					userList.add(user);
					map.put(query_list[1], userList);
				}
			}
			String queryAnalysis = "";
			analysisWriter.write("This is the Analytics for Query Processing");
			analysisWriter.write("\n---------------------------------------------\n");
			for (var entry : map.entrySet()) {

				for (int i = 0; i < entry.getValue().size(); i++) {
					for (var entryUser : entry.getValue().get(i).entrySet()) {
						queryAnalysis = "User " + entryUser.getKey() + " submitted " + entryUser.getValue()
								+ " queries " + "for DB " + entry.getKey() + "\n";
						analysisWriter.write(queryAnalysis);
					}
				}

			}
			analysisWriter.close();
		} catch (Exception o) {
			System.out.println("Failed to get Queries Analytics");
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				System.out.println("Failed to get Queries Analytics");
			}
		}
		return query_analytics_data;
	}

	public List<String[]> updateCount(String dbname) {
		BufferedReader reader = null;
		List<String[]> update_analytics_data = new ArrayList<>();
		try {
			FileReader fr = new FileReader("./system/@Log/event_log.txt");
			reader = new BufferedReader(fr);
			String str = "";
			String[] dataValue = str.split(delimiter);
			List<String> dataList = new ArrayList<>();
			for (String row : dataValue) {
				dataList.add(row);
			}
			for (String row : dataList) {
				update_analytics_data.add(row.split(valuedelimiter));
			}
		}

		catch (IOException f) {
			System.out.println("Failed to get Update Operations Analysis");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Failed to get Queries Analytics");
			}
		}
		return update_analytics_data;
	}

//	public static void main(String[] args) {
//		AnalyticsRun a = new AnalyticsRun();
//		// a.updateCount("db1");
//		a.QueriesCount();
//	}
}
