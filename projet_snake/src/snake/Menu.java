package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    String reset = "\033[0m";
    String pseudo;

    public Menu(String pseudo) {
        this.pseudo = pseudo;
    }

    public static String menu(String pseudo) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        


        while(true) {
            input=null;
            System.out.print("\033[16;30H>");
            while (input == null) {
                try {
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[16;33H\033[K");
                    switch (input.charAt(0)) {
                        case 'j' : 
                            return "jouer";

                        case 'q' : 
                            return "quitter";

                        case 'd' : 
                            return "deco";

                        // case 'o' : 
                        //     Menu.options();
            }
                } catch (IOException e) {}
                catch (StringIndexOutOfBoundsException e) {}
            }
            
        }

    }

    public void options() {

    }

    public void affichage() {
        System.out.print("\033\143");
        Display.afficherTitre(3,8);
        Display.texteMenu(pseudo);
        Display.cadre(1,1,30, 80);
    }

    
}
