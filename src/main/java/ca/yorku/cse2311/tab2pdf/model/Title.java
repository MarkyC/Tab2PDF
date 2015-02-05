package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Title
 * <p/>
 * Repesents the title of the tab (ie: TITLE=Marco Cirillo)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Title implements ITabNotation {

    private final String title;

    public Title() {

        this("No Title");
    }

    public Title(String title) {

        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        //Do Nothing
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
