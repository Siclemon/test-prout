package snake;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String nom;
        Login log = new Login();
        Menu menu = new Menu();
        ww jeu = new ww();
        Save sauv = new Save();
        boolean boucle = true;

        System.out.print("\033\143");

        nom = log.Pseudo();

        do {

            switch (menu.menu(nom)) {
                case "jouer":
                    jeu.main();
                    break;

                case "quitter":
                    boucle = false;
                    break;
            
                case "deco":
                    nom = log.Pseudo();
                    break;
            }

        } while (boucle);
        
        sauv.prout();
        
       
    }
}
