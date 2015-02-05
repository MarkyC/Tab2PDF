package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.drawDigit;

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

    public int getNote() {
        return note;
    }

    public void draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        try {
            drawDigit(staveNumber, lineNumber, xCoordinate, note, writer);
        } catch (IOException e) {
            //TODO: What should we do on an IOExeption?
        } catch (DocumentException e) {
            //TODO: What should we do on an DocumentExecption?
        }
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
