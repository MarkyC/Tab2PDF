package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
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

    JLabel jlbLabel2, jlbLabel3, jlbLabel5, jlbLabel6, jlbLabel8, jlbLabel9, jlbLabel11, jlbLabel12, jlbLabel14,
            jlbLabel15, jlbLabel17, jlbLabel18;

    /**
     * @param window    the window we are working with
     */
    public AboutListener (MainJFrame window) {

       super();

       this.window = window;

        ImageIcon deep = null;
        ImageIcon anton = null;
        ImageIcon marco = null;
        ImageIcon varsha = null;
       // ImageIcon  = null;
       // ImageIcon  = null;

        try {
            deep = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/Deep.jpg")));
            anton = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/anton.jpg")));
            marco = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/marco.jpeg")));
            varsha = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/Varsha.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridLayout(3,4));
       // setLayout(new GridLayout(6, 3));
        // 3 rows, 1 column Panel having Grid Layout
        jlbLabel2 = new JLabel(deep);
        jlbLabel2.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel3 = new JLabel("<html>&nbsp&nbsp&nbsp deep_.09@hotmail.com<br>&nbsp&nbsp&nbsp Deep Patel </html>");
        jlbLabel5 = new JLabel(marco);
        jlbLabel5.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel6 = new JLabel("<html>&nbsp&nbsp&nbsp cirillom@my.yorku.ca <br>&nbsp&nbsp&nbsp Marco Pietro Cirillo </html>");
        jlbLabel8 = new JLabel(anton);
        jlbLabel8.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel9 = new JLabel("<html>&nbsp&nbsp&nbsp antosi@my.yorku.ca<br>&nbsp&nbsp&nbsp Anton Sitkovets </html>");
        jlbLabel11 = new JLabel("11");
        jlbLabel11.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel12 = new JLabel("<html>&nbsp&nbsp&nbsp brodyatto@gmail.com<br>&nbsp&nbsp&nbsp Brody Atto <html>");
        jlbLabel14 = new JLabel(varsha);
        jlbLabel14.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel15 = new JLabel("<html>&nbsp&nbsp&nbsp varsha_raghav@hotmail.com<br>&nbsp&nbsp&nbsp Varsha Raghav <html>");
        jlbLabel17 = new JLabel("17");
        jlbLabel17.setBorder(LineBorder.createGrayLineBorder());
        jlbLabel18 = new JLabel("<html>&nbsp&nbsp&nbsp glib.sitiugin@gmail.com<br>&nbsp&nbsp&nbsp Glib Sitiguin <html>");
        add(jlbLabel2);
        add(jlbLabel3);
        add(jlbLabel5);
        add(jlbLabel6);
        add(jlbLabel8);
        add(jlbLabel9);
        add(jlbLabel11);
        add(jlbLabel12);
        add(jlbLabel14);
        add(jlbLabel15);
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
