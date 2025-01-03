import java.awt.*;
import java.awt.event.*;

class Q101 extends Frame implements ActionListener{
	Panel p1;
	Button b1,b2,b3,b4,b5,b6,b7;
	Label l1;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Q101(){
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		p1 = new Panel(gbl);
		b1 = new Button("Java");
		b2 = new Button("Python");
		b3 = new Button("C");
		b4 = new Button("C++");
		b5 = new Button("PPT");
		b6 = new Button("Excel");
		b7 = new Button("EXIT");b7.setBackground(Color.red);b7.setForeground(Color.white);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		l1 = new Label();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbl.setConstraints(b1, gbc);
		p1.add(b1);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbl.setConstraints(b2, gbc);
		p1.add(b2);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbl.setConstraints(b3, gbc);
		p1.add(b3);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbl.setConstraints(b4, gbc);
		p1.add(b4);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbl.setConstraints(b5, gbc);
		p1.add(b5);
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbl.setConstraints(b6, gbc);
		p1.add(b6);
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(l1, gbc);
		p1.add(l1);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(b7, gbc);
		p1.add(b7);
		add(p1);
		setSize(320,270);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b7){
			System.exit(0);
		}else if(ae.getSource() == b1){
			l1.setText("Java - 60hrs - Rs.6000");
		}else if(ae.getSource() == b2){
			l1.setText("Pyton - 40hrs - Rs.5000");
		}else if(ae.getSource() == b3){
			l1.setText("C - 40hrs - Rs.4500");
		}else if(ae.getSource() == b4){
			l1.setText("C++ - 40hrs - Rs.4500");
		}else if(ae.getSource() == b5){
			l1.setText("PPT - 10hrs - Rs.1000");
		}else if(ae.getSource() == b6){
			l1.setText("Excel - 21hrs - Rs.3200");
		}
	}
	public static void main(String args[]){new Q101();}
}