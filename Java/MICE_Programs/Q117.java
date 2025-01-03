package progs;
import java.sql.*;
import java.io.*;


public class Q117 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			String x;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			System.out.println("Enter a table name to delete : ");
			x = br.readLine();
			String query = "drop table if exists " + x;
			st.executeUpdate(query);
			cn.close();
			System.out.println("Table " + x + "Successfully deleted");
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
	}
}