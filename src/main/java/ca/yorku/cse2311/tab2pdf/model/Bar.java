package ca.yorku.cse2311.tab2pdf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 1/12/2015.
 */
public class Bar {

        /* TODO: A Bar should know how wide it is, so we don't print off the end of the page */


    private final List<BarLine> lines = new ArrayList<>();

    private final int numLines;

    /**
     * Constructs a Bar with 6 lines (the standard number of lines for a guitar tab)
     */
    public Bar() {

        this.numLines = 6;
    }

    /**
     * Constructs a Bar with the specified number of lines.
     * <p/>
     * For a standard guitar, this will be 6 lines. For a standard Bass guitar, this will be 4
     *
     * @param numLines The number of lines (coincides with the number of strings the guitar has)
     */
    public Bar(int numLines) {

        this.numLines = numLines;

    }

    /**
     * Returns the number of lines this bar has
     *
     * @return the number of lines this bar has
     */
    public int getNumLines() {
        return numLines;
    }

    /**
     * Retrieves the BarLine at the specified index (ie: if the Bar has 6 lines, I can ask for line #4)
     *
     * @param lineNumber The number of the single BarLine that you want to retrieve
     * @return The BarLine at the specified index
     */
    public BarLine getLine(int lineNumber) {

        if (lineNumber > numLines) {

            throw new IndexOutOfBoundsException(String.format(
                    "Cannot get line %d. This bar only contains %d lines", lineNumber, numLines
            ));
        }

        return lines.get(lineNumber);
    }

    /**
     * Adds a new BarLine to this Bar
     *
     * @param line the new BarLine to add to this Bar
     */
    public void addLine(BarLine line) {

        if (lines.size() > numLines) {

            throw new IndexOutOfBoundsException(String.format(
                    "Cannot add line %d. This bar may only contain %d lines", lines.size(), numLines
            ));
        }

        lines.add(line);
    }
}
