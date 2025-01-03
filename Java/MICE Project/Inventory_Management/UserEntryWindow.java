import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserEntryWindow extends JFrame implements ActionListener{

    //declare
    private JButton buttonSave,buttonCancel,buttonExit;
    private JLabel labelUser,labelPassword,labelRetype;
    private TextField textUser,textPassword,textRetype;
    private String userName,password,retypePassword;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    private Connection cn;
    private PreparedStatement st;

    //constructor
    UserEntryWindow(){

        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonSave = new JGradientButton("Save", new Color(130,170,255), new Color(150,190,255));
        buttonCancel = new JGradientButton("Cancel", new Color(230,230,100), new Color(250,250,100));
        buttonExit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));

        //Label initialization
        labelUser = new JLabel("User Name");
        labelPassword = new JLabel("Password");
        labelRetype = new JLabel("Retype Password");

        //textField initialization
        textUser = new TextField();
        textPassword = new TextField();
        textRetype = new TextField();
        textPassword.setEchoChar('*');
        textRetype.setEchoChar('*');

        //adding to Panel
        //initializing GridBagConstraint variables
        gbc.weightx = 1;gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //adding Labels
        //users
        gbc.gridx = 0;gbc.gridy = 0;
        mainGridBag.add(labelUser, gbc);

        //password
        gbc.gridx = 0;gbc.gridy = 1;
        mainGridBag.add(labelPassword, gbc);

        //retype password
        gbc.gridx = 0;gbc.gridy = 2;
        mainGridBag.add(labelRetype, gbc);

        //adding text fields
        gbc.gridwidth = 2;gbc.gridheight = 1;

        //users
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(textUser, gbc);

        //password
        gbc.gridx = 1;gbc.gridy = 1;
        mainGridBag.add(textPassword, gbc);

        //retype password
        gbc.gridx = 1;gbc.gridy = 2;
        mainGridBag.add(textRetype, gbc);

        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonSave.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 3;
        mainGridBag.add(buttonSave, gbc);

        //cancel
        buttonCancel.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 3;
        mainGridBag.add(buttonCancel, gbc);

        //exit
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 2;gbc.gridy = 3;
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
        setSize(475,250);
        setTitle("User Entry");
        setVisible(true);

    }

    //action listener
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == buttonExit){
            System.exit(0);
        }else if(ae.getSource() == buttonCancel){
            cancel();
        } else if (ae.getSource() == buttonSave) {
            userCreation();
        }
    }

    //cancel function
    void cancel(){
        textUser.setText(" ");
        textPassword.setText(" ");
        textRetype.setText(" ");
        //
        textUser.setText("");
        textPassword.setText("");
        textRetype.setText("");
        textUser.requestFocus();
    }

    //cancel only password and retype password function
    void cancelPartial(){
        textPassword.setText(" ");
        textRetype.setText(" ");
        //
        textPassword.setText("");
        textRetype.setText("");
        textPassword.requestFocus();
    }

    //user creation function
    void userCreation(){
        try{
            //getting username and password
            userName = textUser.getText().trim();
            password = textPassword.getText().trim();
            retypePassword = textRetype.getText().trim();
            //verification
            if (!(password.equals(retypePassword))){
                cancelPartial();
                JOptionPane.showMessageDialog(null,"Password and Retype Password fields do not match\nPlease type again","ERROR",0);
                return;
            }

            //SQL
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query = "insert into users values(?,?)";
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, userName);
            st.setString(2, password);
            st.executeUpdate();
            cn.close();
            cancel();

            //success
            JOptionPane.showMessageDialog(null,"User successfully created","Success",-1);
        }catch (Exception ex){
            //error
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }
}
