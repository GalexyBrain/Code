//import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main_Window extends JFrame implements ActionListener{

    //declare
    private MenuBar mainMenuBar;
    private Menu createTable, entries, query, report;
    private JButton items,transaction,users,exit,userEntry,itemEntry,transactionEntry,changePrice,itemQuery,stockReport,transactionReport;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel mainGridBag;

    //constructor
    Main_Window(){

        //panel
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        mainGridBag = new JPanel(gbl);

        //buttons declaration
        items = new JGradientButton("Items", new Color(115,250,100), new Color(140,255,115));
        transaction = new JGradientButton("Transaction", new Color(115,250,100), new Color(140,255,115));
        users = new JGradientButton("Users", new Color(115,250,100), new Color(140,255,115));
        exit = new JGradientButton("Exit", new Color(255,80,80), new Color(255,100,100));
        userEntry = new JGradientButton("User Entry", new Color(115,175,255), new Color(115,190,255));
        itemEntry = new JGradientButton("Item Entry", new Color(115,175,255), new Color(115,190,255));
        transactionEntry = new JGradientButton("Transaction Entry",new Color(115,175,255), new Color(115,190,255));
        changePrice = new JGradientButton("Change Price",  new Color(250,255,115), new Color(255,255,145));
        itemQuery = new JGradientButton("Item Query",  new Color(250,130,250), new Color(255,150,255));
        stockReport = new JGradientButton("Stock Report",  new Color(125,255,230), new Color(140,255,255));
        transactionReport = new JGradientButton("Transaction Report",  new Color(125,255,230), new Color(140,255,255));

        //Declaring menu
        mainMenuBar = new MenuBar();
        createTable = new Menu("Create Table");
        entries = new Menu("Entries");
        query = new Menu("Query");
        report = new Menu("Report");

        //adding to CreateTable menu
        createTable.add(new MenuItem("Items"));
        createTable.add(new MenuItem("Trans"));
        createTable.add(new MenuItem("Users"));
        createTable.add(new MenuItem("-"));
        createTable.add(new MenuItem("Exit"));

        //adding to Entries menu
        entries.add(new MenuItem("User Entry"));
        entries.add(new MenuItem("Item Entry"));
        entries.add(new MenuItem("Transaction Entry"));
        entries.add(new MenuItem("-"));
        entries.add(new MenuItem("Change Price"));

        //adding to Query menu
        query.add(new MenuItem("Item Query"));

        //adding to Report menu
        report.add(new MenuItem("Stock Report"));
        report.add(new MenuItem("Transaction Report"));

        //adding to menu bar
        mainMenuBar.add(createTable);
        mainMenuBar.add(entries);
        mainMenuBar.add(query);
        mainMenuBar.add(report);

        //adding menu to JFrame
        setMenuBar(mainMenuBar);

        //adding to Panel
        //initializing GridBagConstraint variables
        gbc.weightx = 1;gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;gbc.gridheight = 1;

        //adding items
        gbc.gridx = 0;gbc.gridy = 0;
        mainGridBag.add(items, gbc);

        //adding transaction
        gbc.gridx = 0;gbc.gridy = 1;
        mainGridBag.add(transaction, gbc);

        //adding users
        gbc.gridx = 0;gbc.gridy = 2;
        mainGridBag.add(users, gbc);

        //adding exit
        gbc.gridx = 0;gbc.gridy = 3;
        mainGridBag.add(exit, gbc);

        //adding usersEntry
        gbc.gridx = 1;gbc.gridy = 0;
        mainGridBag.add(userEntry, gbc);

        //adding itemEntry
        gbc.gridx = 1;gbc.gridy = 1;
        mainGridBag.add(itemEntry, gbc);

        //adding transEntry
        gbc.gridx = 1;gbc.gridy = 2;
        mainGridBag.add(transactionEntry, gbc);

        //adding changePrice
        gbc.gridx = 1;gbc.gridy = 3;
        mainGridBag.add(changePrice, gbc);

        //adding itemQuery
        gbc.gridx = 2;gbc.gridy = 0;
        mainGridBag.add(itemQuery, gbc);

        //adding stockReport
        gbc.gridx = 3;gbc.gridy = 0;
        mainGridBag.add(stockReport, gbc);

        //adding transReport
        gbc.gridx = 3;gbc.gridy = 1;
        mainGridBag.add(transactionReport, gbc);

        //adding Listeners
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        items.addActionListener(this);
        transaction.addActionListener(this);
        users.addActionListener(this);
        exit.addActionListener(this);
        userEntry.addActionListener(this);
        itemEntry.addActionListener(this);
        transactionEntry.addActionListener(this);
        changePrice.addActionListener(this);
        itemQuery.addActionListener(this);
        stockReport.addActionListener(this);
        transactionReport.addActionListener(this);
        createTable.addActionListener(this);
        entries.addActionListener(this);
        query.addActionListener(this);
        report.addActionListener(this);

        //adding panel to framed
        add(mainGridBag);
        //Finalising frame
        setSize(600,500);
        setTitle("Main Menu");
        setVisible(true);
    }

    //action listener
    @Override
    public void actionPerformed(ActionEvent ae){
        //for buttons
        if (ae.getSource() == exit){
            System.exit(0);
        } else if (ae.getSource() == items){
            new CreateItemsTable();
        } else if (ae.getSource() == transaction) {
            new CreateTransactionTable();
        } else if (ae.getSource() == users) {
            new CreateUsersTable();
        } else if (ae.getSource() == userEntry) {
            new UserEntryWindow();
        } else if (ae.getSource() == itemEntry) {
            new ItemEntryWindow();
        } else if (ae.getSource() == transactionEntry) {
            new TransactionEntryWindow();
        } else if (ae.getSource() == changePrice) {
            new ChangePriceWindow();
        } else if (ae.getSource() == itemQuery) {
            new ItemQueryWindow();
        } else if (ae.getSource() == stockReport) {
            new StockReportWindow();
        } else if (ae.getSource() == transactionReport) {
            new TransactionReportWindow();
        }

        //if menu getting name of menuItem pressed
        String menuItem = (String)ae.getActionCommand();

        //checking source Menu
        //if createTable
        if (ae.getSource() == createTable){
            if (menuItem.equalsIgnoreCase("exit")){
                System.exit(0);
            } else if (menuItem.equalsIgnoreCase("Items")) {
                new CreateItemsTable();
            } else if (menuItem.equalsIgnoreCase("Trans")) {
                new CreateTransactionTable();
            } else if (menuItem.equalsIgnoreCase("Users")) {
                new CreateUsersTable();
            }
        }

        //if entries
        if (ae.getSource() == entries){
            if (menuItem.equalsIgnoreCase("User Entry")){
                new UserEntryWindow();
            } else if (menuItem.equalsIgnoreCase("Item Entry")){
                new ItemEntryWindow();
            } else if (menuItem.equalsIgnoreCase("Transaction Entry")){
                new TransactionEntryWindow();
            } else if (menuItem.equalsIgnoreCase("Change price")) {
                new ChangePriceWindow();
            }
        }

        //if Query
        if (ae.getSource() == query){
            new ItemQueryWindow();
        }

        //if report
        if (ae.getSource() == report){
            if (menuItem.equalsIgnoreCase("Stock Report")){
                new StockReportWindow();
            } else if (menuItem.equalsIgnoreCase("Transaction Report")) {
                new TransactionReportWindow();
            }
        }
    }

    //main method
    public static void main(String[] args){
        new Main_Window();
    }
}