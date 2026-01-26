package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    //static String pseudo;

    // public Menu(Joueur player) {

    // }

    public static String menu(Joueur player) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Historique histo = new Historique();
        
        affichage(player);

        while(true) {
            input=null;
            while (input == null) {
                try {
                    System.out.print("\033[21;40H");
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[21;40H          ");
                    switch (input.charAt(0)) {
                        case 'j' : 
                            Animation.stop();
                            return "jouer";

                        case 'q' : 
                            Animation.stop();
                            return "quitter";

                        case 'd' : 
                            Animation.stop();
                            return "deco";

                        case 'h' : 
                            Animation.stop();
                            histo.historique(player);
                            affichage(player);
                    }
                } catch (IOException e) {}
                catch (StringIndexOutOfBoundsException e) {}
            }
            
        }

    }

    public static void historique() {
        Display.effacer(13, 39, 3, 78);
    }

    public static void affichage(Joueur player) {
        System.out.print("\033\143");
        Display.afficherTitre(3,8);
        Display.texteMenu(15,38, player.getPseudo());
        Display.cadre(1,1,40,80,"\033[48;2;100;250;100m");
        Animation.anim(player, 27, 5, 6, 6, "\033[21;40H");
        ScoreList.tableauDesScores(27,38);
    }

    
}
