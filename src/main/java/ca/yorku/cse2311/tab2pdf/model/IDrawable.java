package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

/**
 * IDrawable
 * <p/>
 * Interface to allow drawing of each object
 *
 * @author Brody
 * @since 2015-02-05
 */
public interface IDrawable {

    public void draw(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate);

}
