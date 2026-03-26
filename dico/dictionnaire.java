import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class dictionnaire {
	public static void main(String[] args) throws IOException {
		//try {
			BufferedReader br = new BufferedReader(
    new InputStreamReader(new FileInputStream("Classeur1.txt"),"ISO-8859-1"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("caca2.txt"));
			FileOutputStream oFileOutputStream = new FileOutputStream("caca3.txt");
			OutputStreamWriter writer = new OutputStreamWriter(oFileOutputStream, StandardCharsets.UTF_8);
			String line;
			//line = br.readLine();
			while ((line = br.readLine()) != null) {
				line = replaceChar(line);
				System.out.println(line);
				//bw.write(line);
				//bw.newLine();

				writer.write(line+"\n");
			}
			br.close();
			bw.close();
			writer.close();
		// } catch (IOException e) {
		// 	System.out.println("erreur");
		// }

	}

	
static String replaceChar(String chaine)
    {
        chaine = chaine.replace('â', 'a');
        chaine = chaine.replace('à', 'a');
        chaine = chaine.replace('ä', 'a');
        chaine = chaine.replace('é', 'e');
        chaine = chaine.replace('è', 'e');
        chaine = chaine.replace('ê', 'e');
        chaine = chaine.replace('ë', 'e');
        chaine = chaine.replace('î', 'i');
        chaine = chaine.replace('ï', 'i');
        chaine = chaine.replace('ô', 'o');
        chaine = chaine.replace('ö', 'o');
        chaine = chaine.replace('ù', 'u');
        chaine = chaine.replace('û', 'u');
        chaine = chaine.replace('ü', 'u');
        chaine = chaine.replace('ç', 'c');
        chaine = chaine.replace('ÿ', 'y');
        chaine = chaine.replace("-", "");
        chaine = chaine.replace("'","");
        chaine = chaine.replace(" ","");
        return chaine;
    }
 
}
