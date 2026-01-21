package snake.skins;

import java.util.HashMap;
import java.util.Scanner;

import snake.Skins;

public class SkinBuilder {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Skins skins = new Skins();
        String[][] skin = new String[4][8];
        String[][] temp = new String[4][8];
        int nbCouleurs;
        HashMap<Integer, String> couleurs = new HashMap<>();
        String couleurFond = "47;138;40m";
        couleurs.put(0, "47;138;40m");

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = "";
                skin[i][j] = "";
            }
        }

        System.out.print("\033\143");
        String quoi = fichier();

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

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x+y)%2==0) 
                    temp[y/2][x] = "\033[" + devantOuDerriere(y) + ";2;120;120;120m";
                else temp[y/2][x] = "\033[" + devantOuDerriere(y) + ";2;150;150;150m";
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");

            }
        }

        afficherCouleurs(couleurs, nbCouleurs);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                temp[y/2][x] = temp[y/2][x] + skin[y/2][x] +"\033[" + devantOuDerriere(y) + ";2;255;255;0m";
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");
                System.out.print("\033[6;1H");
                int choix = sc.nextInt();
                if (choix != 0)
                    skin[y/2][x] = "\033[" + devantOuDerriere(y) + ";2;" + couleurs.get(choix) + skin[y/2][x];
                else temp[y/2][x] = temp[y/2][x] + "\033[" + devantOuDerriere(y) + ";2;" + couleurFond;
                temp[y/2][x] += skin[y/2][x];
                System.out.print(temp[y/2][x] + "\033[" + (y/2 + 1) + ";" + (x + 1) + "H▀" + "\033[m");
            }
        }
        sc.nextLine();
        try { Thread.sleep(1500);}
        catch (InterruptedException e){}

        String clrAnim = couleur();
        
        System.out.println("Quel nom ?");
        String nom = sc.nextLine();

        skins.newSkin(quoi, nom, skin);
        skins.newSkinAnimation(quoi, nom, clrAnim);

        sc.close();
    }

    static String fichier() {
        System.out.println("Quel objet voulez-vous dessiner ?");
        System.out.println("\t1 - Tête du serpent\n\t2 - Corps du serpent\n\t3 - Pomme");

        while (true)
            switch (sc.nextInt()) {
                case 1:
                    return "head";
                case 2:
                    return "body";
                case 3:
                    return "fruit";
        }
    }

    static int devantOuDerriere(int y) {
        if (y % 2 == 0)
            return 38;
        else
            return 48;
    }

    static String couleur(int indice) {
        String[] rgb;
        boolean ok;
        do {
            ok = false;
            System.out.print("Couleur "+indice+" : ");
            rgb = sc.nextLine().trim().replace(",","").replace(";", " ").split(" ");

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

    //pour le fond
    static String couleur() {
        String[] rgb;
        boolean ok;
        do {
            ok = false;
            System.out.print("\n\nCouleur pour l'animation : ");
            rgb = sc.nextLine().trim().replace(",","").replace(";", " ").split(" ");

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

        String result = "\033[38;2;" + rgb[0] + ";" + rgb[1] + ";" + rgb[2] + "m";
        System.out.println("\033[48;2;"+result+"     "+"\033[m");
        return result;
    }

    static void afficherCouleurs(HashMap<Integer,String> couleurs, int nb) {
        for (int i = 1; i <= nb; i++) {
            String[] rgb = couleurs.get(i).substring(0, couleurs.get(i).length()-1).split(";");
            String fondClair = "";
            if (Integer.parseInt(rgb[0]) + Integer.parseInt(rgb[1]) + Integer.parseInt(rgb[2]) < 100)
                fondClair = "\033[48;2;245;245;230m";
            System.out.print("\033[" + i + ";10H" + "\033[48;2;" + couleurs.get(i) + "  " + "\033[m\033[38;2;" +  couleurs.get(i) +fondClair + "-Couleur " + i + "  (" + "\033[49m\033[38;2;220;0;0m" + rgb[0] + "\033[38;2;0;220;0m " + rgb[1] + "\033[38;2;0;0;220m " + rgb[2] + "\033[m\033[38;2;" +  couleurs.get(i) + ")\033[m");
        }
    }
}
