import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class Swingg extends JFrame {
    public static void main(String[] args) {
        JFrame fr = new JFrame("aaaa");
        fr.setSize(400, 300);
        fr.setResizable(true);
        //fr.setLayout(null);
        fr.setLocationRelativeTo(null);

        float[] couleur = Color.RGBtoHSB(255, 190, 200,null);
        float[] couleur2 = Color.RGBtoHSB(180, 180, 240,null);
        float[] couleur3 = Color.RGBtoHSB(180, 250, 200,null);
        Color c3 = Color.getHSBColor(couleur3[0],couleur3[1],couleur3[2]);
        fr.getContentPane().setBackground(Color.getHSBColor(couleur[0],couleur[1],couleur[2]));

        JButton bout = new JButton("BOUTON");
        bout.setBounds(100, 50, 90, 30);
        bout.setBackground(Color.getHSBColor(couleur2[0],couleur2[1],couleur2[2]));
        fr.add(bout);

        ImageIcon icone = new ImageIcon("im.jpg");
        JLabel image = new JLabel(icone);
        image.setBounds(100, 100, 120, 120);
        fr.getContentPane().add(image);

        JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER,10,25));
        pan.setBackground(c3);
        pan.add(new JButton("aaa"));
        ImageIcon ico = new ImageIcon("Sans titre.png");
        JLabel ima = new JLabel(ico);
        ima.setSize(50, 50);
        //pan.add(ima);

        JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,2));
        pan2.setBackground(c3);
        JLabel txt = new JLabel("TEXTE");
        txt.setBackground(Color.WHITE);
        txt.setText("cacturne");
        pan2.add(txt);
        fr.add(pan2,BorderLayout.WEST);


        fr.add(pan,BorderLayout.EAST);

        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}
