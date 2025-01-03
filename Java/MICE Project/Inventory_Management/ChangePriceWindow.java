import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePriceWindow extends JFrame implements ActionListener {
    //declare
    private  int itemNo;
    private double newPrice;
    private JButton buttonShow,buttonSave,buttonCancel,buttonExit;
    private JLabel labelItemNo,labelItemName,labelGrade,labelQuantity,labelPrice,labelNewPrice;
    private TextField textItemNo,textItemName,textGrade,textQuantity,textPrice,textNewPrice;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    Connection cn;
    PreparedStatement st1,st2;
    ResultSet rs;

    //constructor
    ChangePriceWindow(){

        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonShow = new JGradientButton("Show", new Color(80,255,80), new Color(120,255,120));
        buttonSave = new JGradientButton("Save", new Color(130,170,255), new Color(150,190,255));
        buttonCancel = new JGradientButton("Cancel", new Color(230,230,100), new Color(250,250,100));
        buttonExit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));

        //Label initialization
        labelItemNo = new JLabel("Item Number");
        labelItemName = new JLabel("Item Name");
        labelGrade = new JLabel("Grade");
        labelQuantity = new JLabel("Quantity");
        labelPrice = new JLabel("Price");
        labelNewPrice = new JLabel("New Price");

        //text field initialization
        textItemNo = new TextField();
        textItemName = new TextField();
        textGrade = new TextField();
        textQuantity = new TextField();
        textPrice = new TextField();
        textNewPrice = new TextField();


        textItemName.setEditable(false);
        textGrade.setEditable(false);
        textQuantity.setEditable(false);
        textPrice.setEditable(false);

        //adding to Panel
        //initializing GridBagConstraint variables
        gbc.weightx = 1;gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //ItemNo
        gbc.gridx = 0;gbc.gridy = 0;
        mainGridBag.add(labelItemNo, gbc);

        //TextItemNumber
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(textItemNo, gbc);

        //ShowButton
        gbc.gridx = 2;gbc.gridy = 0;
        mainGridBag.add(buttonShow, gbc);

        //adding Labels
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

        //TransItemNo
        gbc.gridx = 0;gbc.gridy = 5;
        mainGridBag.add(labelNewPrice, gbc);

        //adding text fields
        gbc.gridwidth = 2;gbc.gridheight = 1;

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

        // newPrice
        gbc.gridx = 1;gbc.gridy = 5;
        mainGridBag.add(textNewPrice, gbc);

        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonSave.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 6;
        mainGridBag.add(buttonSave, gbc);

        //cancel
        buttonCancel.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 6;
        mainGridBag.add(buttonCancel, gbc);

        //exit
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 2;gbc.gridy = 6;
        mainGridBag.add(buttonExit, gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        buttonExit.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonShow.addActionListener(this);
        buttonCancel.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);
        //Finalising frame
        setSize(475,350);
        setTitle("Change Price");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonExit){
            System.exit(0);
        }else if(ae.getSource() == buttonCancel){
            cancel();
        } else if (ae.getSource() == buttonShow) {
            showInfo();
        } else if (ae.getSource() == buttonSave) {
            save();
        }
    }
    //cancel function
    void cancel(){
        textItemNo.setText(" ");
        textItemName.setText(" ");
        textGrade.setText(" ");
        textQuantity.setText(" ");
        textPrice.setText(" ");
        textNewPrice.setText(" ");
        //
        textItemNo.setText("");
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
        textNewPrice.setText("");
        textItemNo.requestFocus();
    }

    //partial cancel
    void partialCancel(){
        textItemName.setText(" ");
        textGrade.setText(" ");
        textQuantity.setText(" ");
        textPrice.setText(" ");
        textNewPrice.setText(" ");
        //
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
        textNewPrice.setText("");
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

    //save function
    void save(){
        try{
            newPrice = Double.parseDouble(textNewPrice.getText().trim());
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query2 = "update items set price = ? where itemno = ?";
            st2 = cn.prepareStatement(query2);
            st2.setDouble(1, newPrice);
            st2.setInt(2, itemNo);
            if (newPrice < 0){
                JOptionPane.showMessageDialog(null,"New price cannot be negative","ERROR",0);
                textNewPrice.requestFocus();
                return;
            }
            if (itemNo == 0){
                JOptionPane.showMessageDialog(null,"Item number not entered.\nPlease enter an item number and click on show","ERROR",0);
                textNewPrice.requestFocus();
                return;
            }
            st2.executeUpdate();
            cn.close();
            itemNo = 0;
            JOptionPane.showMessageDialog(null,"New price successfully updated to database","Success",-1);
            cn.close();
            cancel();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }
}
