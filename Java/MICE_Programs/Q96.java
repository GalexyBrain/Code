import java.awt.*;
import java.awt.event.*;

class Q96 extends Frame implements ItemListener{
    Panel p;
    Label la1,la2,la3;
    TextField t1;
    TextArea ta1;
    List l1;
    String a[] = {"A","B","C","D","E","F","G","H","I","J"};
    double d[] = {14,20,48,19,12,45,36,18,25,30};    
    Q96(){
        p = new Panel(new GridLayout(6,1));
        la1 = new Label("Select your soap");
        la2 = new Label("Your selection");
        la3 = new Label("Number of soaps selected");
        t1 = new TextField();
        ta1 = new TextArea(5,20);
        l1 = new List(5,true);
        t1.setEditable(false);
        ta1.setEditable(false);
        for(int i = 0;i<a.length; i++){
            l1.add(a[i]);
		}
		p.add(la1);
		p.add(l1);
		p.add(la2);
		p.add(ta1);
		p.add(la3);
		p.add(t1);
		add(p);
		l1.setBackground(Color.yellow);
		l1.setForeground(Color.red);
		l1.addItemListener(this);
		setSize(300,450);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
	}
	public void itemStateChanged(ItemEvent ie){
	   ta1.setText("");
	   String s[] = l1.getSelectedItems();
	   int y[] = l1.getSelectedIndexes();
	   for (int i=0;i<s.length;i++){
	       ta1.append(s[i] + "  Rs." + d[y[i]] + "\n");
	   }
	   t1.setText("" + s.length);
	}		
	public static void main(String args[]){
		new Q96();
	}
}