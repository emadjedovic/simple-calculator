package simpleCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalculatorButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color BUTTON_COLOR_NUM = new Color(163, 205, 163);
    private static final Color BUTTON_COLOR_OP = new Color(131, 100, 89);
    private static final Color BUTTON_FOREGROUND_COLOR = Color.BLACK;
    private static final Color HOVER_COLOR_NUM = new Color(193, 235, 193);
    private static final Color HOVER_COLOR_OP = new Color(151, 120, 109);
    private static final Color BORDER_COLOR = Color.black;

    public CalculatorButton(String label, ActionListener listener) {
        super(label);
        setFont(new Font("Arial", Font.PLAIN, 20));
        if(label=="/" || label=="*" || label=="-" || label=="+" || label=="=" || label=="C") {
        	setBackground(BUTTON_COLOR_OP);
        }
        else {
        	setBackground(BUTTON_COLOR_NUM);
        }
        setForeground(BUTTON_FOREGROUND_COLOR);
        setFocusPainted(false);
        setMargin(new Insets(10, 10, 10, 10)); // Add padding
        addActionListener(listener);
        setBorderPainted(false);
        setContentAreaFilled(false);
        
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if(label=="/" || label=="*" || label=="-" || label=="+" || label=="=" || label=="C") {
                	setBackground(HOVER_COLOR_OP);
                }
                else {
                	setBackground(HOVER_COLOR_NUM);
                }
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		if(label=="/" || label=="*" || label=="-" || label=="+" || label=="=" || label=="C") {
                	setBackground(BUTTON_COLOR_OP);
                }
                else {
                	setBackground(BUTTON_COLOR_NUM);
                }
        	}
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded rectangle
        int width = getWidth();
        int height = getHeight();
        int arc = Math.min(width, height);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Draw text
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(getText())) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Adjust border thickness
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(1)); // Adjust thickness here
        
        // Draw rounded rectangle border
        g2.setColor(BORDER_COLOR);
        int width = getWidth();
        int height = getHeight();
        int arc = Math.min(width, height); // Adjust the arc size for perfect circle
        g2.drawRoundRect(0, 0, width, height, arc, arc); // Adjust border thickness

        // Restore original stroke
        g2.setStroke(oldStroke);
        
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60); // Adjust button size
    }
}


