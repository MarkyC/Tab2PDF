package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Spacing
 * <p/>
 * Repesents the spacing of the tab (ie: SPACING=4.4)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Spacing implements ITabNotation {

    private final float spacing;

    public Spacing() {

        this(5); //default 5
    }

    public Spacing(float spacing) {

        this.spacing = spacing;
    }

    public float getSpacing() {

        return spacing;
    }

    public void draw(int staveNumber, int lineNumber, float xCoordinate, PdfWriter writer) {
        //Do Nothing
    }

    /**
     * The padding to the left of the character
     *
     * @return always 0
     */
    public int leftPadding() {
        return 0;
    }

    /**
     * The padding to the right of the character
     *
     * @return always 0
     */
    public int rightPadding() {
        return 0;
    }

    /**
     * Logical parser size
     *
     * @return always 1
     */
    public int size() {
        return toString().length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spacing spacing1 = (Spacing) o;

        if (spacing != spacing1.spacing) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(spacing);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {

        return "SPACING=" + spacing;
    }
}
