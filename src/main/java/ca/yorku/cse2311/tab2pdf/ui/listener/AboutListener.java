package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AboutListener
 *
 * Opens the About Us Window
 *
 * @author Glib Sitiugin, Anton Sitkovets, Marco Cirillo
 * @since 2015-03-19
 */
 public class AboutListener extends JPanel implements ActionListener{

    private final MainJFrame window;

    JLabel jlbLabel1, jlbLabel2, jlbLabel3, jlbLabel4, jlbLabel5, jlbLabel6, jlbLabel7, jlbLabel8, jlbLabel9,
    jlbLabel10, jlbLabel11, jlbLabel12, jlbLabel13, jlbLabel14, jlbLabel15, jlbLabel16, jlbLabel17, jlbLabel18;

    /**
     * @param window    the window we are working with
     */
    public AboutListener (MainJFrame window) {

       super();

       this.window = window;

        ImageIcon deep = null;
        ImageIcon anton = null;
        ImageIcon marco = null;
       // ImageIcon  = null;
       // ImageIcon  = null;

        try {
            deep = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/Deep.jpg")));
            anton = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/anton.jpg")));
            marco = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/marco.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridLayout(6,3));
       // setLayout(new GridLayout(6, 3));
        // 3 rows, 1 column Panel having Grid Layout
        jlbLabel1 = new JLabel("    Deep Patel");
        jlbLabel2 = new JLabel(deep);
        jlbLabel3 = new JLabel("   deep_.09@hotmail.com");
        jlbLabel4 = new JLabel("    Marco Pietro Cirillo");
        jlbLabel5 = new JLabel(marco);
        jlbLabel6 = new JLabel("   cirillom@my.yorku.ca");
        jlbLabel7 = new JLabel("    Anton Sitkovets");
        jlbLabel8 = new JLabel(anton);
        jlbLabel9 = new JLabel("   antosi@my.yorku.ca");
        jlbLabel10 = new JLabel("   Brody Atto");
        jlbLabel11 = new JLabel("11");
        jlbLabel12 = new JLabel("   brodyatto@gmail.com");
        jlbLabel13 = new JLabel("   Varsha Raghav");
        jlbLabel14 = new JLabel("14");
        jlbLabel15 = new JLabel("   varsha_raghav@hotmail.com");
        jlbLabel16 = new JLabel("   Glib Sitiguin");
        jlbLabel17 = new JLabel("17");
        jlbLabel18 = new JLabel("   glib.sitiugin@gmail.com");
        add(jlbLabel1);
        add(jlbLabel2);
        add(jlbLabel3);
        add(jlbLabel4);
        add(jlbLabel5);
        add(jlbLabel6);
        add(jlbLabel7);
        add(jlbLabel8);
        add(jlbLabel9);
        add(jlbLabel10);
        add(jlbLabel11);
        add(jlbLabel12);
        add(jlbLabel13);
        add(jlbLabel14);
        add(jlbLabel15);
        add(jlbLabel16);
        add(jlbLabel17);
        add(jlbLabel18);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame helpFrame = new JFrame("About Us");
        helpFrame.setSize(500, 500);
      //  JLabel label = new JLabel("Will be implemented soon!", JLabel.CENTER);
        helpFrame.setContentPane(new AboutListener(window));
        helpFrame.pack();
     //   helpFrame.add(label);
        helpFrame.setVisible(true);
    }
}
