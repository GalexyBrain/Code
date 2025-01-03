package progs;
import javax.swing.*;

public class Q124{
	public static void main(String args[]){
		int n,c = 0,t = 0,w;
		double a;
		do{
			n = Integer.parseInt(JOptionPane.showInputDialog("Enter a number : "));
			t += n;
			c++;
			w = JOptionPane.showConfirmDialog(null,"Do you wish to continue","Continue?",JOptionPane.YES_NO_OPTION);
		}while(w == JOptionPane.YES_OPTION);
		a = (double)t / c;
		String s = "Count of numbers is : " + c + "\nSum of numbers is : " + t + "\nAverage of numbers is : " + a;
		JOptionPane.showMessageDialog(null,s,"Result",JOptionPane.PLAIN_MESSAGE);
	}
}
