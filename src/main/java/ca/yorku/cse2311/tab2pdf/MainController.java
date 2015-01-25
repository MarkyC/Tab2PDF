package ca.yorku.cse2311.tab2pdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import cse1030.gui.ViewerModel;

import ca.yorku.cse2311.tab2pdf.MainModel;
import ca.yorku.cse2311.tab2pdf.MainJFrame;;


public class MainController implements ActionListener
{
	public static final String FILE = "FILE";
	
	private MainModel model;
	private MainJFrame view;
	
	public MainController(){
		
	}
	public void setModel(MainModel model) {
	    this.model = model;
	}
}
