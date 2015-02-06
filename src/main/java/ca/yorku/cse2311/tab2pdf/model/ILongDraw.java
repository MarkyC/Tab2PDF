package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * ILongDraw
 * <p/>
 * This interface implements draws across multiple notes
 *
 * @author Brody
 * @since 2015-02-05
 */
public interface ILongDraw extends IDrawable {

    public void drawLong(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer, int oldStave, int OldLine, int oldXCoordinate, String oldString);
}
