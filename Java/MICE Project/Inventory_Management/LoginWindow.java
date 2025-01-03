import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class LoginWindow extends JFrame implements ActionListener {
    //declare
    private JButton buttonLogin,buttonCancel,buttonExit;
    private JLabel labelUser,labelPassword;
    private TextField textUser,textPassword;
    private String userName,password;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;
    private Connection cn;
    private PreparedStatement st;

    //constructor
    LoginWindow(){

        //layout initialization
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //button initialization
        buttonLogin = new JGradientButton("Log In", new Color(130,170,255), new Color(150,190,255));
        buttonCancel = new JGradientButton("Cancel", new Color(230,230,100), new Color(250,250,100));
        buttonExit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));

        //Label initialization
        labelUser = new JLabel("User Name");
        labelPassword = new JLabel("Password");

        //textField initialization
        textUser = new TextField();
        textPassword = new TextField();
        textPassword.setEchoChar('*');

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

        //adding text fields
        gbc.gridwidth = 2;gbc.gridheight = 1;

        //users
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(textUser, gbc);

        //password
        gbc.gridx = 1;gbc.gridy = 1;
        mainGridBag.add(textPassword, gbc);

        //adding buttons
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //save
        buttonLogin.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 0;gbc.gridy = 2;
        mainGridBag.add(buttonLogin, gbc);

        //cancel
        buttonCancel.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 1;gbc.gridy = 2;
        mainGridBag.add(buttonCancel, gbc);

        //exit
        buttonExit.setPreferredSize(new Dimension(150,30));
        gbc.gridx = 2;gbc.gridy = 2;
        mainGridBag.add(buttonExit, gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        buttonExit.addActionListener(this);
        buttonLogin.addActionListener(this);
        buttonCancel.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);
        //Finalising frame
        setSize(475,180);
        setTitle("Log In");
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonExit){
            System.exit(0);
        } else if (ae.getSource() == buttonCancel) {
            cancel();
        } else if (ae.getSource() == buttonLogin) {
            logIn();
        }
    }

    //cancel function
    void cancel(){
        textUser.setText(" ");
        textPassword.setText(" ");
        //
        textUser.setText("");
        textPassword.setText("");
    }

    //login button
    void logIn(){
        try{
            userName = textUser.getText().trim();
            password = textPassword.getText().trim();
            boolean flag = false;
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinv","Thejus","root");
            String query = "select * from users";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                if (rs.getString(1).equals(userName) && rs.getString(2).equals(password)){
                    flag = true;
                }
            }
            if (flag){
                JOptionPane.showMessageDialog(null,"Log In successful","Success",-1);
                new Main_Window();
                cancel();
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null,"No such user found","ERROR",0);
                cancel();
                return;
            }
            cn.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),"ERROR",0);
        }
    }

    //main method
    public static void main(String[] args) {
        new LoginWindow();
    }
}
