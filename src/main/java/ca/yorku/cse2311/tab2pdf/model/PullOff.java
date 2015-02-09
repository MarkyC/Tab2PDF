package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

/**
 * PullOff
 * <p/>
 * Represents a PullOff in guitar tab notation
 * A PullOff may or may or may not have a starting note
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-02-04
 */
public class PullOff implements ITabNotation, ILongDraw {

    public static final Note EMPTY_NOTE = new Note();

    private final Note start;

    private final Note end;

    public PullOff(Note end) {

        this(EMPTY_NOTE, end);
    }

    public PullOff(Note start, Note end) {

        this.start = start;
        this.end = end;
    }

    public Note getStart() {

        return start;
    }

    public Note getEnd() {

        return end;
    }

    public void draw(int staveNumber, int lineNumber, float xCoordinate, PdfWriter writer) {
        //Do Nothing
    }

    public void drawLong(int staveNumber, int lineNumber, float xCoordinate, PdfWriter writer, int oldStave, int oldLine, float oldXCoordinate, String oldString) {
        try {
            PdfHelper.drawPull(staveNumber, lineNumber, xCoordinate, this, writer, oldStave, oldLine, oldXCoordinate, oldString);
        } catch (DocumentException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
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
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullOff slide = (PullOff) o;

        if (end != null ? !end.equals(slide.end) : slide.end != null) return false;
        if (start != null ? !start.equals(slide.start) : slide.start != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "p";
    }
}
