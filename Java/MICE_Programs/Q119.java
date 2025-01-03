import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class Q119 extends Frame implements ActionListener{
	Panel p;
	Label l1,l2,l3,l4,l5,l6;
	TextField t1,t2,t3,t4,t5;
	Button b1,b2,b3;
	Connection cn;
	PreparedStatement st;
	String query;
	int itemNo,qty;
	String itemName;
	char grade;
	double price;
	Q119(){
		p = new Panel(new GridLayout(7,2,1,1));
		l1 = new Label("Item Number");
		l2 = new Label("Item Name");
		l3 = new Label("Grade");
		l4 = new Label("Quantity");
		l5 = new Label("Price");
		l6 = new Label("", 1);
		b1 = new Button("Cancel");
		b2 = new Button("Save");
		b3 = new Button("EXIT");
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		t1.setEditable(false);
		t1.setText("" + getItemNo());
		b1.setBackground(Color.yellow);
		b2.setForeground(Color.white);
		b2.setBackground(Color.blue);
		b3.setForeground(Color.white);
		b3.setBackground(Color.red);
		p.add(l1);p.add(t1);
		p.add(l2);p.add(t2);
		p.add(l3);p.add(t3);
		p.add(l4);p.add(t4);
		p.add(l5);p.add(t5);
		p.add(b1);p.add(b2);
		p.add(b3);p.add(l6);
		add(p);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setSize(350,250);
		setTitle("ItemEntry");
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b3){
			System.exit(0);
		}else if(ae.getSource() == b1){
			t1.setText("" + getItemNo());
			t2.setText(" ");
			t3.setText(" ");
			t4.setText(" ");
			t5.setText(" ");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t1.requestFocus();
			l6.setText("Record Cancled");
		}else if(ae.getSource() == b2){
			try{
				itemNo = Integer.parseInt(t1.getText().trim());
				itemName = t2.getText().trim();
				grade = t3.getText().trim().charAt(0);
				qty = Integer.parseInt(t4.getText().trim());
				price = Double.parseDouble(t5.getText().trim());
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","jThejus","root");
				String query = "insert into items values(?,?,?,?,?)";
				PreparedStatement st = cn.prepareStatement(query);
				st.setInt(1, itemNo);
				st.setString(2, itemName);
				st.setString(3, Character.toString(grade));
				st.setInt(4, qty);
				st.setDouble(5, price);
				st.executeUpdate();
				cn.close();
				t1.setText("" + getItemNo());
				t2.setText(" ");
				t3.setText(" ");
				t4.setText(" ");
				t5.setText(" ");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t1.requestFocus();
				l6.setText("Record saved!!");
			}catch (Exception ex){
				l6.setText(ex.toString());
			}
		}
	}
	int getItemNo(){
		int itemNo = 1;
		try{
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","jThejus","root");
			Statement st = cn.createStatement();
			String query = "select * from items";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				itemNo = rs.getInt(1);
			}
			cn.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
		}
		return itemNo + 1;
	}
	public static void main(String args[]){
		new Q119();
	}
}