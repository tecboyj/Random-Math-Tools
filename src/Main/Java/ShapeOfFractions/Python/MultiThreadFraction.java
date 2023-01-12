package Main.Java.ShapeOfFractions.Python;

import java.io.IOException;

public class MultiThreadFraction extends Thread {

    int x;
    int y;
    public MultiThreadFraction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void terminate() {
        Python.terminate();
    }

    @Override
    public void run() {
        try {
            Python.mainInput(this.x, this.y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
