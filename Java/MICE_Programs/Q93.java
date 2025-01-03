import java.awt.*;
import java.awt.event.*;

class Q93 extends Frame implements ItemListener, ActionListener{
	Panel p;
	Label l1,l2;
	TextField t1;
	Button b1;
	CheckboxGroup g1;
	Checkbox c1,c2,c3,c4,c5;
	Q93(){
		p = new Panel(new GridLayout(5,2));
		l1 = new Label("Select your course");
		l2 = new Label("Your selection");
		b1 = new Button("Exit");
		t1 = new TextField();
		g1 = new CheckboxGroup();
		c1 = new Checkbox("BA", false, g1);
		c2 = new Checkbox("BSc", false, g1);
		c3 = new Checkbox("BCom", false, g1);
		c4 = new Checkbox("BCA", false, g1);
		c5 = new Checkbox("BBA", false, g1);
		t1.setEditable(false);
		p.add(l1);
		p.add(c1);
		p.add(c2);
		p.add(c3);
		p.add(c4);
		p.add(c5);
		p.add(l2);
		p.add(t1);
		p.add(b1);
		add(p);
		c1.addItemListener(this);
		c2.addItemListener(this);
		c3.addItemListener(this);
		c4.addItemListener(this);
		c5.addItemListener(this);
		b1.addActionListener(this);
		setSize(200,500);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}
	public void itemStateChanged(ItemEvent ie){
		t1.setText(g1.getSelectedCheckbox().getLabel());
	}
	public void actionPerformed(ActionEvent ae){
		System.exit(0);
	}		
	public static void main(String args[]){
		new Q93();
	}
}