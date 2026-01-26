package snake;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ScoreList {
    
    public static void tableauDesScores(int y, int x) {
        if (Save.allSaveFiles() != null)
            affListeObjets(triParScore(getGamesList()),y,x,5);
    }

    public static List triParScore(List liste) {
        Comparator<Joueur.Partie> tri = Comparator.comparing(Joueur.Partie::getScore);
        liste.sort(tri);
        Collections.reverse(liste);
        return liste;
    }

    public static List triParDate(List liste) {
        Comparator<Joueur.Partie> tri = Comparator.comparing(Joueur.Partie::getDate);
        liste.sort(tri);
        Collections.reverse(liste);
        return liste;
    }

    public static List<Joueur.Partie> getGamesList() {
        List<Joueur.Partie> games =  new ArrayList<>() {};
        List<Joueur> saves = Save.allSaveFiles();
        
        for (Joueur elem : saves) {
            if (elem.games != null)
                games.addAll(elem.getGames());
        }

        return games;
    }

    public static void affListeObjets(List<Joueur.Partie> liste, int y, int x, int nbLignes) {

        System.out.print("\033["+y+++";"+x+"H");
        System.out.print("\033[48;2;30;60;30;1m");
        System.out.print(" Score -   Pseudo   -    Date    ");
        System.out.print("\033[m");

        nbLignes = Math.min(nbLignes,liste.size());

        for (int i=0; i<nbLignes; i++) {
            int score = liste.get(i).score;
            String pseudo = liste.get(i).pseudo;
            if (pseudo.length()>10)
                pseudo = pseudo.substring(0,10);
            LocalDateTime dateTemp = liste.get(i).date;
            Date date = Date.from(dateTemp.atZone(ZoneId.systemDefault()).toInstant());

            System.out.print("\033["+y+++";"+x+"H");
            if (i%2 == 0)
                System.out.print("\033[48;2;15;30;15m");
            else
                System.out.print("\033[48;2;20;40;20m");
            System.out.printf("  %3d  - %-10s - %td/%tm/%tY ",score,pseudo,date,date,date);
            System.out.print("\033[m");
        }
    }

    public static void historique(Joueur player, int y, int x, int lignes) {
        if (player.games != null) {
            List<Joueur.Partie> games = triParDate(player.games);
            affHistorique(games, y, x, lignes);
        }
    }

    public static void affHistorique(List<Joueur.Partie> liste, int y, int x, int nbLignes) {

        System.out.print("\033["+y+++";"+x+"H");
        System.out.print("\033[48;2;30;60;30;1m");
        System.out.print(" Score -       Date        ");
        System.out.print("\033[m");

        nbLignes = Math.min(nbLignes,liste.size());

        for (int i=0; i<nbLignes; i++) {
            int score = liste.get(i).score;
            LocalDateTime dateTemp = liste.get(i).date;
            Date date = Date.from(dateTemp.atZone(ZoneId.systemDefault()).toInstant());

            System.out.print("\033["+y+++";"+x+"H");
            if (i%2 == 0)
                System.out.print("\033[48;2;15;30;15m");
            else
                System.out.print("\033[48;2;20;40;20m");
            System.out.printf("  %3d  -  %td/%tm/%tY %tH:%tM ",score,date,date,date,date,date);
            System.out.print("\033[m");
        }
    }
}
