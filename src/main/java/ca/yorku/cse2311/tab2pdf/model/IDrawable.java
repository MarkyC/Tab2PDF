package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * IDrawable
 * <p/>
 * Interface to allow drawing of each object
 *
 * @author Brody
 * @since 2015-02-05
 */
public interface IDrawable {

    public void Draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer);

}
