package Main.Java.ShapeOfFractions.GUI;

import Main.Java.ShapeOfFractions.Python.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static Main.Java.ShapeOfFractions.GUI.ShapeOfFractions.*;


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
        public FractionButton(int num, int den) {
            super(num + "/" + den, 380, yValueFraction, 240, 40);

            this.num = num;
            this.den = den;
            this.addActionListener(this);

            stack.add(den);
            stack.add(num);

            yValueFraction += 50;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            multiThreadFraction.add(new MultiThreadFraction(num, den));
            multiThreadFraction.peek().start();
        }
    }
    protected static class OtherDecimal extends CustomButton implements ActionListener {
        String string;
        public OtherDecimal(String name, String string) {
            super(name, 710, yValueOther, 200, 40);

            this.string = string;
            this.addActionListener(this);

            yValueOther += 50;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            multiThreadOther.add(new MultiThreadOther(string));
            multiThreadOther.peek().start();
        }
    }
}
