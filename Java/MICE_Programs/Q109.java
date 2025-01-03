package progs;
import java.sql.*;
import java.io.*;

public class Q109 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		String v1,v2,v3,v4,v5;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			System.out.println("Enter Item number : ");
			v1 = br.readLine();
			System.out.println("Enter Item name : ");
			v2 = "'" + br.readLine() + "'";
			System.out.println("Enter Grade : ");
			v3 = "'" + br.readLine() + "'";
			System.out.println("Enter Quantity : ");
			v4 = br.readLine();
			System.out.println("Enter Price : ");
			v5 = br.readLine();
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "insert into items values(" + v1 + "," + v2 + "," + v3 + "," + v4 + ","+ v5 + ")";
			st.executeUpdate(query);
			cn.close();
			System.out.println("Successfully updated to items table");
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
	}
}