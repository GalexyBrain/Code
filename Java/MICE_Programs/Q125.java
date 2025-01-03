package progs;
import javax.swing.*;

public class Q125{
	public static void main(String args[]){
		long n;
		String s = "";
		n = Long.parseLong(JOptionPane.showInputDialog("Enter a number : "));
		for (int i = 1; i <= 20; i++){
			s += "\n" + n + " X " + i + " = " + (n*i);
		}
		JOptionPane.showMessageDialog(null,s,"Result",JOptionPane.PLAIN_MESSAGE);
	}
}