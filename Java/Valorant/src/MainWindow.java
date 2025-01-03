import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic Button Example");
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add Agent");

        // ActionListener for the Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAgent();
            }
        });

        // Set layout for the panel
        panel.setLayout(new FlowLayout());
        // Add the Add Button to the panel
        panel.add(addButton);

        // Set layout for the frame
        frame.setLayout(new BorderLayout());
        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    private static void addAgent(){
        //init mouse position
        final int[] mouseXY = {0, 0};
        JFrame frame = new JFrame("Form Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        JPanel panel = new JPanel(new GridLayout(4, 2)); // 4 rows, 2 columns

        // Labels
        JLabel label1 = new JLabel("Agent Name");
        JLabel label2 = new JLabel("Keybinding");
        JLabel label3 = new JLabel("");


        // Text Fields
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();

        // Buttons
        JButton getMouseXY = new JButton("Get Mouse Position");
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        // Add components to the panel
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(getMouseXY);
        panel.add(label3);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        //action listeners
        getMouseXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                    panel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            // Check if the left mouse button is pressed (button 1)
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                // Get the mouse coordinates and update the label text
                                mouseXY[0] = e.getX();
                                mouseXY[1] = e.getY();
                                label3.setText("X: " + mouseXY[0] + ", Y: " + mouseXY[1]);
                            }
                        }
                    });
                }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseXY[0] = 0;
                mouseXY[1] = 0;
                textField1.setText("");
                textField2.setText("");
                label3.setText("X: " + mouseXY[0] + ", Y: " + mouseXY[1]);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAgent();
            }
        });
    }
}
