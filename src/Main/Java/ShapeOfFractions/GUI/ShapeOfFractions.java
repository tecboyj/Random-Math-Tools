package Main.Java.ShapeOfFractions.GUI;

import Main.Java.ShapeOfFractions.Python.MultiThreadingBigDecimal;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeOfFractions implements ActionListener {
    JFrame frame = new JFrame("Tec_BoyJ");
    static JPanel panel = new JPanel();
    Font font = new Font("Arial", Font.PLAIN, 24);
    public static int GUISource = 0;

    static int inputScale = 1000;
    static int defaultHeight = 25;
    static int yValueFraction = defaultHeight + 200;
    static int yValueOther = defaultHeight + 200;
    static Stack<Integer> stack = new Stack<>();

    JLabel label = new JLabel("/");
    JLabel labelGUI = new JLabel("GUI Interface");

    static JTextField textField1 = new JTextField();
    static JTextField textField2 = new JTextField();

    public static Thread shutdownHook = new Thread(() -> Runtime.getRuntime().halt(0));


    CustomClasses.CustomButton GUI = new CustomClasses.CustomButton("Python", 90, defaultHeight + 75, 200, 40);
    CustomClasses.CustomButton button = new CustomClasses.CustomButton("GO!", 380, defaultHeight + 50, 240, 40);
    CustomClasses.CustomButton save = new CustomClasses.CustomButton("Save", 380, defaultHeight + 100, 240, 40);
    CustomClasses.CustomButton stop = new CustomClasses.CustomButton("EXIT", 710, defaultHeight + 25, 200, 40);
    CustomClasses.CustomButton all = new CustomClasses.CustomButton("ALL", 710, defaultHeight + 75, 200, 40);

    CustomClasses.OtherDecimal pi = new CustomClasses.OtherDecimal(yValueOther, "Pi", "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171");
    CustomClasses.OtherDecimal root2 = new CustomClasses.OtherDecimal(yValueOther, "Root 2", squareRoot(2, 0, 1));
    CustomClasses.OtherDecimal goldenRatio = new CustomClasses.OtherDecimal(yValueOther, "Golden Ratio", squareRoot(5, 1, 2));

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
            new CustomClasses.FractionButton(yValueFraction, arr[0], arr[1], arr[2]);
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
                GUI.setText("ShapeOfFractions/Python");
                GUISource = 0;
            }
        } else if (e.getSource() == stop) {
            frame.dispose();
        }
        else if (e.getSource() == button) {
            int x = Integer.parseInt(textField1.getText());
            int y = Integer.parseInt(textField2.getText());
            new MultiThreadingBigDecimal(x, y, 1000).start();
        } else if (e.getSource() == all) {
            while (!stack.empty()) {
                new MultiThreadingBigDecimal(stack.pop(), stack.pop(), stack.pop()).start();
            }
        } else if (e.getSource() == save) {
            popUp();
        }
    }

    public void popUp() {
        JFrame popUp = new JFrame();
        JTextField textField = new JTextField();
        textField.setBounds(100, 5, 100, 40);
        textField.setFont(font);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        CustomClasses.CustomButton ok = new CustomClasses.CustomButton("OK", 100, 55, 100, 40);
        ok.addActionListener(e -> {
            inputScale = Integer.parseInt(textField.getText());
            try {
                //File file = new File("config.txt");
                //FileWriter fileWriter = new FileWriter(file);
                FileWriter fileWriter = new FileWriter(Objects.requireNonNull(getClass().getResource("/ShapeOfFractions/config.txt")).getPath(), true);
                fileWriter.write("\n" + textField1.getText() + "," + textField2.getText() + "," + inputScale);
                fileWriter.close();
                new CustomClasses.FractionButton(yValueFraction, Integer.parseInt(textField1.getText()), Integer.parseInt(textField2.getText()), inputScale);
                panel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            inputScale = 1000;
            popUp.dispose();
        });

        popUp.add(textField);
        popUp.add(ok);

        popUp.setSize(300, 100);
        popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUp.setResizable(false);
        popUp.setLocationRelativeTo(null);
        popUp.setLayout(null);
        popUp.setVisible(true);
    }
}
