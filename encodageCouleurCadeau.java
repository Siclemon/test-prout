import java.util.Scanner;

public class encodageCouleurCadeau {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] input = {{" ","_","_","_","_","_","_","_","o","O","_","_","_","_","_","_","_"},{"|"," "," "," "," "," "," "," ","|","|"," "," "," "," "," "," "," ","|"},{"|","~","~","~","~","~","~","~","|","|","~","~","~","~","~","~","~","|"},{"|","_","_","_","_","_","_","_","|","|","_","_","_","_","_","_","_","|"}};
        String[][] resultat = new String[input.length][input[2].length];
        String string = "";

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

                resultat[i][j] = sc.next();

            }

        }


        for (int i=0; i<resultat.length;i++) {
            string = string + "{";
            for (int j=0; j<resultat[i].length;j++){
                string = string + "\""+resultat[i][j]+"\",";
            }
            string = string.substring(0,string.length()-1);
            string = string + "},";
        }
        string = string.substring(0,string.length()-1);

        System.out.println(string);
        
        







    }
}
