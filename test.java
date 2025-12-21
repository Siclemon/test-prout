
import java.util.ArrayList;


public class test {
    public static void main(String[] args) {
    //     int[][][] x = {{{1,2},{4}},{{4,9,4},{8}},{{7}}};
    //     int[][] y = {{4,9,4},{8}};
    //     String couleur;
    //     int indice=10;
    //     int i=0;

    //     for ( int[][] aaa : x){
    //         i++;
    //         if (Arrays.equals(aaa,y)) indice=i;
    //     }
    //     System.out.println(indice);
        
    

    // int a,b,c;

    // for (int i=0; i<300;i++) {
    //     a = i/4;
    //     a = a % 2 + a;
    //     a = a/2+1;

    //     b = (i/4+3)/2;

    //     c = (i/4+1)/2;

    //     System.out.println(c+"   "+b+"      "+(c==b-1));

    // }

    ArrayList<Integer> abc = new ArrayList<>();

    abc.add(3);
    abc.add(9);

    abc.remove(1);
    System.out.println(abc);


    String[][] bibibi = {{"1","2"},{"a","b"}};
    String[][] bobo = {{"uuu"},{"a","a","a","a","a","a"}};
    String[][][] boi = {bibibi,bobo};

    ArrayList<String[][]> bababa = new ArrayList<>();

    bababa.add(boi[0]);
    bababa.add(boi[1]);

    for (int h=0; h<bababa.size();h++){
        for(String[] ligne : bababa.get(h))
        {
            for (String oui : ligne){
                System.out.print(oui);

            }
            System.out.println();

        }
    System.out.println();
    }
    

    }

}

