package testsBuffer.testsObjets;

import java.io.*;
import java.util.concurrent.Callable;

public class Cccc implements Callable<String> {
    public String call() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("ok");
        String input;

        do {
            try {
                // wait until we have data to complete a readLine()
                while (!br.ready()) {
                    Thread.sleep(50);
                }
                input = br.readLine();
            } catch (InterruptedException e) {
                System.out.println("interrompu");
                return null;
            }
        } while ("".equals(input));

        return input;
    }
}
