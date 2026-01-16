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

    public static void texteMenu(int y, int x, String nom) {
        System.out.print("\033[11;9H\033[3m"+nom+reset);
        System.out.print("\033[12;9H\033[3mse \033[4mD\033[24méconnecter"+reset);
        System.out.print("\033["+y+++";"+x+"H\033[1;4mJ"+"\033[1;24mOUER"+reset);
        System.out.print("\033["+y+++";"+x+"H\033[1;4mO"+"\033[1;24mPTIONS"+reset);
        System.out.print("\033["+y+++";"+x+"H\033[1;4mQ"+"\033[1;24mUITTER"+reset);
        System.out.print("\033["+(y+1)+";"+x+"H>");
    }

    public static void texteLogin(int y, int x) {
        System.out.print("\033[11;62H\033[3;38;2;250;250;120mby siclemon"+reset);
        System.out.print("\033["+y+";"+x+"HPseudo :");
        System.out.print("\033["+(y+1)+";"+x+"H>");
    }

    public static void cadre(int y, int x, int hauteur, int largeur, String couleur) {
        for (int i = y; i<hauteur+y; i++) {
            for (int j = x; j<largeur+x; j++) {
                if (j<=x+1 || j >= x+largeur-2 || i==y || i==y+hauteur-1) System.out.print("\033["+i+";"+j+"H"+couleur+" "+reset);
            }
        }
    }
}
