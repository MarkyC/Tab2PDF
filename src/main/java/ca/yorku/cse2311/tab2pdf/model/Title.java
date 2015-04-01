package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

/**
 * Title
 * <p/>
 * Repesents the title of the tab (ie: TITLE=Marco Cirillo)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Title implements ITabNotation {

    public static final String DEFAULT_TITLE = "No Title";

    private final String title;

    public Title() {

        this(DEFAULT_TITLE);
    }

    public Title(String title) {

        this.title = title;
    }

    public String getValue() {

        return title;
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

        Title note1 = (Title) o;

        if (title != null ? !title.equals(note1.title) : note1.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "TITLE=" + title;
    }
}
