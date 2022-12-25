package Main.Java.ShapeOfFractions.Python;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static Main.Java.ShapeOfFractions.GUI.ShapeOfFractions.GUISource;

public class Python {
    static Python python;
    public static float[] arrX, arrY;

    Process process;

    public Python(String command) throws IOException {
        this.process = Runtime.getRuntime().exec(command);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            process.destroy();
        }));
    }

    public static void mainInput(int x, int y, int scale) throws IOException, IOException {
        BigDecimal value = new BigDecimal(x).divide(new BigDecimal(y), scale, RoundingMode.DOWN);
        String string = value.toString().replace(".", "");
        if (GUISource == 0) Python.python(string);
        //if (GUISource == 1) ;
        //if (GUISource == 2) ;
    }

    public static void python(String string) throws IOException {
        getPythonFile main = new getPythonFile("/ShapeOfFractions/Python/python.py");

        String command = "python3 " + main.string + " " + string;
        //String command = "python3 " + file.getAbsolutePath() + " " + x + " " + y;
        if (GUISource == 0) command = command.replace("python.py", "pythonGUI.py");
        //System.out.println(command);


        python = new Python(command);

        Scanner scanner = new Scanner(python.process.getInputStream());
        String pythonOutput = scanner.nextLine();
        pythonOutput = pythonOutput.substring(1, pythonOutput.length() - 1);
        System.out.println(pythonOutput);


        List<String> list = Arrays.asList(pythonOutput.split(", "));
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains("(")) stack.add(s.replace("(", ""));
            if (s.contains(")")) stack.add(s.replace(")", ""));
        }

        String[][] arr = new String[stack.size()/2][2];
        int i = stack.size()/2 - 1;
        while (!stack.empty()) {
            arr[i][1] = stack.pop();
            arr[i][0] = stack.pop();
            i--;
        }

        arrX = new float[arr.length];
        arrY = new float[arr.length];
        for (int j = 0; j < arr.length; j++) {
            arrX[j] = Float.parseFloat(arr[j][0]);
            arrY[j] = Float.parseFloat(arr[j][1]);
        }
    }
    public static void write(float[] arrX, float[] arrY) throws IOException {
        FileWriter myWriter = new FileWriter("/home/jc515081/Coding/Java/ShapeOfFractions/res/arrays.txt");
        myWriter.write(Arrays.toString(arrX) + "\n" + Arrays.toString(arrY));
        myWriter.close();
    }

    private static class getPythonFile {
        String string;
        public getPythonFile(String python) {
            this.string = Objects.requireNonNull(getClass().getResource(python)).getPath();
        }
    }
}