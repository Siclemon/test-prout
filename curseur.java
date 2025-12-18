public class curseur {
    public static void main(String[] args) {
        
        for (int i=0; i<10; i++) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        }

        System.out.print("\u001B[A".repeat(3)+"\u001B[C".repeat(3)+"o");
    }
}
