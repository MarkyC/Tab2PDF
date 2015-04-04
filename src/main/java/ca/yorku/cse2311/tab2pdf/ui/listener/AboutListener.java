package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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

    JLabel InfoLabel,brodyPicLabel, brodyInfoLabel, glibPicLabel, glibInfoLabel, picLabel;

    String deepName = "Deep Patel";
    String deepEmail = "deep0410@my.yorku.ca";
    String marcoName = "Marco Pietro Cirillo";
    String marcoEmail = "cirillom@my.yorku.ca";
    String antonName = "Anton Sitkovets";
    String antonEmail = "antosi@my.yorku.ca";
    String brodyName = "Brody Atto";
    String bordyEmail = "brodyatto@gmail.com";
    String varshaName = "Varsha Raghav";
    String varshaEmail = "varsha_raghav@hotmail.com";
    String glibName = "Glib Sitiguin";
    String glibEmail = "situgin@yorku.ca";

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
        ImageIcon  glib = null;
       // ImageIcon  = null;

        try {
            deep = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/Deep.jpg")));
            anton = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/anton.jpg")));
            marco = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/marco.jpeg")));
            varsha = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/Varsha.jpg")));
            glib = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("toolbar/glib.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridLayout(3,4));
       // setLayout(new GridLayout(6, 3));
        // 3 rows, 1 column Panel having Grid Layout

        createRow(deep, deepName, deepEmail);
        createRow(marco, marcoName, marcoEmail);
        createRow(anton, antonName, antonEmail);
        brodyPicLabel = new JLabel("11");
        brodyPicLabel.setBorder(LineBorder.createGrayLineBorder());
        brodyInfoLabel = new JLabel("<html>&nbsp&nbsp&nbsp brodyatto@gmail.com<br>&nbsp&nbsp&nbsp Brody Atto <html>");
        add(brodyPicLabel);
        add(brodyInfoLabel);
 //     createRow(brody, brodyName, brodyEmail);
        createRow(varsha, varshaName, varshaEmail);
        createRow(glib, glibName, glibEmail);
    }


    public void createRow(ImageIcon picture,String name, String email){
        picLabel = new JLabel(picture);
 //       picLabel.setBorder(LineBorder.createGrayLineBorder());
        InfoLabel = new JLabel("<html>&nbsp&nbsp&nbsp "+name+"<br>&nbsp&nbsp&nbsp "+email+" </html>");
        add(picLabel);
        add(InfoLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame helpFrame = new JFrame("About Us");
        helpFrame.setSize(500, 500);
        helpFrame.setContentPane(new AboutListener(window));
        helpFrame.pack();
        helpFrame.setVisible(true);
    }
}
