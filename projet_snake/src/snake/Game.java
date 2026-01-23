package snake;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Game {
    static final int dimensions = 10;
    static String[][] tableau =  new String[dimensions][dimensions];
    static ArrayList<int[]> serpent = new ArrayList<>(); //{{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> choses = new HashMap<>();

    static int yTete, xTete;
    static Random rng = new Random();
    static int yPomme = rng.nextInt(dimensions), xPomme = rng.nextInt(dimensions);
    static Thread deplacementAuto = new Thread(new DeplacementAutomatique());
    static int yLast = -1, xLast = 0;
    static boolean perdu = false;
    static boolean pommeMangee = false;
    static String couleurFondUn = "\033[48;2;47;138;40m",couleurFondDeux = "\033[48;2;34;112;28m";


    static Future<String> futur;
    static ExecutorService exec;

    static LocalDateTime date;

    Skins skinsList = new Skins();

    public Game() {
        yTete = dimensions/2;
        xTete = dimensions/2;
        perdu = false;
        tableau =  new String[dimensions][dimensions];
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
        //Game sss = new Game();
        
        ExecutorService service = Executors.newCachedThreadPool();
        exec = Executors.newSingleThreadExecutor();



        // Map<String,String[][]> skinsFruit = skinsList.getSkins("fruit");
        // String quelFuit = player.skins.getFruit();
        // String[][] fruit = skinsFruit.get(quelFuit);

        choses.put("pomme", skinsList.getSkins("fruit").get(player.skins.fruit));
        choses.put("tete", skinsList.getSkins("head").get(player.skins.head));
        choses.put("corps", skinsList.getSkins("body").get(player.skins.body));
        //choses.put("fond", skinsList.getSkins("back").get(player.skins.back));
        couleurFondUn = skinsList.getBack().get(player.skins.back)[1];
        couleurFondDeux = skinsList.getBack().get(player.skins.back)[2];
    

        for (String[] ligne : tableau) Arrays.fill(ligne, " ");

        serpent.add(0,new int[] {yTete,xTete});
        //serpent.add(new int[] {0,0});

        tableau[yTete][xTete] = "tete";
        tableau[yPomme][xPomme] = "pomme";

        Thread entree = new Thread() {
            @Override
            public void run() {
                do {
                    
                    try {
                        char input;
                        //sss.affichage();
                        //sss.aff();
                        System.out.print("\033[20;85H");

                        futur = exec.submit(new InputSnake());
                        input = futur.get().charAt(0);
                        System.out.print("\033[20;85H");


                        switch (input) {
                            case 'z'-> {
                                yLast = -1;
                                xLast = 0;
                            }

                            case 'q' -> {
                                yLast = 0;
                                xLast = -1;
                            }

                            case 's'-> {
                                yLast = 1;
                                xLast = 0;
                            }

                            case 'd' -> {
                                yLast = 0;
                                xLast = 1;
                            }

                            case 'o' -> {
                                yPomme = yTete;
                                xPomme = xTete;
                                //afficher(" ",yPomme,xPomme);
                                //sss.deplacement(yLast, xLast);
                            }
                        }

                    } catch (InterruptedException ex) {
                        System.out.println("jabadabada");
                    } catch (ExecutionException ee) {
                        System.out.println("ee");
                    } catch (CancellationException e) {
                        System.out.println("cancel");
                    }



                } while (!perdu);
                exec.shutdownNow();
            }
        };
        
        //AFFICHAGE INITIAL
        System.out.print("\033\143");
        Display.cadre(1, 1, 42, 84,"\033[48;2;100;250;100m");
        Display.gameInfo(player);
        System.out.print("\033[20;85H");

        service.submit(deplacementAuto);

        entree.start();
        



        try {
            entree.join();
        } catch (InterruptedException ex) {
            System.out.println("erreur jsp quoi");;
        }
        
        service.shutdown();

        
        deplacementAuto.interrupt();

        System.out.print("\033[41;1H");
        System.out.println("perdu lol");

        player.games.add(0,new Joueur.Partie(serpent.size(), date, player.getPseudo()));
        if (serpent.size()>player.getHighScore()) player.setHighScore(serpent.size());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    public void aff() {
        String[][] tabaff = new String[40][80];

        for (int y=0; y<tabaff.length; y++) {
            for (int x = 0; x < tabaff[y].length; x++) {
                String machin = "▀";

                if (tableau[y/4][x/8] != " ")
                    machin = choses.get(tableau[y/4][x/8])[y%4][x%8] + machin;

                if (y%8<4 && x%16<8 || y%8>3 && x%16>7) machin = couleurFondUn + machin;
                else machin = couleurFondDeux + machin;

                tabaff[y][x] = machin;
            }
        }

        System.out.print("\033[?25l");
        System.out.print("\033[2;3H");
        // for (String[] ligne : tabaff) {
        //     for (String caractere : ligne) {
        //         System.out.print(caractere+"\033[0m");
        //     }
        //     System.out.print("\n\033[2C");
        // }
        for (int y=0; y<tabaff.length; y++) {
            for (int x = 0; x < tabaff[y].length; x++) {
                System.out.print("\033["+(y+2)+";"+(x+3)+"H"+tabaff[y][x]+"\033[0m");
            }
        }
        System.out.print("\033[?25h");

        DeplacementAutomatique.lastFrame = LocalTime.now();
    }

    public void queueMangee() {

        for (int i=1; i<serpent.size(); i++) {
            if (serpent.get(i)[0] == yTete && serpent.get(i)[1] == xTete) {
                perdu=true;
                break;
            }
        }
    }

    public void pommeMangee() {

        if (yPomme==yTete && xPomme==xTete) {
            //cherche une case vide pour la pomme
            while (true) { 
                yPomme = rng.nextInt(dimensions);
                xPomme = rng.nextInt(dimensions);

                int count =0;
                for (int i=0; i<serpent.size(); i++) {
                    if (!(yPomme==serpent.get(i)[0] && xPomme==serpent.get(i)[1])) count++;
                }
                if (count==serpent.size()) break;
            }

            //affiche la nouvelle pomme
            tableau[yPomme][xPomme] = "pomme";
        } else {
            tableau[serpent.get(serpent.size()-1)[0]][serpent.get(serpent.size()-1)[1]] = " ";
            serpent.remove(serpent.size()-1);
        }
    }



    public void deplacement(int y, int x){

        yLast = y;
        xLast = x;

        yTete += y;
        xTete += x;

        //sorti ?
        if (xTete<0 || xTete>dimensions-1 || yTete<0 || yTete>dimensions-1) {
            perdu = true;
            // yTete -= y; //sinon erreur     ! en fait non sinon le mangeage de queue marche pas
            // xTete -= x;
            System.out.println("sorti");
        } else {

            serpent.add(0,new int[] {yTete,xTete}); //nouvelle position de la tete

            for (int i=1; i<serpent.size(); i++) {
                tableau[serpent.get(i)[0]][serpent.get(i)[1]] = "corps";
            }
            tableau[yTete][xTete] = "tete";

        }
    }
}

class DeplacementAutomatique implements Runnable {
    static LocalTime mtn = LocalTime.now();
    static LocalTime lastFrame = LocalTime.now();
    static long duree;
    Game jsp = new Game();

    @Override
    public void run () {

        while (!Game.perdu) { 
            mtn = LocalTime.now();
            duree=Duration.between(lastFrame, mtn).toMillis();

            if (duree>=200-Game.serpent.size()) {
                jsp.deplacement(Game.yLast, Game.xLast);

                jsp.queueMangee();

                if (!Game.perdu) {
                    jsp.pommeMangee();
                    jsp.aff();
                }
                System.out.print("\033[22;85H");
            }
            
        }

        Game.futur.cancel(true);

    }
}


