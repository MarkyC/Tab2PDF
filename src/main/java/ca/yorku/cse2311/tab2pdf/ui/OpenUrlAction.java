package ca.yorku.cse2311.tab2pdf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenUrlAction implements ActionListener
{
	private final URI uri = new URI("http://markyc.github.io/Tab2PDF/");
	
	public void actionPerformed(ActionEvent e){
		HelpListener.open(uri);
	}
	
	public void checkURI(URI newUri){
		try{
			URI uri = new URI(newUri.getRawPath());
			System.out.println("URI parsed succesfully!");
		}catch (URISyntaxException e){
			e.printStackTrace();
		}
	}
}
