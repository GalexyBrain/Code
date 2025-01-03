package progs;
import java.sql.*;

public class Q104 {

	public static void main(String[] args) {
		Connection cn;
		String s1,s2,s3;
		try{
			s1 = "jdbc:mysql://localhost:3306/dbinv";
			s2 = "jThejus";
			s3 = "root";
			cn = DriverManager.getConnection(s1,s2,s3);
			if (cn != null){
				System.out.println("Connection Success");
			}else{
				System.out.println("Connection Failure");
			}
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
	}

}
