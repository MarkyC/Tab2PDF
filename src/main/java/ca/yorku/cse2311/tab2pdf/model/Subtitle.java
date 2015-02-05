package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Subtitle
 * <p/>
 * Repesents the subtitle of the tab (ie: SUBTITLE=Marco Cirillo)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Subtitle implements ITabNotation {

    private final String subtitle;

    public Subtitle() {

        this("No Subtitle");
    }

    public Subtitle(String subtitle) {

        this.subtitle = subtitle;
    }

    public String getSubtitle() {

        return subtitle;
    }


    public void Draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        //Do Nothing
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
