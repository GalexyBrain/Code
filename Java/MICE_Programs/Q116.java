package progs;
import java.sql.*;
import java.io.*;


public class Q116 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			int x;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			System.out.println("Enter an item number : ");
			x = Integer.parseInt(br.readLine());
			String query = "delete from items where itemno = " + x;
			st.executeUpdate(query);
			cn.close();
			System.out.println("Record of item with item number - " + x + " - successfully delete");
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}