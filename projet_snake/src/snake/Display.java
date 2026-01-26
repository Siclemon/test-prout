package snake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Display {
    static String reset = "\033[0m";

    
    public static void afficherTitre(int y, int x) {
        String couleur = "\033[38;2;34;112;28m";
        System.out.print("\033[" + y++ + ";" + x + "H" + couleur +"  █████████  ██████   █████   █████████   █████   ████ ██████████");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ███░░░░░███░░██████ ░░███   ███░░░░░███ ░░███   ███░ ░░███░░░░░█");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░███    ░░░  ░███░███ ░███  ░███    ░███  ░███  ███    ░███  █ ░ ");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░░█████████  ░███░░███░███  ░███████████  ░███████     ░██████   ");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ░░░░░░░░███ ░███ ░░██████  ░███░░░░░███  ░███░░███    ░███░░█   ");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ███    ░███ ░███  ░░█████  ░███    ░███  ░███ ░░███   ░███ ░   █");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░░█████████  █████  ░░█████ █████   █████ █████ ░░████ ██████████");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ░░░░░░░░░  ░░░░░    ░░░░░ ░░░░░   ░░░░░ ░░░░░   ░░░░ ░░░░░░░░░░ "+reset);
    }

    public static void texteMenu(int y, int x, String nom) {
        System.out.print("\033[11;9H\033[3m"+nom+reset);
        System.out.print("\033[12;9H\033[3mse \033[4mD\033[24méconnecter"+reset);

        String[] lignes = {"Jouer", "Cosmétiques", "Boutique", "Historique", "Quitter"};
        lignes = menuCreator(lignes, true);
        for (int i=0; i<lignes.length; i++) {
            System.out.print("\033[" + (y+i) + ";" + x + "H" + lignes[i] + reset);
        }
        System.out.print("\033["+(y+lignes.length+1)+";"+x+"H>");
    }

    public static void texteLogin(int y, int x) {
        System.out.print("\033[11;62H\033[3;38;2;250;250;120mby siclemon"+reset);
        System.out.print("\033["+y+";"+x+"HPseudo :");
        System.out.print("\033["+(y+1)+";"+x+"H>");
    }

    public static void cadre(int y, int x, int hauteur, int largeur, String couleur) {
        for (int i = y; i<hauteur+y; i++) {
            for (int j = x; j<largeur+x; j++) {
                if (j<=x+1 || j >= x+largeur-2 || i==y || i==y+hauteur-1) System.out.print("\033["+i+";"+j+"H"+couleur+" "+reset);
            }
        }
    }

    public static void gameInfo(Joueur player) {
        System.out.print("\033[1;90H"+player.getPseudo());
        System.out.print("\033[3;90H"+"Meilleur score : "+player.getHighScore());
        
        int y = 18;
        int x = 97;

        System.out.print("\033["+y+";"+x+"H"+ "▄▄▄");
        System.out.print("\033["+(y+1)+";"+(x-1)+"H"+ "█   █");
        System.out.print("\033["+(y+2)+";"+(x-1)+"H"+ "▀▄▄▄▀");

        System.out.print("\033["+(y-1)+";"+(x+1)+"H"+ "z");
        System.out.print("\033["+(y-2)+";"+(x+1)+"H"+ "↑");
        System.out.print("\033["+(y+1)+";"+(x-5)+"H"+ "← q ");
        System.out.print("\033["+(y+1)+";"+(x+4)+"H"+ " d →");
        System.out.print("\033["+(y+3)+";"+(x+1)+"H"+ "s");
        System.out.print("\033["+(y+4)+";"+(x+1)+"H"+ "↓");
    }

    public static String[] menuCreator(String[] options, boolean majuscules) {
        char[] lettresUtilisees = new char[options.length];

        for (int i=0; i<options.length; i++) {

            if (majuscules) options[i] = options[i].toUpperCase();

            for (int j=0; j<options[i].length(); j++) {
                if (lettresUtilisees[i] != options[i].charAt(j)) {
                    lettresUtilisees[i] = options[i].charAt(j);
                    options[i] = "\033[1;4m" + lettresUtilisees[i] + "\033[24m" + options[i].substring(1);

                    break;
                }
            }
        }

        return options;
    }

    public static void effacer(int yMin, int yMax, int xMin, int xMax) {
        for (int i=yMin; i<=yMax; i++) {
            for (int j=xMin; j<=xMax; j++) {
                System.out.print("\033["+i+";"+j+"H ");
            }
        }
    }
}
