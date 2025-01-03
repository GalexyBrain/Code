package progs;
import java.sql.*;

public class Q107 {

	public static void main(String[] args) {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "create table Users (username varchar(20) primary key,"
					+ "password varchar(20) not null)";
			st.execute(query);
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}	
	}

}