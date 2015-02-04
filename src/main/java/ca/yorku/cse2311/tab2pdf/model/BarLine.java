package ca.yorku.cse2311.tab2pdf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * BarLine
 *
 * Represents one line of a Bar (ie: one of the 6 guitar strings)
 *
 * @author Marco
 * @since 2015-01-12
 */
public class BarLine {

    private List<ITabNotation> line;

    public BarLine() {

        this(new ArrayList<ITabNotation>());
    }

    public BarLine(List<ITabNotation> line) {

        this.line = line;
    }

    public List<ITabNotation> getLine() {
        return line;
    }

    public void addNote(ITabNotation note) {

        line.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BarLine barLine = (BarLine) o;

        if (line != null ? !line.equals(barLine.line) : barLine.line != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return line != null ? line.hashCode() : 0;
    }

    @Override
    public String toString() {

        return line.toString();
    }
}
