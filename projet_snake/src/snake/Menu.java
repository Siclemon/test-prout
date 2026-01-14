package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    //static String pseudo;

    public Menu(String pseudo) {

    }

    public static String menu(String pseudo) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        affichage(pseudo);

        while(true) {
            input=null;
            while (input == null) {
                try {
                    System.out.print("\033[19;41H");
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[19;41H          ");
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

    public static void affichage(String pseudo) {
        System.out.print("\033\143");
        Display.afficherTitre(3,8);
        Display.texteMenu(15,38,pseudo);
        Display.cadre(1,1,40, 80);
    }

    
}
