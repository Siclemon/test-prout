package snake;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {
    public static Joueur login() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pseudo=null;

        affichage();

        while (pseudo == null)
            try {
                pseudo = br.readLine();
            } catch (IOException e) {
            }
            
        Animation.stop();
        
        return Save.playerData(pseudo);
    }


    public static void affichage() {
        final int hauteurBlocCentral = 15;
        final String posCurseur = "\033["+(hauteurBlocCentral+3)+";40H";

        System.out.print("\033\143");
        Display.cadre(1,1,40,80,"\033[48;2;100;250;100m");
        Display.afficherTitre(3,8);
        Display.texteLogin(hauteurBlocCentral+2,38);
        Display.cadre(hauteurBlocCentral, 35, 6, 16,"\033[48;2;34;112;28m");
        Animation.anim(27,5,6,18,posCurseur);

        //System.out.print("\033[16;40H");
    }

        
}
