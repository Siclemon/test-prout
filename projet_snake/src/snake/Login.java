package snake;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
    public String Pseudo() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pseudo=null;

        affichage();

        while (pseudo == null)
            try {
                pseudo = br.readLine();
            } catch (IOException e) {
            }
        
        return pseudo;
    }

    public void affichage() {
        System.out.print("\033\143");
        Display.texteLogin();
        Display.cadre(1,1,30,80);
        Display.afficherTitre(3,8);
    }

        
}
