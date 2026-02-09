import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        Socket socket;

        try {
            socket = new Socket("localhost",1415);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                System.out.println(in.readLine());
                
            }

        } catch (Exception e) {
        }

    }
}
