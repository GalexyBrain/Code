package progs;
import java.sql.*;

public class Q110 {

	public static void main(String[] args) {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "select * from items";
			ResultSet rs = st.executeQuery(query);
			System.out.println("---------------------------------------------------------------");
			System.out.println("Item no\t\tItem Name\tGrade\tQuantity\tPrice");
			System.out.println("---------------------------------------------------------------");
			while (rs.next()){
				System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t\t" + rs.getDouble(5));
			}
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
	}

}