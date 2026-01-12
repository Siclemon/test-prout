package jsp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
    public String Pseudo() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pseudo=null;

        affichage();

        try {
            pseudo = br.readLine();
        } catch (IOException e) {
        }
        
        return pseudo;
    }

    public void affichage() {
        Menu menu = new Menu();

        System.out.print("\033\143");
        menu.afficherTitre();
        System.out.print("\033[9;54H\033[3;38;2;250;250;120mby: siclemon\033[0m");
        System.out.print("\033[11;30Hpseudo ?");
        System.out.print("\033[12;30H>");
    }
}
