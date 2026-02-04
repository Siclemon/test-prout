import java.util.Random;
import java.util.Scanner;

public class ColorGuess {
    public static void main(String[] args) {
        int[] couleur;
        double result;
        int[] input;


        couleur = newColor();
        display(couleur);
        input = toRGB(input());
        result = compareAll(input, couleur);
        System.out.printf("\nRésultat :   %.1f %%",result);
        System.out.print("\nRéponse :    "+toHex(couleur)+"\n");
        userColor(input);


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

    private static int[] toRGB(String input) {
        int[] ret = new int[3];
        for (int i=0; i<3; i++)
            ret[i] = Integer.parseInt(input.substring(i*2,i*2+2),16);
        return ret;
    }

    private static double compareAll(int[] input, int[] color) {
        double result = 0;
        for (int i=0; i<3; i++)
            result += compare(input[i], color[i]);
        result = result/3*100;
        return result;
    }

    private static double compare(int input, int color) {
        //int inp = Integer.parseInt(input, 16);
        return (256.0-Math.abs(color-input))/256.0;
    }

    private static String toHex(int[] color) {
        String ret="";
        for (int i=0; i < 3; i++) {
            ret += addZero(Integer.toHexString(color[i]));
        }
        return ret;
    }

    private static String addZero(String str) {
        if (str.length()==1)
            return "0"+str;
        else 
            return str;
    }

    private static void userColor(int[] color) {
        System.out.print("\033[s");
        for (int i = 0; i<9; i++) {
            System.out.print("\033["+(1+i)+";20H"+"\033[48;2;"+color[0]+";"+color[1]+";"+color[2]+"m                  "+"\033[m");
        }
        System.out.print("\033[u");
    }
}
