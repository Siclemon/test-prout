import java.util.Arrays;
import java.util.Random;

public class w {
    public static void main(String[] args) {
        String[][] image;
        Random rng = new Random();
        int xRand, yRand;

        image = new String[40][80];

        for (int y=0;y<image.length;y++) {
            Arrays.fill(image[y], " ");
        }
        
        yRand = 4*rng.nextInt(1,11)-2;
        xRand = 8*rng.nextInt(1,11)-4;

        String rouge ="\033[31m";
        image[yRand][xRand] = rouge + "/";
        image[yRand][xRand-1] = rouge + "\\";
        image[yRand-1][xRand] = rouge + "\\";
        image[yRand-1][xRand-1] = rouge + "/";

        for (int y=0;y<image.length;y++) {
            for (int x=0; x<image[y].length; x++) {
                // if (y%2==0) image[y][x] = "-";
                // if (x%4==0) image[y][x] = "|";
                if (y%8<4 && x%16<8 || y%8>3 && x%16>7) image[y][x] = "\033[42m"+image[y][x];
                else image[y][x] = "\033[32,2,7m"+image[y][x];
            }
        }

        System.out.println("\033\143");
        for (String[] ligne : image) {
            for (String truc : ligne) {
                System.out.print(truc+"\033[0m");
            }
            System.out.println();
        }

        //arraylist pour les positions des segments du corps du serpent

    }

}
