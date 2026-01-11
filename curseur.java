

public class curseur {
    public static void main(String[] args) {
        

        for (int a=0; a<256; a+=30) {

            System.out.print("\033\143");

            System.out.print("\033[?25l");


            for (int b =0; b<256 ; b+=6) {
                for (int c =0; c<256 ; c+=6 ) {

                    System.out.print("\033[38;2;"+a+";"+b+";"+c+";2m██"+"\033[0m");
                }
                System.out.println();
            }

            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
            }

        }
        

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }

        
        System.out.print("\033[s");
        System.out.print("\033[15;30H"+"AAA");
        System.out.print("\033[1;1H"+"bb");
        System.out.print("\033[u");
        System.out.print("\033[?25h");
        

        //while(true);
    }
}
