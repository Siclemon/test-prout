package snake;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Animation {
    static ArrayList<Integer> ySerpent = new ArrayList<>();
    static ArrayList<Integer> xSerpent =  new ArrayList<>();
    static boolean stop = false;
    static int yMinc, xMinc, hauteurc, largeurc;
    static String positionCurseur;

    public static void anim(int yMin, int xMin, int hauteur, int largeur, String posCurseur) {
        yMinc = yMin;
        xMinc = xMin;
        hauteurc = hauteur;
        largeurc = largeur;
        positionCurseur = posCurseur;
        Thread thread = new Thread(new DeplacementAnim());
        stop = false;

        System.out.print(positionCurseur);
        System.out.print("\033[s");
        
        initialisation();
        thread.start();

    }

    public static void initialisation() {
        stop = false;

        //System.out.print("\033[s");
        System.out.print("\033[?25l");

        damier(yMinc,xMinc,hauteurc,largeurc);
        ySerpent.clear();
        xSerpent.clear();
        ySerpent.add(yMinc+hauteurc);
        xSerpent.add(xMinc+2*largeurc);
        System.out.print("\033[u");
        afficher("\033[38;2;250;220;0m",ySerpent.get(0),xSerpent.get(0), true);

        

    }

    public static void deplacement(int dy, int dx) {

        ySerpent.add(0,ySerpent.get(0)+dy);
        xSerpent.add(0,xSerpent.get(0)+dx);

        if(new Random().nextInt(8) != 0) {

            afficher("\033[38;2;250;220;0m", (int)ySerpent.get(ySerpent.size()-1), xSerpent.get(xSerpent.size()-1), false);

            ySerpent.remove(ySerpent.size()-1);
            xSerpent.remove(xSerpent.size()-1);
        }

        
        for (int i=1; i<ySerpent.size();i++) {
            afficher("\033[38;2;200;160;0m", ySerpent.get(i), xSerpent.get(i), true);
        }
        afficher("\033[38;2;250;220;0m", ySerpent.get(0), xSerpent.get(0), true);

    }

    public static void choixDeplacement() {
        Random rng = new Random();
        int rand = rng.nextInt(4);

        switch (rand) {
            case 0:
                check(2, 0);
                break;
            case 1:
                check(-2, 0);
                break;
            case 2:
                check(0, 4);
                break;
            case 3:
                check(0, -4);

                break;
        }
    }

    public static void check(int dy, int dx) {

        if (ySerpent.get(0)+dy < yMinc || ySerpent.get(0)+dy >= yMinc+2*hauteurc) return;
        if (xSerpent.get(0)+dx < xMinc || xSerpent.get(0)+dx >= xMinc+4*largeurc) return;

        for (int i=1; i<ySerpent.size(); i++) {
            if (ySerpent.get(0)+dy == ySerpent.get(i) && xSerpent.get(0)+dx == xSerpent.get(i)) return;
        }
        
        DeplacementAnim.moved = true;
        deplacement(dy, dx);
    }


    public static void afficher(String couleur, int yTruc, int xTruc, boolean truc) {

        String fond;
        String[] forme;

        if (yTruc%4<2 && xTruc%8<4 || yTruc%4>1 && xTruc%8>3) fond = "\033[48;2;47;138;40m";
        else fond = "\033[48;2;34;112;28m";

        if (truc) forme = new String[] {"▄▄","▀▀"};
        else forme = new String[] {"  ","  "};

        System.out.print("\033[s");
        System.out.print("\033[?25l");
        
        System.out.print(fond + couleur + "\033[" + (yTruc) + ";" + (xTruc+1) + "H" + forme[0]);
        System.out.print(fond + couleur + "\033[" + (yTruc+1) + ";" + (xTruc+1) + "H" + forme[1]+"\033[0m");

        System.out.print("\033[u");
        System.out.print("\033[?25h");

    }

    public static void damier(int yMin, int xMin, int hauteur, int largeur) {
        int yMax = yMin + 2*hauteur;
        int xMax = xMin + 4*largeur;
        int yy = 0, xx = 0;
        String couleurFondUn = "\033[48;2;47;138;40m",couleurFondDeux = "\033[48;2;34;112;28m";

        for (int y = yMin; y<yMax; y++) {
            for (int x = xMin; x<xMax; x++) {
                if (yy%4<2 && xx%8<4 || yy%4>1 && xx%8>3) System.out.print(couleurFondUn + "\033[" + y + ";" + x + "H ");
                else System.out.print(couleurFondDeux + "\033[" + y + ";" + x + "H ");
                xx++;
            }
            yy++;
        }
    }

    public static void stop() {
        stop = true;
    }
}



class DeplacementAnim implements Runnable {
    static LocalTime mtn = LocalTime.now();
    static LocalTime lastFrame = LocalTime.now();
    static long duree;
    static boolean moved;
    static int tests;

    @Override
    public void run () {

        while (!Animation.stop) { 
            mtn = LocalTime.now();
            duree = Duration.between(lastFrame, mtn).getSeconds()*1000+Duration.between(lastFrame, mtn).getNano()/1000000;

            if (duree>=200) {
                moved = false;
                tests = 0;

                while (!moved) {
                    tests++;

                    if (tests>=20) Animation.initialisation();

                    Animation.choixDeplacement();
                }

                lastFrame = LocalTime.now();
            }
            
        }


    }

}