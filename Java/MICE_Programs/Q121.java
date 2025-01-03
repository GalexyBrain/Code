import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class Q121 extends Frame implements ActionListener{
	Panel p;
	Label l1,l2,l3;
	TextField t1,t2;
	Button b1,b2,b3;
	Connection cn;
	Statement st;
	String query, userName, password;
	Q121(){
		p = new Panel(new GridLayout(4,2,1,1));
		l1 = new Label("User Name");
		l2 = new Label("Password");
		l3 = new Label("", 1);
		b1 = new Button("Cancel");
		b2 = new Button("Log In");
		b3 = new Button("EXIT");
		t1 = new TextField();
		t2 = new TextField();
		t2.setEchoChar('*');
		b1.setBackground(Color.yellow);
		b2.setForeground(Color.white);
		b2.setBackground(Color.blue);
		b3.setForeground(Color.white);
		b3.setBackground(Color.red);
		p.add(l1);p.add(t1);
		p.add(l2);p.add(t2);
		p.add(b1);p.add(b2);
		p.add(b3);p.add(l3);
		add(p);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setSize(500,170);
		setTitle("UserCreation");
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b3){
			System.exit(0);
		}else if(ae.getSource() == b1){
			t1.setText(" ");
			t2.setText(" ");
			t1.setText("");
			t2.setText("");
			t1.requestFocus();
			l3.setText("Record Cancled");
		}else if(ae.getSource() == b2){
			try{
				userName = t1.getText().trim();
				password = t2.getText().trim();
				boolean flag;
				flag = false;
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","jThejus","root");
				String query = "select * from users";
				st = cn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()){
					if (rs.getString(1).equals(userName) && rs.getString(2).equals(password)){
						flag = true;
					}
				}
				if (flag){
					l3.setText("Login Success");
					t1.setText(" ");
					t2.setText(" ");
					t1.setText("");
					t2.setText("");
					t1.requestFocus();
				}else{
					l3.setText("user /password did not match any record");
					t1.setText(" ");
					t2.setText(" ");
					t1.setText("");
					t2.setText("");
					t1.requestFocus();
					return;
				}
				cn.close();
			}catch (Exception ex){
				l3.setText(ex.toString());
			}
		}
	}
	public static void main(String args[]){
		new Q121();
	}
}