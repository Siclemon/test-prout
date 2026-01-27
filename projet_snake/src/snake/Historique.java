package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Historique {
    public void historique(Joueur player) {
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pageACtuelle = 0;
        int pagesMax = ScoreList.nombrePagesHisto(player, 20);
        
        init(player,pagesMax);


        while(true) {
            input=null;
            while (input == null) {
                try {
                    System.out.print("\033[24;17H");
                    input = br.readLine().toLowerCase();
                    System.out.print("\033[24;17H          ");
                    switch (input.charAt(0)) {
                        case 'r' : 
                            return;
                        case 's' :
                            if (pageACtuelle<pagesMax) {
                                pageACtuelle++;
                                pageSuivante(player, pageACtuelle, pagesMax);
                            }
                            break;
                        case 'p' :
                            if (pageACtuelle>0) {
                                pageACtuelle--;
                                pagePrecedente(player, pageACtuelle);
                            }
                            break;
                    }
                    affNumPage(pageACtuelle, pagesMax);
                } catch (IOException e) {}
                catch (StringIndexOutOfBoundsException e) {}
            }
            
        }
    }

    private void pageSuivante(Joueur pl, int page, int pagesMax) {
        affPrecedent(true);
        if (page<pagesMax) affSuivant(true);
        else affSuivant(false);
        affPage(pl, page);
    }

    private void pagePrecedente(Joueur pl, int page) {
        affSuivant(true);
        if (page>0) affPrecedent(true);
        else affPrecedent(false);
        affPage(pl, page);
    }

    private void affSuivant(boolean visible) {
        String texte = "\033[21;15H" + "\033[1;4m" + "S" + "\033[24m" + "uivant" + "\033[m";
        if (!visible) texte = "\033[38;2;120;120;120m" + texte;
        System.out.print(texte);
    }

    private void affPrecedent(boolean visible) {
        String texte = "\033[22;15H" + "\033[1;4m" + "P" + "\033[24m" + "récédent" + "\033[m";
        if (!visible) texte = "\033[38;2;120;120;120m" + texte;
        System.out.print(texte);
    }

    private void affPage(Joueur pl, int page) {
        Display.effacer(15, 36, 41, 67);
        ScoreList.historique(pl, 15, 41,20, page);
    }

    private void affNumPage(int page, int pageMax) {
        System.out.print("\033[27;18H" + (page+1) + "/" + (pageMax+1));
    }

    private void init(Joueur player, int pagesMax) {
        Display.effacer(11, 39, 3, 78);
        ScoreList.historique(player, 15, 41,20, 0);
        Display.afficherHistorique(14,11);
        Display.texteHistorique(20,15);
        //Display.cadre(16,8,9,17,"\033[48;2;200;200;200m");

        affPrecedent(false);
        if (pagesMax==0) affSuivant(false);
        affNumPage(0, pagesMax);
    }
}
