package Main.Java.ShapeOfFractions.Python;

import java.io.IOException;

public class MultiThreadingBigDecimal extends Thread {

    int x;
    int y;
    int scale;
    public MultiThreadingBigDecimal(int x, int y, int scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public void run() {
        try {
            Python.mainInput(this.x, this.y, this.scale);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
