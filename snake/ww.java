package snake;
import java.io.IOException;
import java.time.Duration;
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


public class ww {
    static int dimensions = 10;
    static String[][] tableau =  new String[dimensions][dimensions];
    static ArrayList<int[]> serpent = new ArrayList<>(); //{{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> dessins = new HashMap<>();
    static HashMap<String, String> couleurs = new HashMap<>();
    static String[][] pomme = {{" "," "," "," "," "," "," "," "},{" "," ","▄","█","█","▄"," "," "},{" "," ","▀","█","█","▀"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] r = {{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] tete = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] corps = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    
    static int yTete = dimensions/2, xTete = dimensions/2;
    static Random rng = new Random();
    static int yPomme = rng.nextInt(dimensions), xPomme = rng.nextInt(dimensions);
    static Thread test = new Thread(new chevreuil());
    static int[] lastMove = {-1,0};
    static int yLast = -1, xLast = 0;
    static boolean perdu = false;


    static Future<String> fut;

    public static void main(String[] args) throws IOException {
        ww sss = new ww();


        ExecutorService exec = Executors.newSingleThreadExecutor();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //fut = exec.submit(new InputSnake());
        
        test.start();

        dessins.put(" ", r);
        dessins.put("pomme", pomme);
        dessins.put("tete",tete);
        dessins.put("corps", corps);

        couleurs.put(" ", "");
        couleurs.put("pomme", "\033[31m");
        couleurs.put("tete","\033[34m");
        couleurs.put("corps", "\033[36m");
    

        for (String[] ligne : tableau) Arrays.fill(ligne, " ");

        serpent.add(0,new int[]{yTete,xTete});

        tableau[yTete][xTete] = "tete";
        tableau[yPomme][xPomme] = "pomme";

        Thread entree = new Thread() {
            @Override
            public void run() {
                do {
                    
                    try {
                        char input;
                        
                        sss.affichage(tableau);
                        System.out.println("Bonjour les amis !");
                        
                        fut = exec.submit(new InputSnake());
                        input = fut.get().charAt(0);


                        switch (input) {
                            case 'z'-> sss.deplacement(-1,0);

                            case 'q' -> sss.deplacement(0,-1);

                            case 's'-> sss.deplacement(1,0);

                            case 'd' -> sss.deplacement(0,1);

                            case 'o' -> {
                                sss.deplacement(yLast, xLast);
                                tableau[yPomme][xPomme] = " ";
                                yPomme = yTete;
                                xPomme = xTete;
                            }
                        }


                    // } catch (Exception e) {
                    //     System.out.println("pas d'input");
                    } catch (InterruptedException ex) {
                        System.out.println("jabadabada");
                    } catch (ExecutionException ee) {
                        System.out.println("ee");
                    } catch (CancellationException e) {
                        System.out.println("cancel");
                    }

                    //est-ce que la queue est mangée ?
                    sss.queueMangee();

                    //est-ce que la pomme est mangée ?
                    if (!perdu) sss.pommeMangee();

                } while (!perdu);
                System.out.println("ooooo-");
                //fut.cancel(true);
                System.out.println("oooooh");
                exec.shutdownNow();
                System.out.println("cradopaud");
            }
        };
        
        System.out.println("bonjour les enfantsd\nddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd\nddddddddddddddddddddddddddddddddddddddddddddd\nddddddddddddddddddddddddddddddddddddddddddddd\nddddddddddddddddddddddddddddddddddddddddddddd\nddddddddddddddddddddddddddddddddddddddddddddd\n");

        entree.start();

        try {
            entree.join();
        } catch (InterruptedException ex) {
            System.out.println("erreur jsp quoi");;
        }

        System.out.println("samer");
        //while (perdu);
        System.out.println("lipopette");

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

    
    public void affichage(String[][] tab){
        String[][] frame = new String[tab.length*4][tab[1].length*8];

        for (int y=0;y<frame.length;y++) {
            Arrays.fill(frame[y], " ");
        }

        for (int y=0;y<frame.length;y++) {
            for (int x=0; x<frame[y].length; x++) {

                frame[y][x] = couleurs.get(tab[y/4][x/8]) + dessins.get(tab[y/4][x/8])[y%4][x%8];

                //ajout de la couleur de fond
                if (y%8<4 && x%16<8 || y%8>3 && x%16>7) frame[y][x] = "\033[42m"+frame[y][x];
                else frame[y][x] = "\033[102m"+frame[y][x];
            }
        }

        System.out.println("\033\143");
        for (String[] ligne : frame) {
             for (String truc : ligne) {
                 System.out.print(truc+"\033[0m");
            }
            System.out.println();
        }

        System.out.println(chevreuil.duree);

        chevreuil.lastFrame = LocalTime.now();

        // for (int[] a : serpent){ juste pour voir les coordonnées du serpent
        //     for (int b : a){
        //         System.out.print(b+" ");
        //     }
        //     System.out.println();
        // }

    }

    

}

class chevreuil implements Runnable {
    static LocalTime mtn = LocalTime.now();
    static LocalTime lastFrame = LocalTime.now();
    static long duree;
    ww jsp = new ww();

    @Override
    public void run () {

        while (!ww.perdu) { 
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds()*1000+Duration.between(lastFrame, mtn).getNano()/1000000;

            if (duree>=700-8*ww.serpent.size()) {
                jsp.deplacement(ww.yLast, ww.xLast);

                jsp.queueMangee();
                if (!ww.perdu) jsp.pommeMangee();

                jsp.affichage(ww.tableau);
            }
            
        }

        ww.fut.cancel(true);

    }
}


