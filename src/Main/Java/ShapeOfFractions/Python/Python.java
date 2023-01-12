package Main.Java.ShapeOfFractions.Python;

import java.io.FileWriter;
import java.io.IOException;
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
    public static void terminate() {
        python.process.destroy();
    }

    public static void mainInput(int x, int y) throws IOException, IOException {
        String string = fractionToDecimal(x, y);
        string = string.replace(".", "");
        String repeating = string.substring(string.indexOf("(") + 1, string.indexOf(")"));
        string = string.substring(0, string.indexOf("("));

        for (int i = 0; i < 10; i++) {
            string += repeating;
            if (string.length() > 1000) break;
        }


        if (GUISource == 0) python(string);
    }

    public static String fractionToDecimal(double num, double den) {
        if (num == 0) return "0";
        if (den == 0) return "";

        String result = "";

        // is result is negative
        if ((num < 0) ^ (den < 0)) {
            result += "-";
        }

        // convert int to long
        //long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = (long) (num / den);
        result += String.valueOf(res);

        // if remainder is 0, return result
        long remainder = (long) ((num % den) * 10);
        if (remainder == 0) return result;

        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }

            // continue
            map.put(remainder, result.length());
            res = (long) (remainder / den);
            result += String.valueOf(res);
            remainder = (long) ((remainder % den) * 10);
        }

        return result;
    }

    public static void python(String string) throws IOException {
        getPythonFile main = new getPythonFile("/ShapeOfFractions/Python/python.py");

        String command = "python3 " + main.string + " " + string;
        if (GUISource == 0) command = command.replace("python.py", "pythonGUI.py");


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