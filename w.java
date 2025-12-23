import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.*;

public class w {
    public static void main(String[] args) {
        String[][] image;

        image = new String[15][120];

        for (int y=0;y<image.length;y++) {
            Arrays.fill(image[y], " ");
        }
        

        for (int y=9;y<image.length;y++) {
            for (int x=0; x<image[y].length; x++) {
                image[y][x] = "\033[40m \033[0m";
            }
        }


        for (String[] ligne : image) {
            for (String truc : ligne) {
                System.out.print(truc);
            }
            System.out.println();
        }

    }

}
