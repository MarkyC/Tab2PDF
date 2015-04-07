package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

/**
 * Spacing
 * <p/>
 * Repesents the scaling of the tab (ie: SCALING=7.0)
 *
 * @author Glib Sitiugin
 * @since 2015-04-03
 */
public class Scaling implements ITabNotation {

    private final float scaling;

    public Scaling() {

        this(7); //default 7
    }

    public Scaling(float scaling) {

        this.scaling = scaling;
    }

    public float getScaling() {

        return scaling;
    }

    public void draw(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate) {
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

        Scaling scaling11 = (Scaling) o;

        if (scaling != scaling11.scaling) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(scaling);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {

        return "SCALING=" + scaling;
    }
}
