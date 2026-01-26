package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Historique {
    public void historique(Joueur player) {
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Display.effacer(13, 39, 3, 78);
        ScoreList.historique(player, 14, 10,20);

        


        while(true) {
            input=null;
            while (input == null) {
                try {
                    System.out.print("\033[21;40H");
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[21;40H          ");
                    switch (input.charAt(0)) {
                        case 'r' : 
                            return;
                    }
                } catch (IOException e) {}
                catch (StringIndexOutOfBoundsException e) {}
            }
            
        }

    }
}
