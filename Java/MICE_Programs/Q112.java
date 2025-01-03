package progs;
import java.sql.*;
import java.io.*;


public class Q112 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			String x, y;
			boolean flag = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			System.out.println("Enter an item name / part of it : ");
			x = br.readLine().toLowerCase();
			String query = "select * from items";
			ResultSet rs = st.executeQuery(query);
			System.out.println("---------------------------------------------------------------");
			System.out.println("Item no\t\tItem Name\tGrade\tQuantity\tPrice");
			System.out.println("---------------------------------------------------------------");
			while (rs.next()){
				y = rs.getString(2).toLowerCase();
				if (y.indexOf(x) >= 0){
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t\t" + rs.getDouble(5));
					flag = true;
				}
			}
			cn.close();
			if (flag == false){
				System.out.println("No record found for the given item name");
			}
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}