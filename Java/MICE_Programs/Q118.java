package progs;
import java.sql.*;
import java.io.*;

public class Q118 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		String v2,v3;
		int v1,v4;
		double v5;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			System.out.println("Enter Item number : ");
			v1 = Integer.parseInt(br.readLine());
			System.out.println("Enter Item name : ");
			v2 = br.readLine();
			System.out.println("Enter Grade : ");
			v3 = br.readLine();
			System.out.println("Enter Quantity : ");
			v4 = Integer.parseInt(br.readLine());
			System.out.println("Enter Price : ");
			v5 = Integer.parseInt(br.readLine());
			cn = DriverManager.getConnection(url,user,passwd);
			String query = "insert into items values(?,?,?,?,?)";
			PreparedStatement st = cn.prepareStatement(query);
			st.setInt(1, v1);
			st.setString(2, v2);
			st.setString(3, Character.toString(v3.charAt(0)));
			st.setInt(4, v4);
			st.setDouble(5, v5);
			st.executeUpdate();
			cn.close();
			System.out.println("Successfully updated to items table");
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
	}
}