import java.awt.*;
import java.awt.event.*;

class Q98 extends Frame implements ActionListener{
	Panel p;
	Label l1;
	Button b1,b2,b3,b4;
	Q98(){
		p = new Panel(new BorderLayout());
		l1 = new Label("",1);
		l1.setFont(new Font("Cooper", Font.BOLD,28));
		b1 = new Button("HTML");
		b2 = new Button("  IP  ");
		b3 = new Button("RAM");
		b4 = new Button("EXIT");
		l1.setBackground(Color.cyan);
		l1.setForeground(Color.blue);
		b1.setFont(new Font("Cooper", Font.BOLD,28));
		b2.setFont(new Font("Cooper", Font.BOLD,28));
		b3.setFont(new Font("Cooper", Font.BOLD,28));
		b4.setFont(new Font("Cooper", Font.BOLD,28));
		p.add("North",b1);
		p.add("East",b3);
		p.add("West",b2);
		p.add("South",b4);
		p.add("Center",l1);
		add(p);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b4.setBackground(Color.red);
		b4.setForeground(Color.white);
		setSize(600,200);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b1){
			l1.setText("Hyper Text Markup Language");
		}if (ae.getSource() == b2){
			l1.setText("Internet Protocall");
		}if (ae.getSource() == b3){
			l1.setText("Random Ascess Memory");
		}if (ae.getSource() == b4){
			System.exit(0);
		}
	}
	public static void main(String args[]){new Q98();}
}
		
		
		