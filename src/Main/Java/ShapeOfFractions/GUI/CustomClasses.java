package Main.Java.ShapeOfFractions.GUI;

import Main.Java.ShapeOfFractions.Python.MultiThreadingBigDecimal;
import Main.Java.ShapeOfFractions.Python.MultiThreadingOther;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class CustomClasses {
    protected static class CustomButton extends JButton {
        public CustomButton(String text, int x, int y, int width, int height) {
            super(text);
            this.setBounds(x, y, width, height);
            this.setFont(new Font("Arial", Font.PLAIN, 24));
            this.setFocusPainted(false);
            ShapeOfFractions.panel.add(this);
        }
    }

    protected static class FractionButton extends CustomButton implements ActionListener {
        int num;
        int den;
        int scale;
        public FractionButton(int yValue, int num, int den, int scale) {
            super(num + "/" + den, 380, yValue, 240, 40);

            this.num = num;
            this.den = den;
            this.scale = scale;
            this.addActionListener(this);

            ShapeOfFractions.stack.add(scale);
            ShapeOfFractions.stack.add(den);
            ShapeOfFractions.stack.add(num);

            ShapeOfFractions.yValueFraction += 50;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new MultiThreadingBigDecimal(num, den, scale).start();
        }
    }
    protected static class OtherDecimal extends CustomButton implements ActionListener {
        String string;
        public OtherDecimal(int yValue, String name, String string) {
            super(name, 710, yValue, 200, 40);

            this.string = string;
            this.addActionListener(this);

            ShapeOfFractions.yValueOther += 50;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new MultiThreadingOther(this.string).start();
        }
    }
}
