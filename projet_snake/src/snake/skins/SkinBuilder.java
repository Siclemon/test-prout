package snake.skins;

import java.util.HashMap;

import java.util.Scanner;

public class SkinBuilder {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String couleur;
        String[][] skin = new String[4][8];
        String[][] temp = new String[4][8];
        int nbCouleurs;
        HashMap<Integer, String> couleurs = new HashMap<>();
        couleurs.put(0, "47;138;40m");

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = "";
                skin[i][j] = "";
            }
        }

        System.out.print("\033\143");
        String fichier = fichier();

        do {
            System.out.print("\033\143");
            System.out.println("Combien de couleurs ?");
            nbCouleurs = sc.nextInt();
            sc.nextLine();
            for (int i = 1; i <= nbCouleurs; i++) {
                couleurs.put(i, couleur(i));
            }
            System.out.println("couleurs ok ?");
        }while (!sc.nextLine().toLowerCase().matches("o|oui|ok|y|yes|1"));


        System.out.print("\033\143");
        //grille grise
        // for (int y = 0; y < 4; y++) {
        //     for (int x = 0; x < 8; x++) {
        //         if (x % 2 == 0)
        //             System.out.print("\033[38;2;120;120;120m\033[48;2;150;150;150m" + "\033[" + (y + 1) + ";" + (x + 1) + "H▀" + "\033[m");
        //         else
        //             System.out.print("\033[48;2;120;120;120m\033[38;2;150;150;150m" + "\033[" + (y + 1) + ";" + (x + 1) + "H▀" + "\033[m");
        //     }
        // }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x+y)%2==0) 
                    temp[y/2][x] = "\033[" + (38 + devantOuDerriere(y)) + ";2;120;120;120m";
                else temp[y/2][x] = "\033[" + (38 + devantOuDerriere(y)) + ";2;150;150;150m";
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");

            }
        }
        sc.nextInt();
        afficherCouleurs(couleurs, nbCouleurs);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                temp[y/2][x] = temp[y/2][x] + skin[y/2][x] +"\033[" + (38 + devantOuDerriere(y)) + ";2;255;255;0m";
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");
                System.out.print("\033[6;1H");
                skin[y/2][x] = "\033[" + (38 + devantOuDerriere(y)) + ";2;" + couleurs.get(sc.nextInt()) + skin[y/2][x];
                temp[y/2][x] += skin[y/2][x];
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");
            }
        }

        sc.close();
    }

    static String fichier() {
        System.out.println("Quel objet voulez-vous dessiner ?");
        System.out.println("\t1 - Tête du serpent\n\t2 - Corps du serpent\n\t3 - Pomme");

        while (true)
            switch (sc.nextInt()) {
                case 1:
                    return "src/snake/skins/head.json";
                case 2:
                    return "src/snake/skins/body.json";
                case 3:
                    return "src/snake/skins/fruit.json";
        }
    }

    static int devantOuDerriere(int y) {
        if (y % 2 == 0)
            return 0;
        else
            return 10;
    }

    static String couleur(int indice) {
        String[] rgb;
        boolean ok;
        do {
            ok = false;
            System.out.print("Couleur "+indice+" : ");
            rgb = sc.nextLine().trim().split(" ");

            try {

                for (String elem : rgb) {
                    if (Integer.parseInt(elem) < 0 || Integer.parseInt(elem) > 255) {
                        System.out.println("entre 0 et 255 stp");
                        ok = false;
                        break;
                    } else
                        ok = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("des chiffres stp");
            }

            if (rgb.length!=3) System.out.println("3 couleurs stp");

        } while (!ok || rgb.length!=3);

        String result = rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
        System.out.println("\033[48;2;"+result+"     "+"\033[m");
        return result;
    }

    static void afficherCouleurs(HashMap couleurs, int nb) {
        for (int i = 1; i <= nb; i++) {
            System.out.print("\033[" + i + ";10H" + "\033[48;2;" + couleurs.get(i) + "  " + "\033[m\033[38;2;" + couleurs.get(i) + "-Couleur " + i + "\033[ù");
        }
    }
}
