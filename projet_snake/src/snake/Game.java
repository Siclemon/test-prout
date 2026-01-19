package snake;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Game {
    static final int dimensions = 10;
    static String[][] tableau = new String[dimensions][dimensions];
    static ArrayList<int[]> serpent = new ArrayList<>(); // {{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> choses = new HashMap<>();

    static int yTete, xTete;
    static Random rng = new Random();
    static int yPomme = rng.nextInt(dimensions), xPomme = rng.nextInt(dimensions);
    static Thread deplacementAuto;
    static int yLast = -1, xLast = 0;
    static boolean perdu = false;
    static boolean pommeMangee = false;
    static String couleurFondUn = "\033[48;2;47;138;40m", couleurFondDeux = "\033[48;2;34;112;28m";

    static Future<String> futur;
    static ExecutorService exec;

    static LocalDateTime date;

    static Skins skinsList = new Skins();

    public Game() {
        yTete = dimensions / 2;
        xTete = dimensions / 2;
        perdu = false;
        tableau = new String[dimensions][dimensions];
        serpent = new ArrayList<>();
        rng = new Random();
        yPomme = rng.nextInt(dimensions);
        xPomme = rng.nextInt(dimensions);
        yLast = -1;
        xLast = 0;
        perdu = false;
        pommeMangee = false;
        couleurFondUn = "\033[48;2;47;138;40m";
        couleurFondDeux = "\033[48;2;34;112;28m";
        date = LocalDateTime.now();
    }

    public void main(Joueur player) throws IOException {
        Game sss = new Game();

        ExecutorService service = Executors.newCachedThreadPool();
        exec = Executors.newSingleThreadExecutor();

        // Map<String,String[][]> skinsFruit = skinsList.getSkins("fruit");
        // String quelFuit = player.skins.getFruit();
        // String[][] fruit = skinsFruit.get(quelFuit);

        choses.put("pomme", skinsList.getSkins("fruit").get(player.skins.getFruit()));
        choses.put("tete", skinsList.getSkins("head").get(player.skins.head));
        choses.put("corps", skinsList.getSkins("body").get(player.skins.body));
        // choses.put("fond", skinsList.getSkins("back").get(player.skins.back));
        couleurFondUn = skinsList.getBack().get(player.skins.back)[1];
        couleurFondDeux = skinsList.getBack().get(player.skins.back)[2];

        for (String[] ligne : tableau)
            Arrays.fill(ligne, " ");

        serpent.add(0, new int[] { yTete, xTete });
        //serpent.add(new int[] { 0, 0 });

        tableau[yTete][xTete] = "tete";
        tableau[yPomme][xPomme] = "pomme";

        Thread entree = new Thread() {
            @Override
            public void run() {
                try {
                    GlobalScreen.registerNativeHook();
                    GlobalScreen.addNativeKeyListener(new Touche());
                } catch (NativeHookException e) {
                }

                while (!perdu) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        // AFFICHAGE INITIAL
        System.out.print("\033\143");
        // System.out.print("\033[?25l"); //cache le curseur
        // for (int y = 0; y < dimensions*4; y++) {
        // for (int x = 0; x < dimensions*8; x++) {
        // String galvaran = " ";
        // if (y%8<4 && x%16<8 || y%8>3 && x%16>7) galvaran = couleurFondUn+galvaran;
        // else galvaran = couleurFondDeux+galvaran;
        // System.out.print("\033["+(y+1)+";"+(x+1)+"H"+galvaran);
        // }
        // }
        // afficher("tete", yTete, xTete);
        // afficher("pomme", yPomme, xPomme);
        // System.out.print("\033[?25h"); //montre le curseur

        Display.cadre(1, 1, 42, 84, "\033[48;2;100;250;100m");
        System.out.print("\033[20;90H");

        yLast =1;
        xLast=0;


        aff();
        // début
        deplacementAuto = new Thread(new DeplacementAutomatique(sss));
        service.submit(deplacementAuto);
        entree.start();

        // fin
        try {
            entree.join();
        } catch (InterruptedException ex) {
            System.out.println("erreur jsp quoi");
        }
        service.shutdown();
        deplacementAuto.interrupt();

        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException nativeHookException) {
            nativeHookException.printStackTrace();
        }

        System.out.print("\033[41;1H");
        System.out.println("perdu lol");

        player.games.add(0, new Joueur.Partie(serpent.size(), date));
        if (serpent.size() > player.getHighScore())
            player.setHighScore(serpent.size());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    // public void nativeKeyPressed(NativeKeyEvent e) {
    //     switch (NativeKeyEvent.getKeyText(e.getKeyCode())) {
    //         case "Z":
    //             yLast = -1;
    //             xLast = 0;
    //             break;
    //         case "S":
    //             yLast = 1;
    //             xLast = 0;
    //             break;
    //         case "Q":
    //             yLast = 0;
    //             xLast = -1;
    //             break;
    //         case "D":
    //             yLast = 0;
    //             xLast = 1;
    //             break;
    //         default:
    //     }
    // }

    public static void aff() {
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
        String[][] tabaff = new String[40][80];

        for (int y = 0; y < tabaff.length; y++) {
            for (int x = 0; x < tabaff[y].length; x++) {
                String machin = "▀";

                if (!tableau[y / 4][x / 8].equals(" "))
                    machin = choses.get(tableau[y / 4][x / 8])[y % 4][x % 8] + machin;

                if (y % 8 < 4 && x % 16 < 8 || y % 8 > 3 && x % 16 > 7)
                    machin = couleurFondUn + machin;
                else
                    machin = couleurFondDeux + machin;

                tabaff[y][x] = machin;
            }
        }

        System.out.print("\033[s");
        // System.out.print("\033[2;3H");
        // for (String[] ligne : tabaff) {
        // for (String caractere : ligne) {
        // System.out.print(caractere+"\033[0m");
        // }
        // System.out.print("\n\033[2C");
        // }
        for (int y = 0; y < tabaff.length; y++) {
            for (int x = 0; x < tabaff[y].length; x++) {
                System.out.print("\033[" + (y + 2) + ";" + (x + 3) + "H" + tabaff[y][x] + "\033[0m");
            }
        }

        System.out.print("\033[u");
        System.out.print("\033[20;90H");

        DeplacementAutomatique.lastFrame = LocalTime.now();
    }

    public static void queueMangee() {

        for (int i = 1; i < serpent.size(); i++) {
            if (serpent.get(i)[0] == yTete && serpent.get(i)[1] == xTete) {
                perdu = true;
                break;
            }
        }

    }

    public static void pommeMangee() {

        if (yPomme == yTete && xPomme == xTete) {
            // cherche une case vide pour la pomme
            while (true) {
                yPomme = rng.nextInt(dimensions);
                xPomme = rng.nextInt(dimensions);

                int count = 0;
                for (int i = 0; i < serpent.size(); i++) {
                    if (!(yPomme == serpent.get(i)[0] && xPomme == serpent.get(i)[1]))
                        count++;
                }
                if (count == serpent.size())
                    break;
            }

            // affiche la nouvelle pomme
            tableau[yPomme][xPomme] = "pomme";
        } else {
            tableau[serpent.get(serpent.size() - 1)[0]][serpent.get(serpent.size() - 1)[1]] = " ";
            serpent.remove(serpent.size() - 1);
        }
    }

    public static void deplacement(int y, int x) {


        yLast = y;
        xLast = x;

        yTete += y;
        xTete += x;

        // sorti ?
        if (xTete < 0 || xTete > dimensions - 1 || yTete < 0 || yTete > dimensions - 1) {
            perdu = true;
            // yTete -= y; //sinon erreur ! en fait non sinon le mangeage de queue marche
            // pas
            // xTete -= x;
            System.out.println("sorti");
        } else {

            serpent.add(0, new int[] { yTete, xTete }); // nouvelle position de la tete

            for (int i = 1; i < serpent.size(); i++) {
                tableau[serpent.get(i)[0]][serpent.get(i)[1]] = "corps";
            }
            tableau[yTete][xTete] = "tete";

        }

        pommeMangee = false;
        if (yPomme == yTete && xPomme == xTete)
            pommeMangee = true;
    }

    // public static void affichage(){

    // System.out.print("\033[?25l"); //cache le curseur
    // System.out.print("\033[s"); //enregistre la posistion du curseur
    // System.out.print("\033[20;90H "); //efface l'input

    // //TETE
    // afficher("tete", yTete, xTete);

    // //CORPS
    // int yTemp = serpent.get(1)[0];
    // int xTemp = serpent.get(1)[1];
    // afficher("corps",yTemp,xTemp);

    // if (!pommeMangee) {
    // //VIDE
    // yTemp = serpent.get(serpent.size()-1)[0];
    // xTemp = serpent.get(serpent.size()-1)[1];
    // afficher(" ",yTemp,xTemp);
    // tableau[serpent.get(serpent.size()-1)[0]][serpent.get(serpent.size()-1)[1]] =
    // " ";
    // serpent.remove(serpent.size()-1);
    // } else {
    // //cherche une case vide pour la pomme
    // while (true) {
    // yPomme = rng.nextInt(dimensions);
    // xPomme = rng.nextInt(dimensions);

    // int count = 0;
    // for (int i=0; i<serpent.size(); i++) {
    // if (!(yPomme==serpent.get(i)[0] && xPomme==serpent.get(i)[1])) count++;
    // }
    // if (count==serpent.size()) break;
    // }
    // tableau[yPomme][xPomme] = "pomme";
    // //POMME

    // }
    // afficher("pomme", yPomme, xPomme); //avant en haut, dans le else

    // System.out.print("\033[0m");
    // System.out.print("\033[5;90H\033[52;38;2;125;0;0m"+serpent.size());

    // System.out.print("\033[u"); //replace en curseur à la position enregistrée
    // System.out.print("\033[?25h"); //affiche à nouveau le curseur

    // DeplacementAutomatique.lastFrame = LocalTime.now();
    // }

    // public static void afficher(String quoi, int yTruc, int xTruc) {
    // for (int y = 0; y < 4; y++) {
    // for (int x = 0; x < 8; x++) {
    // String galvaran=dessins.get(quoi)[y][x];
    // if (!galvaran.equals(" ")) galvaran = couleursFond.get(quoi) + galvaran;
    // else if (yTruc%2==1 && xTruc%2==1 || yTruc%2==0 && xTruc%2==0) galvaran =
    // couleurFondUn+galvaran;
    // else galvaran = couleurFondDeux+galvaran;
    // System.out.print("\033["+(yTruc*4+y+1)+";"+(xTruc*8+x+1)+"H"+couleurs.get(quoi)+galvaran);
    // }
    // }
    // Display.cadre(1, 1, 40, 80,"\033[48;2;100;250;100m");
    // }

}

class DeplacementAutomatique implements Runnable {
    static LocalTime mtn = LocalTime.now();
    static LocalTime lastFrame = LocalTime.now();
    static long duree;
    Game jsp = new Game();

    public DeplacementAutomatique(Game idk) {
        this.jsp = idk;
    }

    @Override
    public void run() {

        while (!Game.perdu) {
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds() * 1000
                    + Duration.between(lastFrame, mtn).getNano() / 1000000;

            if (duree >= 600) {
                Game.deplacement(Game.yLast, Game.xLast);

                //Game.queueMangee();
                // if (!ww.perdu) jsp.pommeMangee();

                //if (!Game.perdu)
                    Game.aff();
                lastFrame = mtn;
            }

        }

        Game.futur.cancel(true);

    }
}

class Touche implements NativeKeyListener {
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        switch (NativeKeyEvent.getKeyText(e.getKeyCode())) {
            case "Z":
                Game.yLast = -1;
                Game.xLast = 0;
                break;
            case "S":
                Game.yLast = 1;
                Game.xLast = 0;
                break;
            case "Q":
                Game.yLast = 0;
                Game.xLast = -1;
                break;
            case "D":
                Game.yLast = 0;
                Game.xLast = 1;
                break;
            default:
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}