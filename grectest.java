import java.nio.charset.Charset;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Random;
import java.util.Scanner;

public class grectest {
    public static void main(String[] args) {
        //System.out.println("Αα Ββ Γγ Δδ Εε Ζζ Ηη Θθ Ιι Κκ Λλ Μμ Νν Ξξ Οο Ππ Ρρ Σσς Ττ Υυ Φφ Χχ Ψψ Ωω ");
        
        Scanner sc = new Scanner(System.in);
        Random rng = new Random();
        int i;
        String input;
        Alphabeta lettre;

        while (true) {
            i = rng.nextInt(24);
            lettre = Alphabeta.values()[i];
            if (rng.nextBoolean())
                System.out.println(lettre.getMajuscule());
            else
                System.out.println(lettre.getMinuscule());
            input = sc.nextLine().trim();

            if (input.equalsIgnoreCase(lettre.getNom())) {
                System.out.println("oui");
            } else {
                System.out.println("non c'est " + lettre.getNom());
            }
            System.out.println();
        }

    }
}

enum Alphabeta {
    ALPHA("alpha",'Α','α'),
    BETA("bêta",'Β','β'),
    GAMMA("gamma",'Γ','γ'),
    DELTA("delta",'Δ','δ'),
    EPSILON("epsilon",'Ε','ε'),
    ZETA("zêta",'Ζ','ζ'),
    ETA("êta",'Η','η'),
    THETA("thêta",'Θ','θ'),
    IOTA("iota",'Ι','ι'),
    KAPPA("kappa",'Κ','κ'),
    LAMBDA("lambda",'Λ','λ'),
    MU("mu",'Μ','μ'),
    NU("nu",'Ν','ν'),
    XI("xi",'Ξ','ξ'),
    OMICRON("omicron",'Ο','ο'),
    PI("pi",'Π','π'),
    RHO("rhô",'Ρ','ρ'),
    SIGMA("sigma",'Σ','σ'),
    TAU("tau",'Τ','τ'),
    UPSILON("upsilon",'Υ','υ'),
    PHI("phi",'Φ','φ'),
    CHI("chi",'Χ','χ'),
    PSI("psi",'Ψ','ψ'),
    OMEGA("oméga",'Ω','ω');


    private String nom;
    private char majuscule;
    private char minuscule;

    private Alphabeta(String nom, char majuscule, char minuscule) {
        this.nom = nom;
        this.majuscule = majuscule;
        this.minuscule = minuscule;
    }

    public String getNom() {
        return nom;
    }

    public char getMajuscule() {
        return majuscule;
    }

    public char getMinuscule() {
        return minuscule;
    }
}
