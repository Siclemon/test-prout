import java.util.Random;
import java.util.Scanner;


public class test {
    public static void main(String[] args) {
        String reset = "\033[0m";
        Random rng = new Random();
        int a,c,d;


        // System.out.println("\033[38;5;55mAAAAAAAAAAAAAA"+reset);
        // System.out.println("\033[38;5;56mAAAAAAAAAAAAAA"+reset);
        // System.out.println("\033[38;5;57mAAAAAAAAAAAAAA"+reset);

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 257; i++) {
                System.out.println("\033["+(38+j*10)+";5;"+i+"mAAAAAAAAAAAAAA"+reset);
            }
        }

        for (int r =0; r<256 ; r+=25) {
            for (int g =0; g<256 ; g+=25) {
                for (int b =0; b<256 ; b+=25) {
                    a= rng.nextInt(256);
                    c= rng.nextInt(256);
                    d= rng.nextInt(256);
                    System.out.print("\033[38;2;"+r+";"+g+";"+b+";4m██"+reset);
                }
                System.out.println();
            }
        }

}
}
