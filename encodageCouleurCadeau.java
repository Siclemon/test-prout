import java.util.Scanner;

public class encodageCouleurCadeau {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] input = {{" ","_","_","_","_","_","_","_","_","_","_"," "},{"|"," "," ","/"," "," "," ","/"," "," "," ","|"},{"|"," ","/"," "," "," ","/"," "," "," ","/","|"},{"|","/","_","_","_","/","_","_","_","/","_","|"}};
        String[][] resultat;
        String string = "";
        int largeur=0;

        //quelle est la largeur max du cadeau ?
        for (int i=0; i<input.length;i++) {
            if (input[i].length > largeur) largeur = input[i].length;
        }

        resultat = new String[input.length][largeur];

        
        //remplissage temporaire du tableau à remplir
        for (int i=0; i<resultat.length;i++) {
            for (int j=0; j<resultat[i].length;j++){
                resultat[i][j] = ".";
            }
        }


        for (int i=0; i<input.length;i++) {
            for (int j=0; j<input[i].length;j++){

                System.out.println("\033\143");

                //quel caractère à colorer ?
                for (int k=0; k<input.length;k++) {
                    for (int l=0; l<input[k].length;l++){
                        if (k==i && l==j) System.out.print("\033[41m"+input[k][l]+"\033[0m");
                        else System.out.print(input[k][l]);
                    }      
                    System.out.println();
                }

                System.out.println();

                //rappel de l'avancée
                for (int k=0; k<resultat.length;k++) {
                    for (int l=0; l<resultat[k].length;l++){
                        System.out.print(resultat[k][l]);
                    }
                    System.out.println();
                }

                resultat[i][j] = String.valueOf(sc.next().charAt(0));

            }

        }




        //afichage en liste
        string = string + "{";
        for (int i=0; i<resultat.length;i++) {
            string = string + "{";
            for (int j=0; j<resultat[i].length;j++){
                if (!resultat[i][j].equals(".")) //retirer les points
                    string = string + "\""+resultat[i][j]+"\",";
            }
            string = string.substring(0,string.length()-1);
            string = string + "},";
        }
        string = string.substring(0,string.length()-1);
        string = string + "}";

        System.out.println(string);
        
        sc.close();

    }
}
