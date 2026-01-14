package testsBuffer;

import java.io.InputStreamReader;
import java.io.*;
import java.util.concurrent.*;

public class jsptest {
    public static void main(String[] args) {

    System.out.println("START");

    CancelableReaderr reader = new CancelableReaderr(new InputStreamReader(System.in));
    String line;

    new Thread(() -> {

        try {

            Thread.sleep(10000);
            reader.cancelRead();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }).start();

    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }

    System.out.println("END");
    System.exit(0);

}
}

class CancelableReaderr {

    private final ExecutorService executor;
    private Future<String> future;
    private BufferedReader brbr = new BufferedReader(new InputStreamReader(System.in));

    public CancelableReaderr(Reader in) {
        //super(in);
        //this.brbr;
        executor = Executors.newSingleThreadExecutor();
    }

    //@Override
    public String readLine() {
        
        future = executor.submit(brbr.readLine());

        try {
            return (String) future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            return null;
        }

        return null;

    }

    public void cancelRead() {
        future.cancel(true);
    }

}
