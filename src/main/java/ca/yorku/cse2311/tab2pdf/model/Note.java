package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

/**
 * Note
 *
 * Repesents a single musical note (ie: '3' or '8')
 *
 * @author Brody
 * @since 2015-01-12
 */
public class Note implements ITabNotation, IDrawable {

    private final int note;

    public Note(int note) {
        this.note = note;
    }

    public Note() {
        this(0);
    }

    public int getValue() {
        return note;
    }

    public void draw(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate) {
        try {
            helper.drawDigit(staveNumber, lineNumber, xCoordinate, note);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return toString().length() - 1;
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

        Note note1 = (Note) o;

        if (note != note1.note) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return note;
    }

    @Override
    public String toString() {
        return note + "";
    }
}
