package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

/**
 * ILongDraw
 * <p/>
 * This interface implements draws across multiple notes
 *
 * @author Brody
 * @since 2015-02-05
 */
public interface ILongDraw extends IDrawable {

    public void drawLong(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate, int oldStave, int OldLine, float oldXCoordinate, String oldString);
}
