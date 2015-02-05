package ca.yorku.cse2311.tab2pdf;

import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



import ca.yorku.cse2311.tab2pdf.MainController;



public class MainJFrame extends JFrame{
	
	private JLabel img;
	private JFileChooser fc;
	
	public MainJFrame(MainController controller) {
		super("Tab To PDF");
		
		img = new JLabel("");
		
		JButton file = new JButton("Choose File");
	    file.addActionListener(controller);
	    file.setActionCommand(MainController.FILE);
	    
	    JButton tab = new JButton("Convert from tabs to PDF");
	    tab.addActionListener(controller);
	    tab.setActionCommand(MainController.TAB2PDF);
		
	    this.setMinimumSize(new Dimension(500, 500));
	    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
	    this.add(file);
	    this.add(tab);
	    this.add(img);
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	  
	
	
}
