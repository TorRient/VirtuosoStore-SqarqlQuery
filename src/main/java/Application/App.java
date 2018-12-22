package Application;

import InsertData.InsertDB;
import Query.Query;

public class App {
	public static void main(String[] args) {
		Query query = new Query();
		int[] n = {100,5000,60000,1000000,15000000}; // n thực thể
		int[] m = {200,7000,80000,2000000,17000000}; // m quan hệ
		InsertDB insert = new InsertDB();
		for(int i = 0; i < 5; i++) {
//			Tạo database
			insert.insertDatabase(n[i], m[i]);
//			Truy vấn
			query.Query1(insert.getDBGen().getConnection());
			query.Query2(insert.getDBGen().getConnection());
			query.Query3(insert.getDBGen().getConnection());		
			query.Query4(insert.getDBGen().getConnection());
			query.Query5(insert.getDBGen().getConnection());
			query.Query6(insert.getDBGen().getConnection());
			query.Query7(insert.getDBGen().getConnection());		
			query.Query8(insert.getDBGen().getConnection());
			query.Query9(insert.getDBGen().getConnection());
			query.Query10(insert.getDBGen().getConnection());
			query.Query11(insert.getDBGen().getConnection());		
			query.Query12(insert.getDBGen().getConnection());
			query.Query13(insert.getDBGen().getConnection());
			query.Query14(insert.getDBGen().getConnection());
			query.Query15(insert.getDBGen().getConnection());		
			query.Query16(insert.getDBGen().getConnection());
			query.Query17(insert.getDBGen().getConnection());
			query.Query18(insert.getDBGen().getConnection());
			query.Query19(insert.getDBGen().getConnection());		
			query.Query20(insert.getDBGen().getConnection());
			System.out.print("Xong lần ");
			System.out.println(i + 1);
		}
		// đóng kết nối
		insert.getDBGen().closeConnection();
	}
}
