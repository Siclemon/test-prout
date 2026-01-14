package snake;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class InputSnake implements Callable<String> {
    public String call() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;


        do {
            try {
                // wait until we have data to complete a readLine()
                while (!br.ready()) {
                    Thread.sleep(20);
                }
                input = br.readLine();
            } catch (InterruptedException e) {
                System.out.println("input interrompu");
                return null;
            }
        } while (input.equals(""));

        return input;
    }
}
