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
            
        Animation.stop();
        
        return pseudo;
    }

    public void affichage() {
        System.out.print("\033\143");
        Display.cadre(1,1,40,80);
        Display.afficherTitre(3,8);
        Animation.anim(27,5,6,18);
        Display.texteLogin(15,38);
    }

        
}
