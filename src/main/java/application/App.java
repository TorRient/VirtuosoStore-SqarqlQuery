package application;

import insertdata.InsertDB;
import query.Query;

public class App {
	public static void main(String[] args) {
		Query query = new Query();
		int[] n = {100,5000,60000,1000000,15000000}; // n thực thể
		int[] m = {200,7000,80000,2000000,17000000}; // m quan hệ
		InsertDB insert = new InsertDB();
		for(int i = 0; i < 1; i++) {
//			Tạo database
//			insert.insertDatabase(n[i], m[i]);
//			Truy vấn
			System.out.println("Query 1: ");
			query.Query1(insert.getDBGen().getConnection());
			System.out.println("Query 2: ");
			query.Query2(insert.getDBGen().getConnection());
			System.out.println("Query 3: ");
			query.Query3(insert.getDBGen().getConnection());
			System.out.println("Query 4: ");
			query.Query4(insert.getDBGen().getConnection());
			System.out.println("Query 5: ");
			query.Query5(insert.getDBGen().getConnection());
			System.out.println("Query 6: ");
			query.Query6(insert.getDBGen().getConnection());
			System.out.println("Query 7: ");
			query.Query7(insert.getDBGen().getConnection());
			System.out.println("Query 8: ");
			query.Query8(insert.getDBGen().getConnection());
			System.out.println("Query 9: ");
			query.Query9(insert.getDBGen().getConnection());
			System.out.println("Query 10: ");
			query.Query10(insert.getDBGen().getConnection());
			System.out.println("Query 11: ");
			query.Query11(insert.getDBGen().getConnection());
			System.out.println("Query 12: ");
			query.Query12(insert.getDBGen().getConnection());
			System.out.println("Query 13: ");
			query.Query13(insert.getDBGen().getConnection());
			System.out.println("Query 14: ");
			query.Query14(insert.getDBGen().getConnection());
			System.out.println("Query 15: ");
			query.Query15(insert.getDBGen().getConnection());
			System.out.println("Query 16: ");
			query.Query16(insert.getDBGen().getConnection());
			System.out.println("Query 17: ");
			query.Query17(insert.getDBGen().getConnection());
			System.out.println("Query 18: ");
			query.Query18(insert.getDBGen().getConnection());
			System.out.println("Query 19: ");
			query.Query19(insert.getDBGen().getConnection());
			System.out.println("Query 20: ");
			query.Query20(insert.getDBGen().getConnection());
		}
		// đóng kết nối
		insert.getDBGen().closeConnection();
	}
}
