import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class ItemQueryWindow extends JFrame implements ActionListener {
    //declare
    private  int itemNo;
    private JButton buttonShow,buttonExit;
    private JLabel labelItemNo,labelItemName,labelGrade,labelQuantity,labelPrice;
    private TextField textItemNo,textItemName,textGrade,textQuantity,textPrice;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    Connection cn;
    PreparedStatement st1,st2;
    ResultSet rs;

    //constructor
    ItemQueryWindow(){

        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonShow = new JGradientButton("Show", new Color(80,255,80), new Color(120,255,120));
        buttonExit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));

        //Label initialization
        labelItemNo = new JLabel("Item Number");
        labelItemName = new JLabel("Item Name");
        labelGrade = new JLabel("Grade");
        labelQuantity = new JLabel("Quantity");
        labelPrice = new JLabel("Price");

        //text field initialization
        textItemNo = new TextField();
        textItemName = new TextField();
        textGrade = new TextField();
        textQuantity = new TextField();
        textPrice = new TextField();

        textItemName.setEditable(false);
        textGrade.setEditable(false);
        textQuantity.setEditable(false);
        textPrice.setEditable(false);

        //adding to Panel
        //initializing GridBagConstraint variables
        gbc.weightx = 1;gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //adding Labels
        //ItemNo
        gbc.gridx = 0;gbc.gridy = 0;
        mainGridBag.add(labelItemNo, gbc);

        //ItemName
        gbc.gridx = 0;gbc.gridy = 1;
        mainGridBag.add(labelItemName, gbc);

        //Grade
        gbc.gridx = 0;gbc.gridy = 2;
        mainGridBag.add(labelGrade, gbc);

        //Quantity
        gbc.gridx = 0;gbc.gridy = 3;
        mainGridBag.add(labelQuantity, gbc);

        //Price
        gbc.gridx = 0;gbc.gridy = 4;
        mainGridBag.add(labelPrice, gbc);

        //adding text fields
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //TextItemNumber
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(textItemNo, gbc);

        //ItemName
        gbc.gridx = 1;gbc.gridy = 1;
        mainGridBag.add(textItemName, gbc);

        //Grade
        gbc.gridx = 1;gbc.gridy = 2;
        mainGridBag.add(textGrade, gbc);

        //Quantity
        gbc.gridx = 1;gbc.gridy = 3;
        mainGridBag.add(textQuantity, gbc);

        //Price
        gbc.gridx = 1;gbc.gridy = 4;
        mainGridBag.add(textPrice, gbc);

        //Price
        gbc.gridx = 1;gbc.gridy = 4;
        mainGridBag.add(textPrice, gbc);

        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonShow.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 5;
        mainGridBag.add(buttonShow, gbc);

        //cancel
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 5;
        mainGridBag.add(buttonExit, gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        buttonExit.addActionListener(this);
        buttonShow.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);
        //Finalising frame
        setSize(475,350);
        setTitle("Item Query");
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonExit){
            System.exit(0);
        } else if (ae.getSource() == buttonShow) {
            showInfo();
        }
    }
    //partial cancel
    void partialCancel(){
        textItemName.setText(" ");
        textGrade.setText(" ");
        textQuantity.setText(" ");
        textPrice.setText(" ");
        //
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
    }


    //show function
    void showInfo(){
        partialCancel();
        try{
            itemNo = Integer.parseInt(textItemNo.getText().trim());
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query1 = "select * from items where itemno = ?";
            st1 = cn.prepareStatement(query1);
            st1.setInt(1, itemNo);
            rs = st1.executeQuery();
            rs.next();
            if (rs.getRow() == 0){
                JOptionPane.showMessageDialog(null,"No such item found in the database","ERROR",1);
                partialCancel();
            }else{
                textItemName.setText("" + rs.getString(2));
                textGrade.setText("" + rs.getString(3));
                textQuantity.setText("" + rs.getInt(4));
                textPrice.setText("" + rs.getDouble(5));
                itemNo = rs.getInt(1);
            }
            cn.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }
}
