package progs;
import java.sql.*;
import java.io.*;


public class Q113 {

	public static void main(String[] args) throws IOException {
		Connection cn;
		String url,user,passwd;
		try{
			url = "jdbc:mysql://localhost:3306/dbinv";
			user = "jThejus";
			passwd = "root";
			char x;
			boolean flag = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cn = DriverManager.getConnection(url,user,passwd);
			Statement st = cn.createStatement();
			System.out.println("Enter an item grade : ");
			x = br.readLine().charAt(0);
			String query = "select * from items where grade in('" + Character.toLowerCase(x) + "','" + Character.toUpperCase(x) + "')" ;
			ResultSet rs = st.executeQuery(query);
			System.out.println("---------------------------------------------------------------");
			System.out.println("Item no\t\tItem Name\tGrade\tQuantity\tPrice");
			System.out.println("---------------------------------------------------------------");
			while (rs.next()){
				//x = Character.toLowerCase(x);
				//y = Character.toLowerCase(rs.getString(3).charAt(0));
				//if (x == y){
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t\t" + rs.getDouble(5));
					flag = true;
				//}
			}
			cn.close();
			if (flag == false){
				System.out.println("No record found for the given grade");
			}
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		
	}

}