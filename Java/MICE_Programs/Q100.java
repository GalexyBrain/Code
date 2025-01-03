import java.awt.*;
import java.awt.event.*;

class Q100 extends Frame implements ActionListener{
	Panel p1,p2,p3,p4;
	Label l1,l2,l3,l4,l5,l6,l7,l8;
	Button b1,b2,b3,b4,b5,b6,b7;
	CardLayout cl;
	Q100(){
		cl = new CardLayout(10,10);
		p1 = new Panel(new BorderLayout());
		p2 = new Panel(new GridLayout(1,4));
		p3 = new Panel(new GridLayout(2,1));
		p4 = new Panel(cl);
		b1 = new Button("First");
		b2 = new Button("Next");
		b3 = new Button("Previous");
		b4 = new Button("Last");
		b5 = new Button("Show 3rd");
		b6 = new Button("Reset");
		b7 = new Button("EXIT");
		l1 = new Label("RRR",1);
		l2 = new Label("V Rona",1);
		l3 = new Label("Avatar",1);
		l4 = new Label("KGF",1);
		l5 = new Label("Yash",1);
		l6 = new Label("Mungaru male",1);
		l7 = new Label("Yagamana",1);
		l8 = new Label("Aptamitra",1 );
		p3.add(l4);
		p3.add(l5);
		p4.add(l1,"one");
		p4.add(l2,"two");
		p4.add(l3,"three");
		p4.add(p3,"four");
		p4.add(l6,"five");
		p4.add(l7,"six");
		p4.add(l8,"seven");
		l1.setBackground(Color.blue);
		l2.setBackground(Color.cyan);
		l3.setBackground(Color.green);
		l4.setBackground(Color.yellow);
		l5.setBackground(Color.magenta);
		l6.setBackground(new Color(125,50,0));
		l7.setBackground(new Color(255,255,0));
		l8.setBackground(Color.gray);
		b7.setBackground(Color.red);
		b7.setForeground(Color.white);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		p2. add(b1);
		p2. add(b2);
		p2. add(b3);
		p2. add(b4);
		p1. add("North",p2);
		p1. add("South",b7);
		p1. add("East",b6);
		p1. add("West",b5);
		p1. add("Center",p4);
		add(p1);
		setSize(400,300);
		setTitle("Cards");
		setVisible(true);	
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == b7){
			System.exit(0);
		}else if(ae.getSource() == b1 || ae.getSource() == b6){
			cl.first(p4);
		}else if(ae.getSource() == b2){
			cl.next(p4);
		}else if(ae.getSource() == b3){
			cl.previous(p4);
		}else if(ae.getSource() == b4){
			cl.last(p4);
		}else if(ae.getSource() == b5){
			cl.show(p4,"three");
		}
	}
	public static void main(String args[]){new Q100();}	
		
}