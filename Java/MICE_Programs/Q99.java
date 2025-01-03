import java.awt.*;
import java.awt.event.*;

class Q99 extends Frame implements ActionListener{
	Panel p;
	Label l1;
	Button b[] = new Button[11];
	Q99(){
		p = new Panel(new GridLayout(4,3,10,10));
		l1 = new Label();
		for (int i = 0; i < 10 ; i++){
			b[i] = new Button(""+i);
			b[i].addActionListener(this);
			p.add(b[i]);
		}
		p.add(l1);
		b[10] = new Button("EXIT");
		b[10].addActionListener(this);
		b[10].setForeground(Color.red);		
		p.add(b[10]);
		add(p);
		setSize(400,600);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		Button a = (Button) ae.getSource();
		if (a == b[10]){
			System.exit(0);
		}else if(a.getBackground() == Color.red){
			a.setBackground(Color.green);
		}else if(a.getBackground() == Color.green){
			a.setBackground(Color.blue);
		}else{
			a.setBackground(Color.red);
		}
	}
	public static void main(String args[]){new Q99();}
}