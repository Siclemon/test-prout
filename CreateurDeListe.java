import java.util.Scanner;
public class CreateurDeListe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string;
        String resultat;


        while (true) {

        string = sc.nextLine();
        resultat ="";

        while (string.length()<15) string = " "+string+" ";


        for (int i=0; i<string.length(); i++) {
            resultat += "\""+string.charAt(i)+"\",";

        }

        resultat = resultat.substring(0,resultat.length()-1);

        System.out.println(resultat);
        System.out.println(string.length());
        }   
    }
}
