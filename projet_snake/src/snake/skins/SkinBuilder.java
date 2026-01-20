package snake.skins;

public class SkinBuilder {
    public static void main(String[] args) {
        
        for (int y=0 ;y<8; y++) {
            for (int x=0; x<8; x++) {
                System.out.print("\033["+(y+1)+";"+(x*2+1)+"H██");
            }
        }
    }
}
