import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.ColorChooserUI;

import org.w3c.dom.css.RGBColor;

public class frame extends JFrame {
    int clics=0;
    public frame() {
        setTitle("a");
        setSize(500,300);
        setResizable(true);
        //setLayout(null);

        JButton bouton = new JButton("prout");
        JPanel panel = new JPanel();
        JTextArea txt = new JTextArea();
        JPanel panelTexte = new JPanel();

        // panel.setBounds(10,20,100,100);
        panel.setSize(300,50);
        panel.setBackground(Color.PINK);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panelTexte.setSize(200,100);
        panelTexte.setBackground(Color.ORANGE);
        //panelTexte.setLocation(100, 10);

        //bouton.setBounds(5,5,50,50);
        bouton.setBackground(Color.getHSBColor(105.3f,0.46f,0.53f));
        
        //txt.setBounds(50, 50, 150, 150);

        panel.add(bouton);
        panelTexte.add(txt);



        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                clics++;
                txt.setText(Integer.toString(clics));
            }
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(panelTexte);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        frame o = new frame();
    }
}
