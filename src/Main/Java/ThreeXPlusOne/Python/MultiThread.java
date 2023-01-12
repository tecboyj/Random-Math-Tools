package Main.Java.ThreeXPlusOne.Python;

import java.io.IOException;

public class MultiThread extends Thread {

    int x;
    public MultiThread(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        try {
            Python.python(this.x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
