package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody Atto on 25/01/2015.
 * TODO: Rename class to a mor apropriat representation of the sheet music
 */
public class SquareNote implements ITabNotation {

    private final String note;

    public SquareNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
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
