import java.io.IOException;

import snake.Game;
import snake.Joueur;
import snake.Login;
import snake.Menu;
import snake.Save;

public class App {
    public static void main(String[] args) throws IOException {
        Joueur player;
        Game jeu = new Game();
        boolean boucle = true;

        System.out.print("\033\143");

        player = Login.login();

        do {

            switch (Menu.menu(player.getPseudo())) {
                case "jouer":
                    jeu.main(player);
                    break;

                case "quitter":
                    boucle = false;
                    break;
            
                case "deco":
                    player = Login.login();
                    break;
            }

        } while (boucle);
        
        Save.prout(player);
        
        System.out.print("\033[E");
       
    }
}
