import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
    ArrayList<Polichombr> liste = new ArrayList<>();

    public Polichombr(Socket socket, PrintWriter out, BufferedReader in) {
        this.socket = socket;
        this.out = out;
        this.in = in;
        liste.add(this);
    }

    @Override
    public void run() {
        out.println("bvn");
        String msg;
        while (true) {
            
            try {
                msg = in.readLine();
                System.out.println(in.readLine());
                envoi(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    void envoi(String msg) {
        for (Polichombr usr : liste) {
            usr.out.println(msg);
        }
    }

    
}
