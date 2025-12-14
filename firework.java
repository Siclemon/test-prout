import java.util.Random;
import java.util.Scanner;

public class firework {
    public static void main(String[] args) {
        int hauteur = 20, largeur;
        String carac;
        int xMin, xMax, yMin, yMax;
        Scanner scan = new Scanner(System.in);
        double distance;
        Random rng = new Random();
        int rand;

        System.out.println("taille ?");
        hauteur = scan.nextInt();

        largeur = (int)((float)hauteur*2);
        yMin = -hauteur/2;
        yMax = -yMin;
        xMin = yMin*2;
        xMax = -xMin;


        for (int i=yMin; i<hauteur; i++) {

            try {
                Thread.sleep(250-hauteur*2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.print("\033\143");

            for (int y = yMax; y>=yMin; y--){
                for (int x = xMin; x<=xMax; x++){
                    distance = Math.sqrt(x*x/4+y*y);
                    rand = rng.nextInt(1,11);

                    //contour
                    if (Math.abs(x)==xMax || Math.abs(y)==yMax) carac = "@";
                    else carac = " ";
                        
                    //montée
                    if (x==0 && i<0 && y!=yMin) {
                        if (y==i) carac = "o";
                        else if (y==i-1) carac = "|";
                        else if (y==i-2) carac = "'";
                    }

                    if (Math.abs(x)<=i*2 && Math.abs(y)<=i && distance<yMax-2) {
                        
                        carac=".";
                        
                        if (Math.abs(y)>=0 && Math.abs(y)<Math.abs(x)/3) carac = "-";
                        if (Math.abs(x)>=0 && Math.abs(x)<=Math.abs(y)/2) carac = "|";
                        if (y>=x/3 && y<x*2 || y<=x/3 && y>x*2) carac = "/";
                        if (-y>=x/3 && -y<x*2 || -y<=x/3 && -y>x*2) carac = "\\";
                        if (x==0) carac = "|";
                        if (y==0) carac = "-";
                        if (x==y) carac = "/";
                        if (x==-y) carac = "\\";

                        if (rand < distance*10/yMax+i/(hauteur/2)) carac = ".";

                        if (x==0 && y==0) carac ="*";
                    }


                
                    System.out.print(carac);

                }
                System.out.println();
            }

        }   


    }
}
