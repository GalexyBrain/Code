package progs;
import java.sql.*;
import java.io.*;


public class Q115 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "update items set grade = ucase(grade)";
			st.executeUpdate(query);
			cn.close();
			System.out.println("Grade changed to upper case");
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}