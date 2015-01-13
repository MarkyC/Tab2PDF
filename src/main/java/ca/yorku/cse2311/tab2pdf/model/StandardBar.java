package ca.yorku.cse2311.tab2pdf.model;

/**
 * StandardBar
 *
 * Represents a Standard Bar ('|') in Musical Notation. A Standard Bar separates Bars of music
 * It's the leftmost thing in the image on this page: http://en.wikipedia.org/wiki/Bar_%28music%29
 *
 * @author Brody
 * @since 2015-01-12
 */
public class StandardBar implements IMusicalNotation {

    @Override
    public String toString() {
        return "StandardBar{}";
    }
}
