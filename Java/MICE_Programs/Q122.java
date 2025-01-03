import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class Q122 extends Frame implements ActionListener{
	Panel p1,p2,p3,p4;
	Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
	TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
	Button cancel,save,show,exit;
	Connection cn;
	PreparedStatement st1,st2,st3,st4;
	ResultSet rs;
	String query1,query2,query3,query4;
	int itemNo,qty,tItemNo,existingQty;
	String date;
	char type;
	double price;
	Q122(){
		p1 = new Panel(new GridLayout(12,2,1,1));
		p2 = new Panel(new GridLayout(1,4,1,1));
		p4 = new Panel(new BorderLayout(1,1));
		l1 = new Label("Item Number");
		l2 = new Label("Item Number");
		l3 = new Label("Item Name");
		l4 = new Label("Grade");
		l5 = new Label("Quantity");
		l6 = new Label("Price");
		l7 = new Label("Transaction Item Number");
		l8 = new Label("Transaction Type");
		l9 = new Label("Transaction Date");
		l10 = new Label("Transaction Quantity");
		l11 = new Label("Transaction Price");
		l12 = new Label("");
		cancel = new Button("Cancel");
		save = new Button("Save");
		exit = new Button("EXIT");
		show = new Button("Show");
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		t6 = new TextField();
		t7 = new TextField();
		t8 = new TextField();
		t9 = new TextField();
		t10 = new TextField();
		t11 = new TextField();
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		cancel.setBackground(Color.yellow);
		save.setForeground(Color.white);
		save.setBackground(Color.blue);
		exit.setForeground(Color.white);
		exit.setBackground(Color.red);
		show.setBackground(Color.green);
		p2.add(l1);p2.add(t1);
		p2.add(show);p2.add(exit);
		p4.add("North",p2);
		p1.add(l2);p1.add(t2);
		p1.add(l3);p1.add(t3);
		p1.add(l4);p1.add(t4);
		p1.add(l5);p1.add(t5);
		p1.add(l6);p1.add(t6);
		p1.add(l7);p1.add(t7);
		p1.add(l8);p1.add(t8);
		p1.add(l9);p1.add(t9);
		p1.add(l10);p1.add(t10);
		p1.add(l11);p1.add(t11);
		p1.add(cancel);p1.add(save);
		p4.add("Center",p1);
		p4.add("South", l12);
		add(p4);
		cancel.addActionListener(this);
		save.addActionListener(this);
		show.addActionListener(this);
		exit.addActionListener(this);
		setSize(400,450);
		setTitle("TransactionEntry");
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == exit){
			System.exit(0);
		}else if(ae.getSource() == cancel){
			t1.setText(" ");
			t2.setText(" ");
			t3.setText(" ");
			t4.setText(" ");
			t5.setText(" ");
			t6.setText(" ");
			t7.setText(" ");
			t8.setText(" ");
			t9.setText(" ");
			t10.setText(" ");
			t11.setText(" ");
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
			t7.setText("");
			t8.setText("");
			t9.setText("");
			t10.setText("");
			t11.setText("");
			t1.requestFocus();
			l12.setText("Record Cancled");
		}else if(ae.getSource() == save){
			try{
				tItemNo = Integer.parseInt(t7.getText().trim());
				type = t8.getText().trim().charAt(0);
				date = t9.getText().trim();
				qty = Integer.parseInt(t10.getText().trim());
				price = Double.parseDouble(t11.getText().trim());
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
				query2 = "insert into trans values(?,?,?,?,?)";
				query3 = "update items set qty = qty + ? where itemno = ?";
				query4 = "update items set qty = qty - ? where itemno = ?";
				st2 = cn.prepareStatement(query2);
				st3 = cn.prepareStatement(query3);
				st4 = cn.prepareStatement(query4);
				st2.setInt(1, tItemNo);
				st2.setString(2, Character.toString(type));
				st2.setString(3, date);
				st2.setInt(4, qty);
				st2.setDouble(5, price);
				st3.setInt(1, qty);
				st3.setInt(2, tItemNo);
				st4.setInt(1, qty);
				st4.setInt(2, tItemNo);
				if (qty < 0){
					l12.setText("Qyantity cannot be negitive");
					t10.requestFocus();
					return;
				}
				if (price < 0){
					l12.setText("Price cannot be negitive");
					t11.requestFocus();
					return;
				}
				if (Character.toString(type).equalsIgnoreCase("p")){
					st2.executeUpdate();
					st3.executeUpdate();
					l12.setText("Records saved successfully");
				}else if(Character.toString(type).equalsIgnoreCase("s")){
					if (existingQty < qty){
						l12.setText("Not enough stock");
						t10.requestFocus();
						return;
					}else{
						st2.executeUpdate();
						st4.executeUpdate();
						l12.setText("Records saved successfully");
					}
				}else{
					l12.setText("Type can only be p/s");
					return;
				}
				t1.setText(" ");
				t2.setText(" ");
				t3.setText(" ");
				t4.setText(" ");
				t5.setText(" ");
				t6.setText(" ");
				t7.setText(" ");
				t8.setText(" ");
				t9.setText(" ");
				t10.setText(" ");
				t11.setText(" ");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
				t8.setText("");
				t9.setText("");
				t10.setText("");
				t11.setText("");
				t1.requestFocus();
			}catch (Exception ex){
				l12.setText(ex.toString());
			}
		}else if(ae.getSource() == show){
			try{
				itemNo = Integer.parseInt(t1.getText().trim());
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
				query1 = "select * from items where itemno = ?";
				st1 = cn.prepareStatement(query1);
				st1.setInt(1, itemNo);
				rs = st1.executeQuery();
				rs.next();
				if (rs.getRow() == 0){
					l12.setText("No item found for the given item number");
				}else{
					l12.setText(" ");
					t2.setText("" + rs.getInt(1));
					t3.setText("" + rs.getString(2));
					t4.setText("" + rs.getString(3));
					t5.setText("" + rs.getInt(4));
					existingQty = rs.getInt(4);
					t6.setText("" + rs.getDouble(5));
					t7.setText("" + rs.getInt(1));
				}
			}catch (Exception ex){
				l12.setText(ex.toString());
			}
		}
	}
	public static void main(String args[]){
		new Q122();
	}
}