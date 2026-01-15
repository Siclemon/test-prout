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
        Display.cadre(1,1,40,80,"\033[48;2;100;250;100m");
        Display.afficherTitre(3,8);
        Animation.anim(27,5,6,18,"\033[16;40H");
        Display.texteLogin(15,38);
        Display.cadre(13, 35, 6, 16,"\033[48;2;34;112;28m");

        System.out.print("\033[16;40H");
    }

        
}
