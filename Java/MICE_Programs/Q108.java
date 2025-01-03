package progs;
import java.sql.*;

public class Q108 {

	public static void main(String[] args) {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "insert into items values(2, 'Cinthol', 'A', 50, 16)";
			st.executeUpdate(query);
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}	
	}

}