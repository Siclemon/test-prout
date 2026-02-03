import java.util.Random;
import java.util.Scanner;

public class ColorGuess {
    public static void main(String[] args) {
        int[] couleur;
        double result;


        couleur = newColor();
        display(couleur);
        result = compareAll(input(), couleur);
        System.out.printf("\nRésultat :   %.1f %%",result);
        System.out.print("\nRéponse :    "+toHex(couleur)+"\n");


    }

    private static void display(int[] color) {
        System.out.print("\033\143");
        for (int i = 0; i<9; i++) {
            System.out.println("\033[48;2;"+color[0]+";"+color[1]+";"+color[2]+"m                  "+"\033[m");
        }
        System.out.print("\n\n\033[4mCode hexadécimal :\033[m\n> #");
    }

    private static int[] newColor() {
        Random rng = new Random();
        return new int[]  {rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)};
    }

    private static String input() {
        Scanner sc = new Scanner(System.in);
        String ret = null;

        do {
            ret = sc.nextLine();
            if (ret.toLowerCase().trim().equals("stop"))
                System.exit(1);
        } while (ret.length()!=6);
        sc.close();
        return ret;
    }

    private static double compareAll(String input, int[] color) {
        double result = 0;
        for (int i=0; i<3; i++)
            result += compare(input.substring(i*2,i*2+2), color[i]);
        result = result/3*100;
        return result;
    }

    private static double compare(String input, int color) {
        int inp = Integer.parseInt(input, 16);
        return (256.0-Math.abs(color-inp))/256.0;
    }

    private static String toHex(int[] color) {
        String ret;
        ret = Integer.toHexString(color[0]) + Integer.toHexString(color[1]) + Integer.toHexString(color[2]);
        return ret;
    }
}
