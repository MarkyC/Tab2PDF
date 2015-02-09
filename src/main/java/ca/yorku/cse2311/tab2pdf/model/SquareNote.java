package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.PdfHelper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

/**
 * Created by Brody Atto on 25/01/2015.
 * TODO: Rename class to a mor apropriat representation of the sheet music
 */
public class SquareNote implements ITabNotation {

    private final Note note;

    public SquareNote(int note) {
        this.note = new Note(note);
    }

    public Note getNote() {
        return note;
    }

    public void draw(int staveNumber, int lineNumber, float xCoordinate, PdfWriter writer) {

        try {
            PdfHelper.drawSquareNote(staveNumber, lineNumber, xCoordinate, this, writer);
        } catch (IOException e) {
            //TODO: What should we do on an IOExeption?
        } catch (DocumentException e) {
            //TODO: What should we do on an DocumentExecption?
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

        SquareNote that = (SquareNote) o;

        if (!note.equals(that.note)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return note.hashCode();
    }

    @Override
    public String toString() {
        return "<" + note + ">";
    }
}
