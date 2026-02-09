import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1415);
        //Serveur serv = new Serveur(serverSocket);
        Socket socket;

        while (true)
            try {
                socket = serverSocket.accept();
                System.out.println("Client connecté " + socket.toString());
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Polichombr jsp = new Polichombr(socket, out, in);
                jsp.start();
            } catch (Exception e) {
                serverSocket.close();
            }

        

    }
}

class Polichombr extends Thread {
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Polichombr(Socket socket, PrintWriter out, BufferedReader in) {
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        while (true)
            try {
                in.readLine();
                out.print("Mysdibule " + in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }


}
