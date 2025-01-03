import java.sql.*;
import java.awt.*;
import java.awt.event.*;

class Q120 extends Frame implements ActionListener{
	Panel p;
	Label l1,l2,l3,l4;
	TextField t1,t2,t3;
	Button b1,b2,b3;
	Connection cn;
	PreparedStatement st;
	String query, userName, password, retypePassword;
	Q120(){
		p = new Panel(new GridLayout(5,2,1,1));
		l1 = new Label("User Name");
		l2 = new Label("Password");
		l3 = new Label("Retype Password");
		l4 = new Label("", 1);
		b1 = new Button("Cancel");
		b2 = new Button("Save");
		b3 = new Button("EXIT");
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t2.setEchoChar('*');
		t3.setEchoChar('*');
		b1.setBackground(Color.yellow);
		b2.setForeground(Color.white);
		b2.setBackground(Color.blue);
		b3.setForeground(Color.white);
		b3.setBackground(Color.red);
		p.add(l1);p.add(t1);
		p.add(l2);p.add(t2);
		p.add(l3);p.add(t3);
		p.add(b1);p.add(b2);
		p.add(b3);p.add(l4);
		add(p);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		setSize(300,200);
		setTitle("UserCreation");
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b3){
			System.exit(0);
		}else if(ae.getSource() == b1){
			t1.setText(" ");
			t2.setText(" ");
			t3.setText(" ");
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t1.requestFocus();
			l4.setText("Record Cancled");
		}else if(ae.getSource() == b2){
			try{
				userName = t1.getText().trim();
				password = t2.getText().trim();
				retypePassword = t3.getText().trim();
				if (!(password.equals(retypePassword))){
					l4.setText("Password does not match");
					t2.setText(" ");
					t3.setText(" ");
					t2.setText("");
					t3.setText("");
					t2.requestFocus();
					return;
				}
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","jThejus","root");
				String query = "insert into users values(?,?)";
				PreparedStatement st = cn.prepareStatement(query);
				st.setString(1, userName);
				st.setString(2, password);
				st.executeUpdate();
				cn.close();
				t1.setText(" ");
				t2.setText(" ");
				t3.setText(" ");
				t1.requestFocus();
				l4.setText("User created");
			}catch (Exception ex){
				l4.setText(ex.toString());
			}
		}
	}
	public static void main(String args[]){
		new Q120();
	}
}