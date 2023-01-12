package Main.Java.ThreeXPlusOne.GUI;

import Main.Java.ThreeXPlusOne.Python.MultiThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static Main.Java.ThreeXPlusOne.GUI.ThreeXPlusOne.yValue;


public class CustomClasses {
    protected static class CustomButton extends JButton {
        public CustomButton(String text, int x, int y, int width, int height) {
            super(text);
            this.setBounds(x, y, width, height);
            this.setFont(new Font("Arial", Font.PLAIN, 24));
            this.setFocusPainted(false);
            ThreeXPlusOne.panel.add(this);
        }
    }

    protected static class MainButton extends CustomButton implements ActionListener {
        int x;
        public MainButton(int x) {
            super(String.valueOf(x), 380, yValue, 240, 40);

            this.x = x;
            this.addActionListener(this);

            ThreeXPlusOne.yValue += 50;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new MultiThread(x).start();
        }
    }
}
