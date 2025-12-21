import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

//import intro.boole;

public class CadeauxTests {
    public static void main(String[] args) {
        Random rng = new Random();
        int rand;
        String[][][] cadeaux = {{{" ","_","_","O","_","_"," "},{"|"," "," ","|"," "," ","|"},{"|","=","=","|","=","=","|"},{"|","_","_","|","_","_","|"}},{{" ","_","_","_","_","_","_","_","o","O","_","_","_","_","_","_","_"},{"|"," "," "," "," "," "," "," ","|","|"," "," "," "," "," "," "," ","|"},{"|","~","~","~","~","~","~","~","|","|","~","~","~","~","~","~","~","|"},{"|","_","_","_","_","_","_","_","|","|","_","_","_","_","_","_","_","|"}},{{" "," "," ","_","c","O","_"," "," "},{" "," ","|","_","_","_","_","|"," "},{"_","_","|","_","_","_","_","|","_"},{"|"," "," "," ","|"," "," "," ","|"},{"|"," "," "," ","|"," "," "," ","|"},{"|","_","_","_","|","_","_","_","|"}}};
        String[][][] codesCouleurs = {{{"0","1","1","2","1","1","0"},{"1","0","0","2","0","0","1"},{"1","2","2","2","2","2","1"},{"1","1","1","2","1","1","1"}},{{"0","1","1","1","1","1","1","1","2","2","1","1","1","1","1","1","1"},{"1","0","0","0","0","0","0","0","2","2","0","0","0","0","0","0","0","1"},{"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","1"},{"1","1","1","1","1","1","1","1","2","2","1","1","1","1","1","1","1","1"}},{{"0","0","0","1","3","3","1","0","0"},{"0","0","1","3","3","3","3","1","0"},{"2","2","1","2","2","2","2","1","2"},{"2","0","0","0","3","0","0","0","2"},{"2","0","0","0","3","0","0","0","2"},{"2","2","2","2","3","2","2","2","2"}}};
        int[] nbCouleurs = {2,2,3};
        ArrayList<String> couleurs = new ArrayList<String>();
        String[] listeCouleurs = {"rouge","jaune","blanc","bleu","cyan","violet","vert"};
        String caca;
        ArrayList<Integer> indexCouleurs = new ArrayList<Integer>();
        boolean dejaLa;



            HashMap<String, String> idCouleurs = new HashMap<>();
            idCouleurs.put("noir","\033[1;30m");
            idCouleurs.put("rouge","\033[1;91m");
            idCouleurs.put("vert" , "\033[1;32m");
            idCouleurs.put("jaune" ,"\033[1;93m");
            idCouleurs.put("bleu", "\033[1;94m");
            idCouleurs.put("violet", "\033[1;95m");
            idCouleurs.put("cyan", "\033[1;96m");
            idCouleurs.put("blanc", "\033[1;97m");
            idCouleurs.put("","");




        for (int i=0; i<cadeaux.length;i++){

            couleurs.clear();
            couleurs.add("");
            while (couleurs.size()<nbCouleurs[i]+1) {
                caca = listeCouleurs[rng.nextInt(listeCouleurs.length)];
                dejaLa = false;
                for (int z=0; z<couleurs.size(); z++){
                    if (caca==couleurs.get(z)) dejaLa = true;
                }
                if (!dejaLa) couleurs.add(caca); 
            }

            for (String a :couleurs) System.out.println(a);


            for (int j=0; j<codesCouleurs[i].length;j++){
                for (int k=0; k<codesCouleurs[i][j].length; k++){
                    codesCouleurs[i][j][k] = couleurs.get(Integer.parseInt(codesCouleurs[i][j][k]));

                }
            }
            

            for (int j=0; j<cadeaux[i].length;j++){
                for (int k=0; k<cadeaux[i][j].length; k++){
                System.out.print(idCouleurs.get(codesCouleurs[i][j][k])+cadeaux[i][j][k]+"\033[0m");

                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
