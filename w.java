import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class w {
    static String[][] tableau =  new String[10][10];
    static ArrayList<int[]> serpent = new ArrayList<>(); //{{yTete,xTete}{ySeg1,xSeg1}{ySeg2,xSeg2}}
    static HashMap<String, String[][]> dessins = new HashMap<>();
    static String[][] pomme = {{" "," "," "," "," "," "," "," "},{" "," ","▄","█","█","▄"," "," "},{" "," ","▀","█","█","▀"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] r = {{}};
    static String[][] tete = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    static String[][] corps = {{" "," "," "," "," "," "," "," "},{" "," ","█","\033[104m▀","\033[104m▀","█"," "," "},{" "," ","█","\033[104m▄","\033[104m▄","█"," "," "},{" "," "," "," "," "," "," "," "}};
    
    static int[] yxTete = {5,5};
    static ArrayList<Integer> xSerpent = new ArrayList<>();
    static ArrayList<Integer> ySerpent = new ArrayList<>();
    static Random rng = new Random();
    static int[] yxPomme = {rng.nextInt(10),rng.nextInt(10)};

    public static void main(String[] args) {
        w sss = new w();
        String[][] image;
        
        int xRand, yRand;
        Scanner sc = new Scanner(System.in);
        char input;
        

        
        dessins.put("", r);
        dessins.put("pomme", pomme);
        dessins.put("tete",tete);
        dessins.put("corps", corps);
    
        serpent.add(yxTete);


        image = new String[40][80];

        
        
        yRand = 4*rng.nextInt(1,11)-2;
        xRand = 8*rng.nextInt(1,11)-4;

        String rouge ="\033[1;31m";
        image[yRand][xRand] = rouge + "/";
        image[yRand][xRand-1] = rouge + "\\";
        image[yRand-1][xRand] = rouge + "\\";
        image[yRand-1][xRand-1] = rouge + "/";

        tableau[rng.nextInt(10)][rng.nextInt(10)] = "pomme";

        
        

        while (true) {

            sss.affichage(tableau);

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

        }






        
    }

    public void deplacement(int y, int x){

        yxTete[0] = yxTete[0]+y;
        yxTete[1] = yxTete[1]+x;

        serpent.add(0,yxTete.clone()); //nouvelle position de la tete
        if (yxPomme[0]!=yxTete[0] || yxPomme[1]!=yxTete[1]) serpent.remove(serpent.size()-1); //retire le bout du serpent

        // ySerpent.add(0,ySerpent.get(0)+y);
        // xSerpent.add(0,xSerpent.get(0)+x);

        // ySerpent.removeLast();
        // xSerpent.removeLast();
    }

    public void affichage(String[][] tab){
        String[][] frame = new String[tab.length*4][tab[1].length*8];

        for (int y=0;y<frame.length;y++) {
            Arrays.fill(frame[y], " ");
        }

        for (int y=0;y<frame.length;y++) {
            for (int x=0; x<frame[y].length; x++) {
                //pomme
                if (tab[y/4][x/8]=="pomme") frame[y][x] = "\033[31m"+dessins.get("pomme")[y%4][x%8];

                //corps
                for (int i=1; i<serpent.size(); i++) {
                    if (y/4==serpent.get(i)[0] && x/8==serpent.get(i)[1]) frame[y][x] = "\033[36m"+dessins.get("corps")[y%4][x%8];
                }
                //tete
                if (y/4==serpent.get(0)[0] && x/8==serpent.get(0)[1]) frame[y][x] = "\033[34m"+dessins.get("tete")[y%4][x%8];

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

        // for (int[] a : serpent){ juste pour voir les coordonnées du serpent
        //     for (int b : a){
        //         System.out.print(b+" ");
        //     }
        //     System.out.println();
        // }

    }

    

}
