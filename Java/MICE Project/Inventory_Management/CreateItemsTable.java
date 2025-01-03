import java.sql.*;
import javax.swing.*;
public class CreateItemsTable {
    CreateItemsTable(){
        Connection cn1,cn2,cn3;
        try{
            cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            Statement st1 = cn1.createStatement();
            String query1 = "create table Items (itemno int primary key,"
                    + "itemname varchar(20) not null,"
                    + "grade char(1),"
                    + "qty int,"
                    + "price double)";
            st1.execute(query1);
            cn1.close();
            JOptionPane.showMessageDialog(null,"Table successfully created","Success",1);
        }catch (SQLException ex1){
            int w = JOptionPane.showConfirmDialog(null,"The table already exists\ndo you want to delete it and create a new one?","Continue?",JOptionPane.YES_NO_OPTION);
            if (w == JOptionPane.YES_OPTION){
                try {
                    cn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv", "Thejus", "root");
                    Statement st2 = cn2.createStatement();
                    String query2 = "drop table items";
                    st2.execute(query2);
                    cn2.close();
                    JOptionPane.showMessageDialog(null,"Table successfully deleted","Success",1);
                }catch (SQLException ex2)  {
                    JOptionPane.showMessageDialog(null,ex2.toString(),"ERROR",0);
                }
                try{
                    cn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
                    Statement st1 = cn3.createStatement();
                    String query3 = "create table Items (itemno int primary key,"
                            + "itemname varchar(20) not null,"
                            + "grade char(1),"
                            + "qty int,"
                            + "price double)";
                    st1.execute(query3);
                    cn3.close();
                    JOptionPane.showMessageDialog(null,"Table successfully created","Success",1);
                }catch(Exception ex3){
                    JOptionPane.showMessageDialog(null,ex3.toString(),"ERROR",0);
                }
            }
        }
    }
}