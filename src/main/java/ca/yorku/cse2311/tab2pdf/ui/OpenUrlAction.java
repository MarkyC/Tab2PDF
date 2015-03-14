package ca.yorku.cse2311.tab2pdf.ui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenUrlAction implements ActionListener
{
	private final String LINK = "http://markyc.github.io/Tab2PDF/user-manual.pdf";
	
	
	public void actionPerformed(ActionEvent e){
		try{
			URI uri = new URI(LINK);
			open(uri);
			System.out.println("URI parsed succesfully!");
		}catch (URISyntaxException m){
			m.printStackTrace();
		}
		
	}
	
	public static void open(URI uri){
    	if (Desktop.isDesktopSupported()){
    		try{
    			Desktop.getDesktop().browse(uri);
    		}catch (IOException e){
    			e.printStackTrace();
    		}
    	}else{
    		//TODO
    	}
    }
	
}
