package Main.Java.ShapeOfFractions.Python;

import java.io.IOException;

public class MultiThreadingOther extends Thread {

    String string;
    public MultiThreadingOther(String string) {
        this.string = string;
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