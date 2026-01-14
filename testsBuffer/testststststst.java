package testsBuffer;

import java.util.concurrent.TimeUnit;

public class testststststst {
  public static void main(String... args)
      throws InterruptedException {
    if (args.length != 2) {
      System.out.println("Usage: java ConsoleInputTest <number of tries> " + "<timeout in seconds>");
      System.exit(1);
    }

    ConsoleInput con = new ConsoleInput(
        Integer.parseInt(args[0]),
        Integer.parseInt(args[1]),
        TimeUnit.SECONDS
    );

    String input = con.readLine();
    System.out.println("Done. Your input was: " + input);
  }
}
