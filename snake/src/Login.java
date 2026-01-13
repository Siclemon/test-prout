package snake.src;
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
        Menu menu = new Menu();
        String reset = "\033[0m";
        int hauteur = 30;
        int largeur = 80;

        System.out.print("\033\143");

        //cadre
        for (int y = 1; y<hauteur+1; y++) {
                for (int x = 1; x<largeur+1; x++) {
                    if (x<=2 || x >= largeur-1 || y==1 || y==hauteur) System.out.print("\033["+y+";"+x+"H\033[48;2;100;250;100m "+reset);
                }
            }

        //titre
        menu.afficherTitre(3,7);
        //texte
        System.out.print("\033[2;67H\033[3;38;2;250;250;120mby: siclemon"+reset);
        System.out.print("\033[14;37Hpseudo ?");
        System.out.print("\033[15;37H>");
        }

        
}
