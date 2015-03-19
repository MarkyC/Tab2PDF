package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

/**
 * Pipe
 *
 * Represents a Pipe ('|') in Musical Notation. A Pipe separates Standard Bars of music
 * It's the leftmost thing in the image on this page: http://en.wikipedia.org/wiki/Bar_%28music%29
 *
 * @author Brody
 * @since 2015-01-12
 */
public class Pipe implements ITabNotation {

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
        return 1;
    }

    @Override
    public String toString() {
        return "|";
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Pipe;
    }

    public void draw(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate) {
        helper.thinLine(staveNumber, xCoordinate);
    }
}
