import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Client {
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1415);
            Client client = new Client(socket);
            client.ecoute();
            client.ecrire();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // try {
        //     socket = new Socket("localhost",1415);   
        //     String dofin;
        //     Scanner sc = new Scanner(System.in);
        //     ecoute();
        //     while ((dofin = sc.nextLine()) != null) {
                
        //         out.println(dofin);
        //         System.out.println(in.readLine());
        //     }

        // } catch (Exception e) {
        // }

    }

    public void ecrire() {
        Scanner sc = new Scanner(System.in);

        String msg;

        while (socket.isConnected()) {
            msg = sc.nextLine();
            out.write(msg);
        }
    }

    public void ecoute() {
        new Thread (new Runnable() {
            public void run() {
                String msg;
                while (socket.isConnected()) {
                    try {
                        msg = in.readLine();
                        System.out.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
