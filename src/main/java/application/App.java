package application;

import ConnectDatabase.DBGeneration;
import ConnectDatabase.Query;

public class App {
	public static void main(String[] args) {
//		System.out.println("hoangf");
		int[] n = {1000000};
		int[] m = {1000000};
//		long startTime = System.currentTimeMillis();
		DBGeneration dbGen = new DBGeneration();
		dbGen.clearStatements();
//		long endTime = System.currentTimeMillis();
//		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 1; i++) {
//			System.out.println(i);
			dbGen.genDatabase(n[i], m[i]);
//			long time = databaseGeneration.getDataAccessObject().queryStatementTime(null, RDF.TYPE, databaseGeneration.getDataAccessObject().getPersonType(), null);
//			System.out.println("Time to query in " + n[i] + " entities and " + m[i] + " relationship is " + time);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
		Query query = new Query();
//		query.Query1(dbGen.getInsertData().getConnection());
//		query.Query2(dbGen.getInsertData().getConnection());
		query.Query3(dbGen.getInsertData().getConnection());		
//		query.Query6(dbGen.getInsertData().getConnection());
		dbGen.getInsertData().closeConnection();
	}
}
