package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Historique {
    public void historique(Joueur player) {
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Display.effacer(11, 39, 3, 78);
        ScoreList.historique(player, 15, 40,20);
        Display.texteHistorique(15,15);
        //Display.cadre(16,8,9,17,"\033[48;2;200;200;200m");

        while(true) {
            input=null;
            while (input == null) {
                try {
                    System.out.print("\033[22;18H");
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[22;18H          ");
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
