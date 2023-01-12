package Main.Java.ShapeOfFractions.Python;

import java.io.IOException;

public class MultiThreadOther extends Thread {

    String string;
    public MultiThreadOther(String string) {
        this.string = string;
    }

    public void terminate() {
        Python.terminate();
    }

    @Override
    public void run() {
        try {
            Python.python(this.string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}