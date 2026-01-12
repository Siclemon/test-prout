package snake;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
    public String Pseudo() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu();
        String pseudo=null;

        System.out.print("\033\143");
        menu.afficherTitre();
        System.out.print("\033[9;54H\033[3mby: siclemon\033[0m");
        System.out.print("\033[11;30Hpseudo ?");
        System.out.print("\033[12;30H");
        try {
            pseudo = br.readLine();
        } catch (IOException e) {
        }
        
        return pseudo;
    }
}
