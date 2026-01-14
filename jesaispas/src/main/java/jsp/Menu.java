package jsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    String reset = "\033[0m";

    public Menu() {
    }

    public String menu(String pseudo) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.print("\033\143");
        afficherTitre();
        afficherTruc(pseudo);


        while(true) {
            input=null;
            System.out.print("\033[16;33H");
            while (input == null) {
                try {
                    input = br.readLine();
                    switch (input.charAt(0)) {
                        case 'j' : 
                            return "jouer";

                        case 'q' : 
                            return "quitter";

                        case 'd' : 
                            return "deco";

                        // case 'o' : 
                        //     Menu.options();
            }
                } catch (IOException e) {}
                catch (StringIndexOutOfBoundsException e) {}
            }
            
        }

    }

    public static void options() {

    }

    public void afficherTruc(String nom) {
        System.out.print("\033[12;30H\033[1;4mJ"+"\033[1;24mOUER"+reset);
        System.out.print("\033[13;30H\033[1;4mO"+"\033[1;24mPTIONS"+reset);
        System.out.print("\033[14;30H\033[1;4mQ"+"\033[1;24mUITTER"+reset);
        System.out.print("\033[9;2H\033[3m"+nom+reset);
        System.out.print("\033[10;2H\033[3mse \033[4mD\033[24méconnecter"+reset);
    }

    public void afficherTitre() {
        System.out.print("\033[38;2;34;112;28m"+"\033[1;2H"+"  █████████  ██████   █████   █████████   █████   ████ ██████████");
        System.out.print("\033[2;2H"+" ███░░░░░███░░██████ ░░███   ███░░░░░███ ░░███   ███░ ░░███░░░░░█");
        System.out.print("\033[3;2H"+"░███    ░░░  ░███░███ ░███  ░███    ░███  ░███  ███    ░███  █ ░ ");
        System.out.print("\033[4;2H"+"░░█████████  ░███░░███░███  ░███████████  ░███████     ░██████   ");
        System.out.print("\033[5;2H"+" ░░░░░░░░███ ░███ ░░██████  ░███░░░░░███  ░███░░███    ░███░░█   ");
        System.out.print("\033[6;2H"+" ███    ░███ ░███  ░░█████  ░███    ░███  ░███ ░░███   ░███ ░   █");
        System.out.print("\033[7;2H"+"░░█████████  █████  ░░█████ █████   █████ █████ ░░████ ██████████");
        System.out.print("\033[8;2H"+" ░░░░░░░░░  ░░░░░    ░░░░░ ░░░░░   ░░░░░ ░░░░░   ░░░░ ░░░░░░░░░░ "+reset);
    }
}
