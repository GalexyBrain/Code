package progs;
import java.sql.*;

public class Q106 {

	public static void main(String[] args) {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "create table Trans (Titemno int not null,"
					+ "Ttype char not null,"
					+ "Tdate varchar(20),"
					+ "Tqty int,"
					+ "Tprice double)";
			st.execute(query);
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}	
	}

}