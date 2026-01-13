
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

import snake.src.InputSnake;


public class www {
    static int dimensions = 10;
    static String[][] tableau =  new String[dimensions][dimensions];
    static ArrayList<int[]> serpent = new ArrayList<>(); //{{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> dessins = new HashMap<>();
    static HashMap<String, String> couleurs = new HashMap<>();
    static HashMap<String, String> couleursFond = new HashMap<>();
    static String[][] pomme = {{" "," "," "," "," "," "," "," "},{" "," ","▄","█","█","▄"," "," "},{" "," ","▀","█","█","▀"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] r = {{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] tete = {{" "," "," "," "," "," "," "," "},{" "," ","█","▀","▀","█"," "," "},{" "," ","█","▄","▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] corps = {{" "," "," "," "," "," "," "," "},{" "," ","█","▀","▀","█"," "," "},{" "," ","█","▄","▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    
    static int yTete, xTete;
    static Random rng = new Random();
    static int yPomme = rng.nextInt(dimensions), xPomme = rng.nextInt(dimensions);
    static Thread test = new Thread(new chevreuill());
    static int[] lastMove = {-1,0};
    static int yLast = -1, xLast = 0;
    static boolean perdu = false;
    static boolean pommeMangee = false;
    static String couleurFondUn = "\033[48;2;47;138;40m",couleurFondDeux = "\033[48;2;34;112;28m";


    static Future<String> fut;

    public www() {
        yTete = dimensions/2;
        xTete = dimensions/2;
        perdu = false;
        dimensions = 10;
        tableau =  new String[dimensions][dimensions];
        serpent = new ArrayList<>(); 
        dessins = new HashMap<>();
        couleurs = new HashMap<>();
        couleursFond = new HashMap<>();
        rng = new Random();
        yPomme = rng.nextInt(dimensions);
        xPomme = rng.nextInt(dimensions);
        lastMove = new int[] {-1,0};
        yLast = -1;
        xLast = 0;
        perdu = false;
        pommeMangee = false;
        couleurFondUn = "\033[48;2;47;138;40m";
        couleurFondDeux = "\033[48;2;34;112;28m";
    }

    public static void main(String[] args) throws IOException {
        www sss = new www();
        

        ExecutorService exec = Executors.newSingleThreadExecutor();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //fut = exec.submit(new InputSnake());
        
        test.start();

        dessins.put(" ", r);
        dessins.put("pomme", pomme);
        dessins.put("tete",tete);
        dessins.put("corps", corps);

        couleurs.put(" ", "");
        couleurs.put("pomme", "\033[38;2;156;13;3m");
        couleurs.put("tete","\033[38;2;250;220;0m");
        couleurs.put("corps", "\033[38;2;200;160;0m");

        //couleursFond.put(" ", "");
        couleursFond.put("pomme", "\033[48;2;200;200;200m");
        couleursFond.put("tete","\033[48;2;180;90;15m");
        couleursFond.put("corps", "\033[48;2;170;80;10m");
    

        for (String[] ligne : tableau) Arrays.fill(ligne, " ");

        serpent.add(0,new int[] {yTete,xTete});
        serpent.add(new int[] {0,0});

        tableau[yTete][xTete] = "tete";
        tableau[yPomme][xPomme] = "pomme";

        Thread entree = new Thread() {
            @Override
            public void run() {
                do {
                    
                    try {
                        char input;
                        
                        sss.affichage(tableau);
                        System.out.print("\033[20;85H");
                        fut = exec.submit(new InputSnake());
                        input = fut.get().charAt(0);
                        System.out.print("\033[20;85H");

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


                } while (!perdu);
                //fut.cancel(true);
                exec.shutdownNow();
            }
        };
        
        //AFFICHAGE INITIAL
        System.out.print("\033\143");
        System.out.print("\033[?25l"); //cache le curseur
        for (int y = 0; y < dimensions*4; y++) {
            for (int x = 0; x < dimensions*8; x++) {
                String galvaran = " ";
                if (y/4 == yTete && x/8 == xTete) galvaran=tete[y%4][x%8];
                else if (y/4 == yPomme && x/8 == xPomme) galvaran=couleurs.get("pomme")+pomme[y%4][x%8];
                if (y%8<4 && x%16<8 || y%8>3 && x%16>7) galvaran = couleurFondUn+galvaran;
                else galvaran = couleurFondDeux+galvaran;
                System.out.print("\033["+(y+1)+";"+(x+1)+"H"+galvaran);
            }
        }
        System.out.print("\033[?25h"); //montre le curseur
        System.out.print("\033[20;85H");
        

        entree.start();

        try {
            entree.join();
        } catch (InterruptedException ex) {
            System.out.println("erreur jsp quoi");;
        }
        test.interrupt();

        System.out.print("\033[41;1H");
        System.out.println("perdu lol");
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

        pommeMangee = false;
        if (yPomme==yTete && xPomme==xTete) pommeMangee = true;
    }

    
    public void affichage(String[][] tab){

        System.out.print("\033[?25l"); //cache le curseur
        System.out.print("\033[s"); //enregistre la posistion du curseur

        System.out.print("\033[20;85H        "); //efface l'input

        //TETE
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 8; x++) {
                String galvaran=tete[y][x];
                if (!galvaran.equals(" ")) galvaran = couleursFond.get("tete") + galvaran;
                else if (yTete%2==1 && xTete%2==1 || yTete%2==0 && xTete%2==0) galvaran = couleurFondUn+galvaran;
                else galvaran = couleurFondDeux+galvaran;
                System.out.print("\033["+(yTete*4+y+1)+";"+(xTete*8+x+1)+"H"+couleurs.get("tete")+galvaran);
            }
        }
        //CORPS
        int yTemp = serpent.get(1)[0];
        int xTemp = serpent.get(1)[1];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 8; x++) {
                String galvaran=corps[y][x];
                if (!galvaran.equals(" ")) galvaran = couleursFond.get("corps") + galvaran;
                else if (yTemp%2==1 && xTemp%2==1 || yTemp%2==0 && xTemp%2==0) galvaran = couleurFondUn+galvaran;
                else galvaran = couleurFondDeux+galvaran;
                System.out.print("\033["+(yTemp*4+y+1)+";"+(xTemp*8+x+1)+"H"+couleurs.get("corps")+galvaran);
            }
        }
        if (!pommeMangee) {
            //VIDE
            yTemp = serpent.get(serpent.size()-1)[0];
            xTemp = serpent.get(serpent.size()-1)[1];
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 8; x++) {
                    String galvaran=r[y][x];
                    if (yTemp%2==1 && xTemp%2==1 || yTemp%2==0 && xTemp%2==0) galvaran = couleurFondUn+galvaran;
                    else galvaran = couleurFondDeux+galvaran;
                    System.out.print("\033["+(yTemp*4+y+1)+";"+(xTemp*8+x+1)+"H"+galvaran);
                }
            }
            tableau[serpent.get(serpent.size()-1)[0]][serpent.get(serpent.size()-1)[1]] = " ";
            serpent.remove(serpent.size()-1);
        } else {
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
            tableau[yPomme][xPomme] = "pomme";
            //POMME
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 8; x++) {
                    String galvaran=pomme[y][x];
                    if (yPomme%2==1 && xPomme%2==1 || yPomme%2==0 && xPomme%2==0) galvaran = couleurFondUn+galvaran;
                    else galvaran = couleurFondDeux+galvaran;
                    System.out.print("\033["+(yPomme*4+y+1)+";"+(xPomme*8+x+1)+"H"+couleurs.get("pomme")+galvaran);
                }
            }
        }

        System.out.print("\033[0m");
        System.out.print("\033[5;85H\033[53;38;2;125;0;0m"+serpent.size());

        System.out.print("\033[u"); //replace en curseur à la position enregistrée
        System.out.print("\033[?25h"); //affiche à nouveau le curseur



        //System.out.println(chevreuil.duree);
        chevreuill.lastFrame = LocalTime.now();

        // for (int[] a : serpent){ juste pour voir les coordonnées du serpent
        //     for (int b : a){
        //         System.out.print(b+" ");
        //     }
        //     System.out.println();
        // }

    }

    

}

class chevreuill implements Runnable {
    static LocalTime mtn = LocalTime.now();
    static LocalTime lastFrame = LocalTime.now();
    static long duree;
    www jsp = new www();

    @Override
    public void run () {

        while (!www.perdu) { 
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds()*1000+Duration.between(lastFrame, mtn).getNano()/1000000;

            if (duree>=700-8*www.serpent.size()) {
                jsp.deplacement(www.yLast, www.xLast);

                jsp.queueMangee();
                //if (!ww.perdu) jsp.pommeMangee();

                if (!www.perdu) jsp.affichage(www.tableau);
            }
            
        }

        www.fut.cancel(true);

    }
}


