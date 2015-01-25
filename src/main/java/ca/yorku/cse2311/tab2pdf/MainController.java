package ca.yorku.cse2311.tab2pdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;





import ca.yorku.cse2311.tab2pdf.Main;
import ca.yorku.cse2311.tab2pdf.MainJFrame;;


public class MainController implements ActionListener
{
	public static final String FILE = "FILE";
	public static final String TAB2PDF = "TAB2PDF";
	
	private Main model;
	private MainJFrame view;
	
	public MainController(){
		
	}
	public void setModel(Main model) {
	    this.model = model;
	}
	
	public void setView(MainJFrame view) {
	    this.view = view;
	}
	
	 @Override
	 public void actionPerformed(ActionEvent event) {
		 if (event.getActionCommand().equals(FILE)){
		    	model.createPdf(view.getFilesToOpen());
		    	view.setImage(model.getCurrentImage());
		    }else if( event.getActionCommand().equals(TAB2PDF)){
		    	model.clearImages();
		    	view.setImage(model.getCurrentImage());
		    }
	 }
	 
}
