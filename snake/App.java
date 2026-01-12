package snake;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        String nom;
        Login log = new Login();
        Menu menu = new Menu();
        ww jeu = new ww();

        System.out.print("\033\143");

        nom = log.Pseudo();
        // System.out.println(nom.repeat(5));
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e){}

        while (true) {

            switch (menu.menu(nom)) {
                case "jouer":
                    jeu.main();
                    break;

                case "quitter":
                    System.exit(0);
            
                case "deco":
                    nom = log.Pseudo();
            }
        }
        

       
    }
}
