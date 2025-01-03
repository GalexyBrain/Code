import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TransactionEntryWindow extends JFrame implements ActionListener{

    //declare
    private int itemNo,existingQty,tItemNo,qty;
    private char type;
    private String date;
    private double price;
    private JButton buttonShow,buttonSave,buttonCancel,buttonExit;
    private JLabel labelItemNo,labelItemName,labelGrade,labelQuantity,labelPrice,labelTransItemNo,labelTransType,labelTransDate,labelTransQuantity,labelTransPrice;
    private TextField textItemNo,textItemName,textGrade,textQuantity,textPrice,textTransItemNo,textTransType,textTransDate,textTransQuantity,textTransPrice;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    Connection cn;
    PreparedStatement st1,st2,st3,st4;
    ResultSet rs;

    //constructor
    TransactionEntryWindow(){

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
        labelTransItemNo = new JLabel("Transaction Item Number");
        labelTransType = new JLabel("Transaction Type");
        labelTransDate = new JLabel("Transaction Date");
        labelTransQuantity = new JLabel("Transaction Quantity");
        labelTransPrice = new JLabel("Transaction Price");

        //text field initialization
        textItemNo = new TextField();
        textItemName = new TextField();
        textGrade = new TextField();
        textQuantity = new TextField();
        textPrice = new TextField();
        textTransItemNo = new TextField();
        textTransType = new TextField();
        textTransDate = new TextField();
        textTransQuantity = new TextField();
        textTransPrice = new TextField();

        textItemName.setEditable(false);
        textGrade.setEditable(false);
        textQuantity.setEditable(false);
        textPrice.setEditable(false);
        textTransItemNo.setEditable(false);

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
        mainGridBag.add(labelTransItemNo, gbc);

        //TransType
        gbc.gridx = 0;gbc.gridy = 6;
        mainGridBag.add(labelTransType, gbc);

        //TransDate
        gbc.gridx = 0;gbc.gridy = 7;
        mainGridBag.add(labelTransDate, gbc);

        //TransQuantity
        gbc.gridx = 0;gbc.gridy = 8;
        mainGridBag.add(labelTransQuantity, gbc);

        //TransPrice
        gbc.gridx = 0;gbc.gridy = 9;
        mainGridBag.add(labelTransPrice, gbc);

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

        //TransItemNo
        gbc.gridx = 1;gbc.gridy = 5;
        mainGridBag.add(textTransItemNo, gbc);

        //TransType
        gbc.gridx = 1;gbc.gridy = 6;
        mainGridBag.add(textTransType, gbc);

        //TransDate
        gbc.gridx = 1;gbc.gridy = 7;
        mainGridBag.add(textTransDate, gbc);

        //TransQuantity
        gbc.gridx = 1;gbc.gridy = 8;
        mainGridBag.add(textTransQuantity, gbc);

        //TransPrice
        gbc.gridx = 1;gbc.gridy = 9;
        mainGridBag.add(textTransPrice, gbc);


        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonSave.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 10;
        mainGridBag.add(buttonSave, gbc);

        //cancel
        buttonCancel.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 10;
        mainGridBag.add(buttonCancel, gbc);

        //exit
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 2;gbc.gridy = 10;
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
        setSize(475,500);
        setTitle("Transaction Entry");
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
        textTransItemNo.setText(" ");
        textTransType.setText(" ");
        textTransDate.setText(" ");
        textTransQuantity.setText(" ");
        textTransPrice.setText(" ");
        //
        textItemNo.setText("");
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
        textTransItemNo.setText("");
        textTransType.setText("");
        textTransDate.setText("");
        textTransQuantity.setText("");
        textTransPrice.setText("");
        textItemNo.requestFocus();
    }

    //partial cancel
    void partialCancel(){
        textItemName.setText(" ");
        textGrade.setText(" ");
        textQuantity.setText(" ");
        textPrice.setText(" ");
        textTransItemNo.setText(" ");
        textTransType.setText(" ");
        textTransDate.setText(" ");
        textTransQuantity.setText(" ");
        textTransPrice.setText(" ");
        //
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
        textTransItemNo.setText("");
        textTransType.setText("");
        textTransDate.setText("");
        textTransQuantity.setText("");
        textTransPrice.setText("");
        textTransType.requestFocus();
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
                existingQty = rs.getInt(4);
                textPrice.setText("" + rs.getDouble(5));
                textTransItemNo.setText("" + rs.getInt(1));
            }
            cn.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }

    //function to update to database
    void save(){
        try{
            tItemNo = Integer.parseInt(textTransItemNo.getText().trim());
            type = textTransType.getText().trim().charAt(0);
            date = textTransDate.getText().trim();
            qty = Integer.parseInt(textTransQuantity.getText().trim());
            price = Double.parseDouble(textTransPrice.getText().trim());
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query2 = "insert into trans values(?,?,?,?,?)";
            String query3 = "update items set qty = qty + ? where itemno = ?";
            String query4 = "update items set qty = qty - ? where itemno = ?";
            st2 = cn.prepareStatement(query2);
            st3 = cn.prepareStatement(query3);
            st4 = cn.prepareStatement(query4);
            st2.setInt(1, tItemNo);
            st2.setString(2, Character.toString(type));
            st2.setString(3, date);
            st2.setInt(4, qty);
            st2.setDouble(5, price);
            st3.setInt(1, qty);
            st3.setInt(2, tItemNo);
            st4.setInt(1, qty);
            st4.setInt(2, tItemNo);
            if (qty < 0){
                JOptionPane.showMessageDialog(null,"Quantity cannot be negative","ERROR",0);
                textTransQuantity.requestFocus();
                return;
            }
            if (price < 0){
                JOptionPane.showMessageDialog(null,"Price cannot be negative","ERROR",0);
                textTransPrice.requestFocus();
                return;
            }
            if (Character.toString(type).equalsIgnoreCase("p")){
                st2.executeUpdate();
                st3.executeUpdate();
                JOptionPane.showMessageDialog(null,"Records saved successfully","SUCCESS",1);
            }else if(Character.toString(type).equalsIgnoreCase("s")){
                if (existingQty < qty){
                    JOptionPane.showMessageDialog(null,"Not enough stock","ERROR",0);
                    textTransQuantity.requestFocus();
                    return;
                }else{
                    st2.executeUpdate();
                    st4.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Records saved successfully","SUCCESS",1);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Transaction type can only be p/s","ERROR",0);
                return;
            }
            cn.close();
            cancel();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }
}