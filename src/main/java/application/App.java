package application;

import ConnectDatabase.DBGeneration;
import ConnectDatabase.Query;

public class App {
	public static void main(String[] args) {
		Query query = new Query();
		int[] n = {2000000};
		int[] m = {4000000};
		DBGeneration dbGen = new DBGeneration();
		for(int i = 0; i < 1; i++) {
//			Tạo database
//			dbGen.genDatabase(n[i], m[i]);
//			Truy vấn
			query.Query1(dbGen.getInsertData().getConnection());
			query.Query2(dbGen.getInsertData().getConnection());
			query.Query3(dbGen.getInsertData().getConnection());		
			query.Query4(dbGen.getInsertData().getConnection());
			query.Query5(dbGen.getInsertData().getConnection());
			query.Query6(dbGen.getInsertData().getConnection());
			query.Query7(dbGen.getInsertData().getConnection());		
			query.Query8(dbGen.getInsertData().getConnection());
			query.Query9(dbGen.getInsertData().getConnection());
			query.Query10(dbGen.getInsertData().getConnection());
			query.Query11(dbGen.getInsertData().getConnection());		
			query.Query12(dbGen.getInsertData().getConnection());
			query.Query13(dbGen.getInsertData().getConnection());
			query.Query14(dbGen.getInsertData().getConnection());
			query.Query15(dbGen.getInsertData().getConnection());		
			query.Query16(dbGen.getInsertData().getConnection());
			query.Query17(dbGen.getInsertData().getConnection());
			query.Query18(dbGen.getInsertData().getConnection());
			query.Query19(dbGen.getInsertData().getConnection());		
			query.Query20(dbGen.getInsertData().getConnection());
		}
		dbGen.getInsertData().closeConnection();
	}
}
