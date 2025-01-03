import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class TransactionReportWindow extends JFrame implements ActionListener {
    //declare
    private  int itemNo,y;
    private JButton buttonExit;
    private JLabel labelTransItemNo,labelTransType,labelTransDate,labelQuantity,labelPrice;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    Connection cn;
    Statement st1;
    ResultSet rs;

    //constructor
    TransactionReportWindow(){
        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonExit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));

        //Label initialization
        labelTransItemNo = new JLabel("Item Number");
        labelTransType = new JLabel("Transaction type");
        labelTransDate = new JLabel("Date");
        labelQuantity = new JLabel("Quantity");
        labelPrice = new JLabel("Price");

        //adding to Panel
        //initializing GridBagConstraint variables
        gbc.weightx = 1;gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //Labels
        //ItemNumber
        gbc.gridx = 0;gbc.gridy = 0;
        mainGridBag.add(labelTransItemNo,gbc);

        //ItemName
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(labelTransType,gbc);

        //Grade
        gbc.gridx = 2;gbc.gridy = 0;
        mainGridBag.add(labelTransDate,gbc);

        //Quantity
        gbc.gridx = 3;gbc.gridy = 0;
        mainGridBag.add(labelQuantity,gbc);

        //Price
        gbc.gridx = 4;gbc.gridy = 0;
        mainGridBag.add(labelPrice,gbc);

        //adding Items in stock
        try{
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query = "select * from trans";
            st1 = cn.createStatement();
            rs = st1.executeQuery(query);
            y=1;
            while (rs.next()){
                //add item number
                gbc.gridx = 0;gbc.gridy=y;
                mainGridBag.add(new Label(""+rs.getInt(1)),gbc);

                //add item name
                gbc.gridx = 1;gbc.gridy=y;
                mainGridBag.add(new Label(""+rs.getString(2)),gbc);

                //add grade
                gbc.gridx = 2;gbc.gridy=y;
                mainGridBag.add(new Label(""+rs.getString(3)),gbc);

                //add quantity
                gbc.gridx = 3;gbc.gridy=y;
                mainGridBag.add(new Label(""+rs.getInt(4),2),gbc);

                //add price
                gbc.gridx = 4;gbc.gridy=y;
                mainGridBag.add(new Label(""+rs.getDouble(5),2),gbc);

                //going to next column
                y++;
            }
            cn.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }

        //adding button exit
        gbc.gridwidth=5;
        gbc.gridx = 0;gbc.gridy = y;
        mainGridBag.add(buttonExit,gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        buttonExit.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);

        //Finalising frame
        setSize(600,(y+1)*40);
        setTitle("Transaction Report");
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonExit){
            System.exit(0);
        }
    }
}
