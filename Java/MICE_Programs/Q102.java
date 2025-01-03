import java.awt.*;
import java.awt.event.*;

class Q102 extends Frame implements ActionListener{
	Panel p1;
	TextArea ta1;
	MenuBar mb;
	Menu m1,m2;
	Q102(){
		p1 = new Panel(new GridLayout(1,1));
		mb = new MenuBar();
		m1 = new Menu("Size");
		m2 = new Menu("Color");
		ta1 = new TextArea("TESTING");
		m1.add(new MenuItem("8"));
		m1.add(new MenuItem("10"));
		m1.add(new MenuItem("12"));
		m1.add(new MenuItem("14"));
		m1.add(new MenuItem("16"));
		m1.add(new MenuItem("18"));
		m1.add(new MenuItem("20"));
		m1.add(new MenuItem("22"));
		m1.add(new MenuItem("24"));
		m1.add(new MenuItem("26"));
		m1.add(new MenuItem("-"));
		m1.add(new MenuItem("EXIT", new MenuShortcut(KeyEvent.VK_X)));
		m2.add(new MenuItem("Red", new MenuShortcut(KeyEvent.VK_R)));
		m2.add(new MenuItem("Green", new MenuShortcut(KeyEvent.VK_G)));
		m2.add(new MenuItem("Blue", new MenuShortcut(KeyEvent.VK_B)));
		m2.add(new MenuItem("Yellow", new MenuShortcut(KeyEvent.VK_Y)));
		m2.add(new MenuItem("Magenta", new MenuShortcut(KeyEvent.VK_M)));
		mb.add(m1);
		mb.add(m2);
		setMenuBar(mb);
		m1.addActionListener(this);
		m2.addActionListener(this);
		p1.add(ta1);
		add(p1);
		setSize(1920,1080);	
		setVisible(true);
	}	

	public void actionPerformed(ActionEvent ae){
		String x = (String)ae.getActionCommand();
		if (ae.getSource() == m1){
			if (x.equalsIgnoreCase("EXIT")){
				System.exit(0);
			}else{
				Font f = new Font("Arial", Font.PLAIN, Integer.parseInt(x));
				ta1.setFont(f);
			}
		}
		if (ae.getSource() == m2){
			if(x.equalsIgnoreCase("red")){
				ta1.setForeground(Color.red);
			}if(x.equalsIgnoreCase("green")){
				ta1.setForeground(Color.green);
			}if(x.equalsIgnoreCase("blue")){
				ta1.setForeground(Color.blue);
			}if(x.equalsIgnoreCase("yellow")){
				ta1.setForeground(Color.yellow);
			}if(x.equalsIgnoreCase("magenta")){
				ta1.setForeground(Color.magenta);
			}
		}
	}
	public static void main(String args[]){new Q102();}
}