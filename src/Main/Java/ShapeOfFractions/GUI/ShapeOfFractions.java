package Main.Java.ShapeOfFractions.GUI;

import Main.Java.ShapeOfFractions.Python.*;
import Main.Java.ShapeOfFractions.GUI.CustomClasses.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeOfFractions implements ActionListener {
    JFrame frame = new JFrame("Shape of Fractions");
    static JPanel panel = new JPanel();
    Font font = new Font("Arial", Font.PLAIN, 24);
    public static int GUISource = 0;

    static int defaultHeight = 25;
    static int yValueFraction = defaultHeight + 200;
    static int yValueOther = defaultHeight + 200;
    static Stack<Integer> stack = new Stack<>();

    JLabel label = new JLabel("/");
    JLabel labelGUI = new JLabel("GUI Interface");

    static JTextField textField1 = new JTextField();
    static JTextField textField2 = new JTextField();

    public static Stack<MultiThreadFraction> multiThreadFraction = new Stack<>();
    public static Stack<MultiThreadOther> multiThreadOther = new Stack<>();


    CustomButton GUI = new CustomButton("Python", 90, defaultHeight + 75, 200, 40);
    CustomButton button = new CustomButton("GO!", 380, defaultHeight + 50, 240, 40);
    CustomButton save = new CustomButton("Save", 380, defaultHeight + 100, 240, 40);
    CustomButton stop = new CustomButton("EXIT", 710, defaultHeight + 25, 200, 40);
    CustomButton all = new CustomButton("ALL", 710, defaultHeight + 75, 200, 40);

    OtherDecimal pi = new OtherDecimal("Pi", "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171");
    OtherDecimal root2 = new OtherDecimal("Root 2", squareRoot(2, 0, 1));
    OtherDecimal goldenRatio = new OtherDecimal("Golden Ratio", squareRoot(5, 1, 2));

    public ShapeOfFractions() {
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        panel.setSize(1000, yValueFraction + 50);
        panel.setPreferredSize(new Dimension(1000, yValueFraction + 50));
        frame.add(panel);

        initialize();
        actionListener();
        buttons();


        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        frame.add(scrollPane);


        frame.setSize(panel.getWidth() + scrollPane.getVerticalScrollBar().getWidth() + 30, 1000);
        frame.setPreferredSize(new Dimension(panel.getWidth() + scrollPane.getVerticalScrollBar().getWidth() + 30, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void initialize() {
        textField1.setBounds(380, defaultHeight, 100, 40);
        textField1.setFont(font);
        textField1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField1);

        label.setBounds(480, defaultHeight, 40, 40);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        textField2.setBounds(520, defaultHeight, 100, 40);
        textField2.setFont(font);
        textField2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(textField2);

        labelGUI.setBounds(90, defaultHeight + 25, 200, 40);
        labelGUI.setFont(font);
        labelGUI.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(labelGUI);
    }
    private void actionListener() {
        button.addActionListener(this);
        save.addActionListener(this);
        GUI.addActionListener(this);
        stop.addActionListener(this);
        all.addActionListener(this);
    }
    private void buttons() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Objects.requireNonNull(getClass().getResource("/ShapeOfFractions/config.txt")).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNextLine()) {
            int[] arr = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            new FractionButton(arr[0], arr[1]);
        }
    }

    private String squareRoot(int number, int additional, int divisor) {
        BigDecimal bNumber = new BigDecimal(number);
        BigDecimal sqrtNumber = bNumber.sqrt(new MathContext(1000)).add(new BigDecimal(additional)).divide(new BigDecimal(divisor), RoundingMode.DOWN);
        return sqrtNumber.toString().replace(".", "");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GUI) {
            if (GUISource == 0) {
                GUI.setText("<html>Desmos <font color='red'>(WIP!)</font></html>");
                GUISource++;
            } else if (GUISource == 1) {
                GUI.setText("<html>Java <font color='red'>(WIP!)</font></html>");
                GUISource++;
            } else {
                GUI.setText("Python");
                GUISource = 0;
            }
        } else if (e.getSource() == stop) {
            while (!multiThreadFraction.empty() && !multiThreadOther.empty()) {
                while (!multiThreadFraction.empty()) multiThreadFraction.pop().terminate();
                while (!multiThreadOther.empty()) multiThreadOther.pop().terminate();
            }
            frame.dispose();
        }
        else if (e.getSource() == button) {
            int x;
            if (Objects.equals(textField1.getText(), "")) x = 1;
            else x = Integer.parseInt(textField1.getText());

            int y = Integer.parseInt(textField2.getText());
            new MultiThreadFraction(x, y).start();
        } else if (e.getSource() == all) {
            while (!stack.empty()) {
                new MultiThreadFraction(stack.pop(), stack.pop()).start();
            }
        } else if (e.getSource() == save) {
            try {
                //File file = new File("config.txt");
                //FileWriter fileWriter = new FileWriter(file);
                FileWriter fileWriter = new FileWriter(Objects.requireNonNull(getClass().getResource("/ShapeOfFractions/config.txt")).getPath(), true);
                fileWriter.write("\n" + textField1.getText() + "," + textField2.getText());
                fileWriter.close();
                new FractionButton(Integer.parseInt(textField1.getText()), Integer.parseInt(textField2.getText()));
                panel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
