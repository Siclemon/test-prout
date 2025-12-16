import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;

public class CompteurPause {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalTime mtn = LocalTime.now();
        DateTimeFormatter hms = DateTimeFormatter.ofPattern("HH:mm:ss");
        Duration duree;
        LocalTime pause = LocalTime.parse("17:00:00");
        int temps, secondes, minutes, heures;
        String texte;
        DayOfWeek jour = DayOfWeek.from(date);
        String jourr;
        String[] pauses = {"10:00:00", "13:00:00", "15:15:00", "17:00:00", "24:40:00"}; //supprimer la pause de test
        int i;
        String[][] zero = {{" "," "," "," ",".","n","~","~","%","x","."," "," "," "," "},{" "," ","x","8","8","X"," "," "," ","8","8","8","."," "," "},{" ","X","8","8","8","X"," "," "," ","8","8","8","8","L"," "},{"X","8","8","8","8","X"," "," "," ","8","8","8","8","8"," "},{"8","8","8","8","8","X"," "," "," ","8","8","8","8","8","X"},{"8","8","8","8","8","X"," "," "," ","8","8","8","8","8","X"},{"8","8","8","8","8","X"," "," "," ","8","8","8","8","8","f"},{"4","8","8","8","8","X"," "," "," ","8","8","8","8","8"," "},{" ","?","8","8","8","X"," "," "," ","8","8","8","8","\""," "},{" "," ","\"","8","8","X"," "," "," ","8","8","*","`"," "," "},{" "," "," "," ","^","\"","=","=","=","\"","`"," "," "," "," "}};
        String[][] un = {{" "," "," "," "," "," "," "," ","o","e"," "," "," "," "," "},{" "," "," "," "," "," ",".","@","8","8"," "," "," "," "," "},{" "," ","=","=","*","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," "," "," "," ","8","8","8","8","8"," "," "," "," "," "},{" "," ","'","*","*","%","%","%","%","%","%","*","*"," "," "}};
        String[][] deux = {{" "," "," ",".","-","-","~","*","t","e","u","."," "," "," "},{" "," ","d","F"," "," "," "," "," ","9","8","8","N","x"," "},{" ","d","8","8","8","b"," "," "," ","`","8","8","8","8",">"},{" ","?","8","8","8","8",">"," "," ","9","8","8","8","8","F"},{" "," ","\"","*","*","\""," "," ","x","8","8","8","8","8","~"},{" "," "," "," "," "," "," ","d","8","8","8","8","*","`"," "},{" "," "," "," "," ","z","8","*","*","\"","`"," "," "," ",":"},{" "," "," ",":","?",".",".",".",".","."," "," ",".",".","F"},{" "," ","<","\"","\"","8","8","8","8","8","8","8","8","8","~"},{" "," ","8",":"," "," ","\"","8","8","8","8","8","8","*"," "},{" "," ","\"","\""," "," "," "," ","\"","*","*","\"","`"," "," "}};
        String stringHeures, stringMinutes, stringSecondes, stringTemps;
        String[][] tableau = new String[27][156];
        int indiceChiffre=0;
        int[] xPosChiffre = {15,32,62,79,109,126};


        //quelles sont les pauses du jour
        jourr =jour.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("fr-FR"));
        if (jourr=="lundi" || jourr=="mardi") pauses[0] = "10:30:00";

        //quelle est la prochaine pause
        for (i=0; !mtn.isBefore(LocalTime.parse(pauses[i])); i++);
        pause = LocalTime.parse(pauses[i]);

        HashMap<Character, String[][]> chiffres = new HashMap<>();
        chiffres.put('0', zero);
        chiffres.put('1', un);
        chiffres.put('2', deux);
        // chiffres.put('3', trois);
        // chiffres.put('4', quatre);
        // chiffres.put('5', cinq);
        // chiffres.put('6', six);
        // chiffres.put('7', sept);
        // chiffres.put('8', huit);
        // chiffres.put('9', neuf);




  
        for (i=0;i<500;i++){

            mtn = LocalTime.now();

            duree = Duration.between(mtn, pause);
            temps = (int)duree.getSeconds();
            heures = temps/3600;
            minutes = (temps-heures*3600)/60;
            secondes = temps%60;

            


            stringHeures = String.format("%02d",heures);
            stringMinutes = String.format("%02d",minutes);
            stringSecondes = String.format("%02d",secondes);
            stringTemps = stringHeures;



            System.out.println(stringHeures+stringMinutes+stringSecondes);

            
            for (int y=0; y<27;y++) {
                for (int x=0; x<156; x++){
                    tableau[y][x]=" ";
                    if (x==0 || x==155 || y==0 || y==26) tableau[y][x] = "X";

                    for (indiceChiffre=0; indiceChiffre<=1; indiceChiffre++) {
                        if (y>7 && y<19 && x>xPosChiffre[indiceChiffre] && x<xPosChiffre[indiceChiffre]+15)
                        tableau[y][x] = chiffres.get(stringTemps.charAt(indiceChiffre))[y-8][x-xPosChiffre[indiceChiffre]];
                    }
                }
                System.out.println();
            }





            System.out.println("\033\143");
            for (int y=0; y<27;y++) {
                for (int x=0; x<156; x++){
                    System.out.print(tableau[y][x]);
                }
                System.out.println();
            }



            // texte = heures+"Il reste "+ minutes+"min et "+secondes+"s.";
            // System.out.println(texte);



            try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
        }
    }
}
