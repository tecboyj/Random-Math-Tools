package Main.Java.ThreeXPlusOne.GUI;

import Main.Java.ThreeXPlusOne.Python.MultiThread;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Java.ThreeXPlusOne.GUI.CustomClasses.*;
import static Main.Java.ThreeXPlusOne.Python.Python.ifAll;

public class ThreeXPlusOne implements ActionListener {
    JFrame frame = new JFrame("Shape of Fractions");
    static JPanel panel = new JPanel();
    Font font = new Font("Arial", Font.PLAIN, 24);

    static int defaultHeight = 25;
    static int yValue = defaultHeight + 200;

    static JTextField textField = new JTextField();


    CustomButton button = new CustomButton("GO!", 380, defaultHeight + 50, 240, 40);
    CustomButton save = new CustomButton("Save", 380, defaultHeight + 100, 240, 40);
    CustomButton exit = new CustomButton("EXIT", 710, defaultHeight + 25, 200, 40);
    CustomButton all = new CustomButton("All", 710, defaultHeight + 75, 200, 40);


    public ThreeXPlusOne() {
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        panel.setSize(1000, yValue + 50);
        panel.setPreferredSize(new Dimension(1000, yValue + 50));
        frame.add(panel);

        textField.setBounds(380, defaultHeight, 240, 40);
        textField.setFont(font);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField);

        actionListener();
        buttons();


        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        frame.add(scrollPane);


        frame.setSize(panel.getWidth() + scrollPane.getVerticalScrollBar().getWidth() + 30, 1000);
        frame.setPreferredSize(new Dimension(panel.getWidth() + scrollPane.getVerticalScrollBar().getWidth() + 30, 1000));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void actionListener() {
        button.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        all.addActionListener(this);
    }
    private void buttons() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Objects.requireNonNull(getClass().getResource("/ThreeXPlusOne/config.txt")).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            new MainButton(Integer.parseInt(scanner.nextLine()));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) frame.dispose();
        else if (e.getSource() == button) new MultiThread(Integer.parseInt(textField.getText())).start();
        else if (e.getSource() == all) {
            ifAll = true;
            new MultiThread(0).start();
        } else if (e.getSource() == save) {
            try {
                FileWriter fileWriter = new FileWriter("/home/jc515081/Coding/MathTools/P3xP1/res/config.txt", true);
                fileWriter.write("\n" + textField.getText());
                new MainButton(Integer.parseInt(textField.getText()));
                panel.repaint();
                fileWriter.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
