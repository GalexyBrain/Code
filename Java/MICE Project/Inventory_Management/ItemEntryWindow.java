import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ItemEntryWindow extends JFrame implements ActionListener{

    //declare
    private JButton buttonSave,buttonCancel,buttonExit;
    private JLabel labelItemNo,labelItemName,labelGrade,labelQuantity,labelPrice;
    private TextField textItemNo,textItemName,textGrade,textQuantity,textPrice;
    private String itemName;
    private int itemNo,quantity,itemNumber;
    private char grade;
    private double price;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    Connection cn;
    PreparedStatement st;

    //constructor
    ItemEntryWindow(){

        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonSave = new JGradientButton("Save", new Color(130,170,255), new Color(150,190,255));
        buttonCancel = new JGradientButton("Cancel", new Color(230,230,100), new Color(250,250,100));
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
        textItemNo.setEditable(false);

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
        gbc.gridwidth = 2;gbc.gridheight = 1;

        //ItemNo
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

        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonSave.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 5;
        mainGridBag.add(buttonSave, gbc);

        //cancel
        buttonCancel.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 5;
        mainGridBag.add(buttonCancel, gbc);

        //exit
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 2;gbc.gridy = 5;
        mainGridBag.add(buttonExit, gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        buttonExit.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonCancel.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);
        //Finalising frame
        cancel();
        setSize(475,400);
        setTitle("Item Entry");
        setVisible(true);
    }

    //action listener
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonExit){
            System.exit(0);
        }else if(ae.getSource() == buttonCancel){
            cancel();
        } else if (ae.getSource() == buttonSave) {
            addItem();
        }
    }

    //cancel function
    void cancel(){
        textItemNo.setText(" ");
        textItemName.setText(" ");
        textGrade.setText(" ");
        textQuantity.setText(" ");
        textPrice.setText(" ");
        //
        textItemNo.setText("");
        textItemName.setText("");
        textGrade.setText("");
        textQuantity.setText("");
        textPrice.setText("");
        textItemNo.setText("" + getItemNo());
        textItemName.requestFocus();
    }

    //Function to add Items To Database
    void addItem(){
        try{
            //Getting info from window
            itemNo = Integer.parseInt(textItemNo.getText().trim());
            itemName = textItemName.getText().trim();
            grade = textGrade.getText().trim().charAt(0);
            quantity = Integer.parseInt(textQuantity.getText().trim());
            price = Double.parseDouble(textPrice.getText().trim());

            //SQL
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query = "insert into items values(?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, itemNo);
            st.setString(2, itemName);
            st.setString(3, Character.toString(grade));
            st.setInt(4, quantity);
            st.setDouble(5, price);
            st.executeUpdate();
            cn.close();
            cancel();

            //successfully added
            JOptionPane.showMessageDialog(null,"Item Successfully added to database","Success",-1);
        }catch (Exception ex){
            //error
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }

    //Function to get ItemNumber of the next item
    int getItemNo(){
        try{
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            Statement st = cn.createStatement();
            String query = "select * from items";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                itemNumber = rs.getInt(1);
            }
            cn.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
        return itemNumber + 1;
    }
}