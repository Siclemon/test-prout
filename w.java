import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class w {
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

    public static void main(String[] args) {
        w sss = new w();

        Scanner sc = new Scanner(System.in);
        
        test.start();
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

            sss.affichage(tableau);
            // System.out.println(serpent.get(serpent.size()-1)[0]+" "+serpent.get(serpent.size()-1)[1]);
            // System.out.println(tableau[serpent.get(serpent.size()-1)[0]][serpent.get(serpent.size()-1)[1]]);
            // System.out.println(tableau[4][5]);
            // System.out.println(tableau[5][5]);
            // for (int[] elem : serpent) {
            //     System.out.println(elem[0]+"-"+elem[1]);
            // }

            switch (sc.next().charAt(0)) {
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
                // //pomme
                // if (tab[y/4][x/8]=="pomme") frame[y][x] = "\033[31m"+dessins.get("pomme")[y%4][x%8];

                // //corps
                // for (int i=1; i<serpent.size(); i++) {
                //     if (y/4==serpent.get(i)[0] && x/8==serpent.get(i)[1]) frame[y][x] = "\033[36m"+dessins.get("corps")[y%4][x%8];
                // }
                // //tete
                // if (y/4==serpent.get(0)[0] && x/8==serpent.get(0)[1]) frame[y][x] = "\033[34m"+dessins.get("tete")[y%4][x%8];

                frame[y][x] = couleurs.get(tab[y/4][x/8]) + dessins.get(tab[y/4][x/8])[y%4][x%8];

                //couleur de fond
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
    w jsp = new w();

    public void run () {

        while (!w.perdu) { 
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds()*1000+Duration.between(lastFrame, mtn).getNano()/1000000;

            if (duree>=750-3*w.serpent.size()) {
                jsp.deplacement(w.yLast, w.xLast);

                if (w.yPomme==w.yTete && w.xPomme==w.xTete) jsp.pommeMangee();
                else {
                    w.tableau[w.serpent.get(w.serpent.size()-1)[0]][w.serpent.get(w.serpent.size()-1)[1]] = " ";
                    w.serpent.remove(w.serpent.size()-1);
                }

                jsp.affichage(w.tableau);
                //System.out.println("a");
            }
            
        }

    }
}


