package ca.yorku.cse2311.tab2pdf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Bar
 *
 * Represents one bar of music
 *
 * @author Marco
 * @since 2015-01-12
 */
public class Bar {

    /* TODO: A Bar should know how wide it is, so we don't print off the end of the page */

    private final List<BarLine> lines = new ArrayList<>();

    private final int numLines;

    private int length;

    private boolean endRepeat = false;

    private boolean beginRepeat = false;

    private int repeat;

    /**
     * Constructs a Bar with 6 lines (the standard number of lines for a guitar tab)
     */
    public Bar() {

        this(6);
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

    public List<BarLine> getLines() {

        return lines;
    }

    public boolean isEmpty() {

        return lines.isEmpty();
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
     * Sets the length of the bar
     *
     * @param len The new length of the bar
     */
    public void setBarLength(int len) {
        this.length = len;
    }

    /**
     * Returns the length of the bar
     *
     * @return Lenght of the bar
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length of the bar
     *
     * @param len The new length of the bar
     */
    public void setBarRepeat(int len) {
        this.repeat = len;
    }

    /**
     * Returns the length of the bar
     *
     * @return Lenght of the bar
     */
    public int getRepeat() {
        return repeat;
    }

    /**
     * Returns the begin repeat state of the bar
     *
     * @return True if the bar is a begin repeat else false
     */
    public boolean getBeginRepeat() {
        return beginRepeat;
    }


    /**
     * Sets the begin repeat state of the bar
     *
     * @param flag True if the bar is a begin repeat, else false
     */
    public void setBeginRepeat(boolean flag) {
        beginRepeat = flag;
    }

    /**
     * Returns the end repeat state of the bar
     *
     * @return True if the bar is a end repeat else false
     */
    public boolean getEndRepeat() {
        return endRepeat;
    }


    /**
     * Sets the end repeat state of the bar
     *
     * @param flag True if the bar is a end repeat, else false
     */
    public void setEndRepeat(boolean flag) {
        endRepeat = flag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bar bar = (Bar) o;

        if (numLines != bar.numLines) return false;
        if (lines != null ? !lines.equals(bar.lines) : bar.lines != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lines != null ? lines.hashCode() : 0;
        result = 31 * result + numLines;
        return result;
    }

    @Override
    public String toString() {

        return lines.toString();
    }
}
