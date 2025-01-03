import java.awt.*;
import java.awt.event.*;

class Q94 extends Frame implements ItemListener, ActionListener{
	Panel p;
	Label l1,l2;
	Button b1;
	Choice c1;
	Q94(){
		p = new Panel(new GridLayout(4,1));
		l1 = new Label("Your selection");
		l2 = new Label("");
		c1 = new Choice();
		c1.add("India");
		c1.add("Pakistan");
		c1.add("China");
		c1.add("Japan");
		c1.add("Bangladesh");
		b1 = new Button("Exit");
		p.add(c1);
		p.add(l1);
		p.add(l2);
		p.add(b1);
		add(p);
		c1.addItemListener(this);
		b1.addActionListener(this);
		setSize(300,200);
		setVisible(true);
	}
	public void itemStateChanged(ItemEvent ie){
		l2.setText(c1.getSelectedItem());
	}
	public void actionPerformed(ActionEvent ae){
		System.exit(0);
	}		
	public static void main(String args[]){
		new Q94();
	}
}