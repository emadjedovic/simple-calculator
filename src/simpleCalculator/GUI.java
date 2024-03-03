package simpleCalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
 private Logic logic;
 private JTextField resultField;
 private StringBuilder inputBuffer;

 public GUI(Logic logic) {
     this.logic = logic;
     inputBuffer = new StringBuilder();
     createAndShowGUI();
 }

 public void createAndShowGUI() {
     JFrame frame = new JFrame("Simple Calculator | Ema Djedovic");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Creating an EmptyBorder with 10 pixels of padding
     EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);

     JPanel panel = new JPanel();
     panel.setBackground(Color.DARK_GRAY);
     panel.setLayout(new BorderLayout());
     panel.setBorder(emptyBorder); // Applying the EmptyBorder to the panel

     // Result field
     resultField = new JTextField();
     resultField.setBackground(Color.DARK_GRAY);
     resultField.setEditable(false);
     resultField.setFont(new Font("Arial", Font.PLAIN, 40));
     resultField.setHorizontalAlignment(JTextField.LEFT); // Align text to the left
     resultField.setPreferredSize(new Dimension(300, 100));
     resultField.setForeground(new Color(230,230,230)); // Set text color to white
     resultField.setBorder(null); // Remove border

     // Panel to hold result field and "C" button horizontally
     JPanel resultPanel = new JPanel();
     resultPanel.setBackground(Color.DARK_GRAY);
     resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS)); // Use BoxLayout with X_AXIS alignment

     // "C" button
     CalculatorButton clearButton = new CalculatorButton("C", new ButtonClickListener());
     clearButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, clearButton.getPreferredSize().height)); // Set maximum height
     clearButton.setAlignmentY(Component.CENTER_ALIGNMENT); // Center button vertically

     resultPanel.add(Box.createHorizontalGlue()); // Add glue to push the button to the right
     resultPanel.add(resultField); // Add result field
     resultPanel.add(clearButton); // Add "C" button

     panel.add(resultPanel, BorderLayout.NORTH); // Add resultPanel to the top of the main panel

     // Button panel
     JPanel buttonPanel = new JPanel();
     buttonPanel.setBackground(Color.DARK_GRAY);
     buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

     // Add other buttons
     String[] buttonLabels = {
             "7", "8", "9", "/",
             "4", "5", "6", "*",
             "1", "2", "3", "-",
             "0", ".", "=", "+"
     };

     for (String label : buttonLabels) {
         CalculatorButton button = new CalculatorButton(label, new ButtonClickListener());
         buttonPanel.add(button);
     }

     panel.add(buttonPanel, BorderLayout.PAGE_END); // Add buttonPanel to the bottom of the main panel

     // Adding the panel to the content pane of the frame
     frame.getContentPane().add(panel);
     frame.pack();
     frame.setResizable(false);
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
 }

 private class ButtonClickListener implements ActionListener {
     @Override
     public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();
         switch (command) {
             case "=":
                 calculate();
                 break;
             case "C":
                 clear();
                 break;
             default:
                 inputBuffer.append(command);
                 resultField.setText(inputBuffer.toString());
                 break;
         }
     }

     private void calculate() {
    	    String expression = inputBuffer.toString();
    	    try {
    	        double result = logic.evaluate(expression);
    	        resultField.setText(String.valueOf(result));
    	        inputBuffer.setLength(0);
    	        inputBuffer.append(result);
    	    } catch (IllegalArgumentException ex) {
    	        System.err.println("Error: " + ex.getMessage());
    	        resultField.setText("Error");
    	        inputBuffer.setLength(0);
    	    }
    	}


     private void clear() {
         inputBuffer.setLength(0);
         resultField.setText("");
     }
 }
}
