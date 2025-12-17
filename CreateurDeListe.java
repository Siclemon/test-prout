import java.util.Scanner;
public class CreateurDeListe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string;
        String resultat;
        String vraiResultat="";


        while (true) {

        string = sc.nextLine();
        resultat ="";

        //while (string.length()<15) string = " "+string+" ";


        for (int i=0; i<string.length(); i++) {
            if (string.charAt(i) == '"') resultat += "\"\\"+string.charAt(i)+"\",";
            else resultat += "\""+string.charAt(i)+"\",";

        }

        resultat = resultat.substring(0,resultat.length()-1);

        if (string.length()==16) resultat = resultat.substring(0,resultat.length()-4);

        System.out.println(resultat);
        System.out.println(string.length());

        vraiResultat = vraiResultat+"{"+resultat+"},";

        System.out.println(vraiResultat);
        }   
    }
}
