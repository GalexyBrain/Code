import java.sql.*;

public class Q105 {

	public static void main(String[] args) {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "Thejus";
			passwd = "root";
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			String query = "create table Items (itemno int primary key,"
					+ "itemname varchar(20) not null,"
					+ "grade char(1),"
					+ "qty int,"
					+ "price double)";
			st.execute(query);
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}	
	}

}