import java.util.Random;
import java.util.Scanner;

public class ColorGuess {
    public static void main(String[] args) {
        int[] couleur;


        couleur = newColor();
        display(couleur);


    }

    private static void display(int[] color) {
        System.out.print("\033\143");
        for (int i = 0; i<7; i++) {
            System.out.println("\033[48;2;"+color[0]+";"+color[1]+";"+color[2]+"m              ");
        }
        System.out.print("\n\nCode hexadécimal :\n> ");
    }

    private static int[] newColor() {
        Random rng = new Random();
        return new int[]  {rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)};
    }

    private static String input() {
        Scanner sc = new Scanner(System.in);
        String ret = null;

        while (ret.length()!=6)
            ret = sc.nextLine();

        sc.close();
        return ret;
    }

    private static double compare(String input, int[] color) {
        
    }
}
