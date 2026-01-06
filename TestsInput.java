import java.io.*;

public class TestsInput {
    public static void main(String[] args) throws IOException {
        String a, b;
    
        Console console = System.console();

        a = console.readLine();

        System.out.print(">"+a+"\n");

        console.readPassword();


        BufferedReader caca = new BufferedReader(new InputStreamReader(System.in));

        b = caca.readLine();

        System.out.println(">"+b);

    }
    
}
