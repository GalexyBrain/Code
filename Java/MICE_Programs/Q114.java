package progs;
import java.sql.*;
import java.io.*;


public class Q114 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			int x;
			double y;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			System.out.println("Enter an item number : ");
			x = Integer.parseInt(br.readLine());
			System.out.println("Enter the new price : ");
			y = Double.parseDouble(br.readLine());
			String query = "update items set price = " + y + "where itemno = " + x;
			st.executeUpdate(query);
			cn.close();
			System.out.println("Price of item changed haveing item number - " + x + " - Changed to - Rs." + y);
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}