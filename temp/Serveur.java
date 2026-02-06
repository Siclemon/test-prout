import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serv = new ServerSocket(1415);
            Socket client = serv.accept();

            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.print("caca");
            

            //while (in!=null);

            serv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}