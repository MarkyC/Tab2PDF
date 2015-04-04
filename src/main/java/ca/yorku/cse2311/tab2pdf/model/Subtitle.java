package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

/**
 * Subtitle
 * <p/>
 * Repesents the subtitle of the tab (ie: SUBTITLE=Marco Cirillo)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Subtitle implements ITabNotation {

    public static final String DEFAULT_SUBTITLE = "No Subtitle";

    private final String subtitle;

    public Subtitle() {

        this(DEFAULT_SUBTITLE);
    }

    public Subtitle(String subtitle) {

        this.subtitle = subtitle;
    }

    public String getValue() {

        return subtitle;
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

        Subtitle note1 = (Subtitle) o;

        if (subtitle != null ? !subtitle.equals(note1.subtitle) : note1.subtitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return subtitle != null ? subtitle.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "SUBTITLE=" + subtitle;
    }
}
