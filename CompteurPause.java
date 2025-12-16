import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.*;

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
        String[] pauses = {"10:00:00", "13:00:00", "15:15:00", "17:00:00"};
        int i;


        //quelles sont les pauses du jour
        jourr =jour.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("fr-FR"));
        if (jourr=="lundi" || jourr=="mardi") pauses[0] = "10:30:00";

        //quelle est la prochaine pause
        for (i=0; !mtn.isBefore(LocalTime.parse(pauses[i])); i++);
        pause = LocalTime.parse(pauses[i]);


  
        for (i=0;i<500;i++){

            mtn = LocalTime.now();

            duree = Duration.between(mtn, pause);
            temps = (int)duree.getSeconds();
            heures = temps/3600;
            minutes = (temps-heures*3600)/60;
            secondes = temps%60;

            HashMap<String, String[][]> chiffres = new HashMap<>();
            chiffres.put("0", zero);



            texte = heures+"Il reste "+ minutes+"min et "+secondes+"s.";

            System.out.println(texte);



            try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
        }
    }
}
