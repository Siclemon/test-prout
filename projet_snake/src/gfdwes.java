import java.util.Random;

public class gfdwes {
    public static void main(String[] args) {
        Random rng = new Random();
        double[] tab = new double[24];

        rng.nextDouble();

        for (int i = 0; i < tab.length; i++) {
            tab[i] = rng.nextDouble(1000,1001);
        }


        for (double d : tab) {
            System.out.println(d);
        }
    }
}
