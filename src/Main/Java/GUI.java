package Main.Java;

import Main.Java.ShapeOfFractions.GUI.ShapeOfFractions;
import Main.Java.RemainderCalculator.Calculator;
import Main.Java.ThreeXPlusOne.GUI.ThreeXPlusOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame frame = new JFrame("Main");
    JPanel panel = new JPanel();
    Font font = new Font("Arial", Font.PLAIN, 24);

    int[] x = {0, 25, 350, 675};
    int[] y = {50, 150, 200, 250, 300, 350, 400, 450, 500, 550};

    ToolButton shapeFractions = new ToolButton("Shape of Fractions", x[1], y[1]);
    ToolButton calculator = new ToolButton("Calculator", x[2], y[1]);
    ToolButton ThreeXPlusOne = new ToolButton("3x + 1", x[3], y[1]);

    public GUI() {
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        panel.setSize(1000, 1000);
        panel.setPreferredSize(new Dimension(1000, 1000));
        frame.add(panel);


        frame.setSize(1000, 1000);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static ShapeOfFractions shapeOfFractions;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shapeFractions) shapeOfFractions = new ShapeOfFractions();
        else if (e.getSource() == calculator) new Calculator();
        else if (e.getSource() == ThreeXPlusOne) new ThreeXPlusOne();
    }

    private class CustomButton extends JButton {
        public CustomButton(String text, int x, int y, int width, int height) {
            super(text);
            this.setBounds(x, y, width, height);
            this.setFont(font);
            this.setFocusPainted(false);
            panel.add(this);
        }
    }
    private class ToolButton extends CustomButton {
        public ToolButton(String text, int x, int y) {
            super(text, x, y, 300, 40);
            this.addActionListener(GUI.this);
        }
    }
}
