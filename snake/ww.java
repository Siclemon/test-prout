package snake;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ww {
    static String[][] tableau =  new String[10][10];
    static ArrayList<int[]> serpent = new ArrayList<>(); //{{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> dessins = new HashMap<>();
    static HashMap<String, String> couleurs = new HashMap<>();
    static String[][] pomme = {{" "," "," "," "," "," "," "," "},{" "," ","▄","█","█","▄"," "," "},{" "," ","▀","█","█","▀"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] r = {{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] tete = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] corps = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    
    static int yTete = 5, xTete = 5;
    static Random rng = new Random();
    static int yPomme = rng.nextInt(10), xPomme = rng.nextInt(10);
    static Thread test = new Thread(new chevreuil());
    static int[] lastMove = {-1,0};
    static int yLast = -1, xLast = 0;
    static boolean perdu = false;
    static Scanner sc = new Scanner(System.in);

    static Future<String> fut;

    public static void main(String[] args) throws IOException {
        ww sss = new ww();
        Scanner sc = new Scanner(System.in);

        ExecutorService exec = Executors.newSingleThreadExecutor();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char input;
        fut = exec.submit(new InputSnake());
        
        //test.start();
        //test.stop();
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

        
        

        do {


            try {

                sss.affichage(tableau);

                fut = exec.submit(new InputSnake());
                input = fut.get().charAt(0);


                switch (input) {
                    case 'z':
                        sss.deplacement(-1,0);
                        break;

                    case 'q' :
                        sss.deplacement(0,-1);
                        break;
                        
                    case 's':
                        sss.deplacement(1,0);
                        break;

                    case 'd' :
                        sss.deplacement(0,1);
                        break;
                
                    default:
                        break;
                }


            } catch (Exception e) {
                System.out.println("pas d'input");
            }


            if (yPomme==yTete && xPomme==xTete) sss.pommeMangee();
            else {
                tableau[serpent.get(serpent.size()-1)[0]][serpent.get(serpent.size()-1)[1]] = " ";
                serpent.remove(serpent.size()-1);
            }

            for (int[] partieDuCorps : serpent) {
                if (partieDuCorps == new int[] {yTete,xTete}) {
                    perdu=true;
                    break;
                }
            }
        } while (!perdu);
        fut.cancel(true);
        System.out.println("aaaa");
        sc.close();

    }

    public void pommeMangee() {
        //retire la pomme
        //tableau[yxPomme[0]][yxPomme[1]] = " ";

        //cherche une case vide pour la pomme
        while (true) { 
            yPomme = rng.nextInt(10);
            xPomme = rng.nextInt(10);

            int count =0;
            for (int i=0; i<serpent.size(); i++) {
                if (!(yPomme==serpent.get(i)[0] && xPomme==serpent.get(i)[1])) count++;
            }
            if (count==serpent.size()) break;
        }

        //affiche la nouvelle pomme
        tableau[yPomme][xPomme] = "pomme";
    }



    public void deplacement(int y, int x){

        yLast = y;
        xLast = x;

        yTete += y;
        xTete += x;

        if (xTete==-1 || xTete==10 || yTete==-1 || yTete==10) {
            perdu = true;
            // yTete -= y; //sinon erreur
            // xTete -= x;
            System.out.println("erreur");
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

    public void run () {

        while (!ww.perdu) { 
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds()*1000+Duration.between(lastFrame, mtn).getNano()/1000000;

            if (duree>=750-3*ww.serpent.size()) {
                jsp.deplacement(ww.yLast, ww.xLast);

                if (ww.yPomme==ww.yTete && ww.xPomme==ww.xTete) jsp.pommeMangee();
                else {
                    ww.tableau[ww.serpent.get(ww.serpent.size()-1)[0]][ww.serpent.get(ww.serpent.size()-1)[1]] = " ";
                    ww.serpent.remove(ww.serpent.size()-1);
                }

                jsp.affichage(ww.tableau);
            }
            
        }

    }
}


