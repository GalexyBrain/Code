import java.awt.*;
import java.awt.event.*;

class Q95 extends Frame implements ItemListener{
    Panel p;
    Label la;
    TextField t1;
    List l1;
    String a[] = {"A","B","C","D","E","F","G","H","I","J"};
    double d[] = {14,20,48,19,12,45,36,18,25,30};    
    Q95(){
        p = new Panel(new GridLayout(3,1));
        la = new Label("Select your soap");
        t1 = new TextField();
        l1 = new List(5);
        for(int i = 0;i<a.length; i++){
            l1.add(a[i]);
		}
		p.add(la);
		p.add(l1);
		p.add(t1);
		add(p);
		l1.setBackground(Color.yellow);
		l1.setForeground(Color.red);
		t1.setEditable(false);
		l1.addItemListener(this);
		setSize(300,200);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
	}
	public void itemStateChanged(ItemEvent ie){
	   t1.setText(l1.getSelectedItem() + "   " + d[l1.getSelectedIndex()]);
	}		
	public static void main(String args[]){
		new Q95();
	}
}