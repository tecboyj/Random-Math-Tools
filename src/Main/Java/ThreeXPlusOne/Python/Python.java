package Main.Java.ThreeXPlusOne.Python;

import java.io.IOException;
import java.util.*;

public class Python {
    static Python python;
    public static boolean ifAll = false;

    Process process;

    public Python(String command) throws IOException {
        this.process = Runtime.getRuntime().exec(command);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            process.destroy();
        }));
    }

    public static void python(int x) throws IOException {
        getPythonFile main = new getPythonFile("/ThreeXPlusOne/python.py");


        String command = "python3 " + main.string + " " + x;
        if (ifAll) {
            command = command.replace("python.py", "pythonALL.py");
            ifAll = false;
        }
        python = new Python(command);

        /*
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

        arrX = new BigDecimal[arr.length];
        arrY = new BigDecimal[arr.length];
        for (int j = 0; j < arr.length; j++) {
            arrX[j] = new BigDecimal(arr[j][0]);
            arrY[j] = new BigDecimal(arr[j][1]);
        }

        if (GUISource == 1) {
            write(arrX, arrY);
            //new DesmosGraph();
        }
        //if (GUISource == 2) new JavaGraph();

         */
    }

    private static class getPythonFile {
        String string;
        public getPythonFile(String python) {
            this.string = Objects.requireNonNull(getClass().getResource(python)).getPath();
        }
    }
}