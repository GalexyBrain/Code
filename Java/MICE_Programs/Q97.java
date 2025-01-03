import java.awt.*;
import java.awt.event.*;

class Q97 extends Frame implements AdjustmentListener{
    Panel p;
    Label l1,l2,l3,l4,l5,l6,l7;
    Scrollbar s1,s2,s3;
    Q97(){
        p = new Panel(new GridLayout(4,3));
        l1 = new Label();
        l2 = new Label();
        l3 = new Label();
        l4 = new Label();
        l5 = new Label();
        l6 = new Label();
        l7 = new Label();
        s1 = new Scrollbar(Scrollbar.HORIZONTAL,0,10,0,265);
        s2 = new Scrollbar(Scrollbar.HORIZONTAL,0,10,0,265);
        s3 = new Scrollbar(Scrollbar.HORIZONTAL,0,10,0,265);
        p.add(s1);p.add(l1);p.add(l2);
		p.add(s2);p.add(l3);p.add(l4);
        p.add(s3);p.add(l5);p.add(l6);
        p.add(l7);
		add(p);
		s1.addAdjustmentListener(this);
        s2.addAdjustmentListener(this);
        s3.addAdjustmentListener(this);
        setSize(400,450);
		setVisible(true);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
	}
	public void adjustmentValueChanged(AdjustmentEvent ae){
	   int r,g,b;
       r = s1.getValue();
       g = s2.getValue();
       b = s3.getValue();
       l1.setText(""+r);
       l2.setBackground(new Color(r,0,0));
       l3.setText(""+g);
       l4.setBackground(new Color(0,g,0));
       l5.setText(""+b);
       l6.setBackground(new Color(0,0,b));
       l7.setBackground(new Color(r,g,b));
       
	}
	public static void main(String args[]){
		new Q97();
	}
}