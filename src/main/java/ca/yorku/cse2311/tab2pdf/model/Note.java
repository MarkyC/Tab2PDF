package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody on 2015-01-12.
 */
public class Note implements IMusicalNotation {

    private final String note;

    public Note(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note1 = (Note) o;

        if (note != null ? !note.equals(note1.note) : note1.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return note != null ? note.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note='" + note + '\'' +
                '}';
    }
}
