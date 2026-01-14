package snake;

public class Display {
    static String reset = "\033[0m";

    
    public static void afficherTitre(int y, int x) {
        String couleur = "\033[38;2;34;112;28m";
        System.out.print("\033[" + y++ + ";" + x + "H" + couleur +"  █████████  ██████   █████   █████████   █████   ████ ██████████");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ███░░░░░███░░██████ ░░███   ███░░░░░███ ░░███   ███░ ░░███░░░░░█");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░███    ░░░  ░███░███ ░███  ░███    ░███  ░███  ███    ░███  █ ░ ");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░░█████████  ░███░░███░███  ░███████████  ░███████     ░██████   ");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ░░░░░░░░███ ░███ ░░██████  ░███░░░░░███  ░███░░███    ░███░░█   ");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ███    ░███ ░███  ░░█████  ░███    ░███  ░███ ░░███   ░███ ░   █");
        System.out.print("\033[" + y++ + ";" + x + "H" + "░░█████████  █████  ░░█████ █████   █████ █████ ░░████ ██████████");
        System.out.print("\033[" + y++ + ";" + x + "H" + " ░░░░░░░░░  ░░░░░    ░░░░░ ░░░░░   ░░░░░ ░░░░░   ░░░░ ░░░░░░░░░░ "+reset);
    }

    public static void texteMenu(String nom) {
        System.out.print("\033[12;30H\033[1;4mJ"+"\033[1;24mOUER"+reset);
        System.out.print("\033[13;30H\033[1;4mO"+"\033[1;24mPTIONS"+reset);
        System.out.print("\033[14;30H\033[1;4mQ"+"\033[1;24mUITTER"+reset);
        System.out.print("\033[9;2H\033[3m"+nom+reset);
        System.out.print("\033[10;2H\033[3mse \033[4mD\033[24méconnecter"+reset);
    }

    public static void texteLogin() {
        System.out.print("\033[2;67H\033[3;38;2;250;250;120mby: siclemon"+reset);
        System.out.print("\033[14;37Hpseudo ?");
        System.out.print("\033[15;37H>");
    }

    public static void cadre(int y, int x, int hauteur, int largeur) {
    //cadre
        for (int i = y; i<hauteur+y; i++) {
            for (int j = x; j<largeur+x; j++) {
                if (j<=2 || j >= largeur-1 || i==1 || i==hauteur) System.out.print("\033["+i+";"+j+"H\033[48;2;100;250;100m "+reset);
            }
        }
    }
}
