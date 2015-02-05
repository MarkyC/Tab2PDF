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

    private final double spacing;

    public Spacing() {

        this(5); //default 5
    }

    public Spacing(double spacing) {

        this.spacing = spacing;
    }

    public double getSpacing() {

        return spacing;
    }

    public void Draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        //Do Nothing
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
