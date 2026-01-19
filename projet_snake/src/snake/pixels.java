package snake;

import java.util.Arrays;

public class pixels {
    public static void main(String[] args) {
        String[][] tableau = new String[10][10];
        String[][] tabaff = new String[40][80];
        String[][] pomme = {{"","","","","","","",""},
                            {"","","\033[38;2;156;13;3m\033[48;2;156;13;3m","\033[38;2;156;13;3m\033[48;2;156;13;3m","\033[48;2;180;35;10m\033[38;2;156;13;3m","\033[38;2;156;13;3m\033[48;2;156;13;3m","",""},
                            {"","","\033[38;2;156;13;3m\033[48;2;156;13;3m","\033[38;2;156;13;3m\033[48;2;156;13;3m","\033[38;2;156;13;3m\033[48;2;156;13;3m","\033[38;2;156;13;3m\033[48;2;156;13;3m","",""},
                            {"","","","","","","",""}};

        for (String[] ligne : tableau) {
            Arrays.fill(ligne, " ");
        }
        tableau[3][5] = "pomme";


        for (int y=0; y<tabaff.length; y++) {
            for (int x = 0; x < tabaff[y].length; x++) {
                String machin = "▀";

                if (tableau[y/4][x/8].equals("pomme")) machin = pomme[y%4][x%8] + machin;

                if (y%8<4 && x%16<8 || y%8>3 && x%16>7) machin = "\033[38;2;47;138;40m\033[48;2;47;138;40m" + machin;
                else machin = "\033[38;2;34;112;28m\033[48;2;34;112;28m" + machin;

                tabaff[y][x] = machin;
            }
        }

        System.out.print("\033\143");
        for (String[] ligne : tabaff) {
            for (String caractere : ligne) {
                System.out.print(caractere);
            }
            System.out.println();
        }
    }
}
