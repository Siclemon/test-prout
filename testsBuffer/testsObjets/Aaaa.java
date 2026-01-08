package testsBuffer.testsObjets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class Aaaa {
    static Future<String> texte;
    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        //Future<String> texte;
        String pikachu=null;
        int timer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("cb de tps? ");
        timer = Integer.parseInt(br.readLine())*1000;

        texte = exec.submit(new Cccc());

        Thread temps = new Thread() {
            public void run() {
                try {
                    Thread.sleep(timer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                texte.cancel(true);
            }
        };
        temps.start();
        
        
        
        try {
            pikachu = texte.get();
        } catch (Exception e) {
            System.out.println("pas d'input");
        }

        System.out.println("texte : " + pikachu);
        //System.exit(0);
            
        
    }

}