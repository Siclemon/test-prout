import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private ServerSocket serv;

    public Serveur(ServerSocket serv) {
        this.serv = serv;
    }

    public void startServeur() {
        try {
            while (!serv.isClosed()) {
                Socket socket = serv.accept();
                System.out.println("abricot");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {

        }
    }

    public void closeServeur() {
        try {
            if (serv != null) {
                serv.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serv = new ServerSocket(1415);
            Serveur serveur = new Serveur(serv);
            serveur.startServeur();

            // PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            // BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // out.print("caca");
            

            // while (in!=null);

            // serv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}