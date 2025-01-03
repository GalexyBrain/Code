import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Q123 extends JFrame implements ActionListener{
	JButton b1,b2,b3;
	JLabel l1,l2,l3;
	JPanel p1;
	ImageIcon i1,i2;
	Q123(){
		i1 = new ImageIcon("Salman1.jpg");
		i2 = new ImageIcon("Madhuri5-b.jpg");
		b1 = new JButton("S.Khan");
		b2 = new JButton("M.Dixit");
		b3 = new JButton("EXIT");
		l1 = new JLabel("");
		l2 = new JLabel("");
		l3 = new JLabel("");
		b1.setToolTipText("Salman Khan");
		b2.setToolTipText("Madhuri Dixit");
		b3.setToolTipText("EXIT");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		p1 = new JPanel(new GridLayout(3,2,1,1));
		p1.add(b1);p1.add(l1);
		p1.add(b2);p1.add(l2);
		p1.add(b3);p1.add(l3);
		add(p1);
		setTitle("SWING");
		setSize(250,200);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b3){
			System.exit(0);
		}else if (ae.getSource() == b1){
			l1.setText("Salman Khan");
			l2.setIcon(i1);
			l3.setText("HDDCKS");
		}else if (ae.getSource() == b2){
			l1.setText("Madhuri Dixit");
			l2.setIcon(i2);
			l3.setText("DDLJ");
		}
		
	}
	public static void main(String[] args){
		new Q123();
	}
}
