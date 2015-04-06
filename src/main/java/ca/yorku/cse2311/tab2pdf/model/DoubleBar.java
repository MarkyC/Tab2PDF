package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

/**
 * Created by Brody Atto on 25/01/2015.
 * <p/>
 * This class represents all variants of the double bar.
 */
public class DoubleBar implements ITabNotation {

    private int repeat;

    private boolean trailingDash;

    /**
     * True if a repeat section starts here (||*)
     */
    private boolean beginRepeat;

    /**
     * True if a repeat section ends with this bar (*||)
     */
    private boolean endRepeat;

    public DoubleBar() {
        this(1, false, false, true);
    }

    public DoubleBar(int repeat, boolean beginRepeat, boolean endRepeat, boolean trailingDash) {
        this.repeat = repeat;
        this.beginRepeat = beginRepeat;
        this.endRepeat = endRepeat;
        this.trailingDash = trailingDash;
    }

    public int getRepeat() {
        return repeat;
    }

    /**
     * @return Returns the value of beginRepeat
     */
    public boolean getBeginRepeat() {
        return beginRepeat;
    }

    /**
     * Latches the beginRepeat flag as true once set
     *
     * @param beginRepeat If the bar starts a repeat section
     */
    public void setBeginRepeat(boolean beginRepeat) {
        if (beginRepeat)
            this.beginRepeat = true;
    }

    /**
     * @return Returns the value of endRepeat
     */
    public boolean getEndRepeat() {
        return endRepeat;
    }

    /**
     * Latches the endRepeat flag as true once set
     *
     * @param endRepeat If the bar ends a repeat section
     */
    public void setEndRepeat(boolean endRepeat) {
        if (endRepeat)
            this.endRepeat = true;
    }

    public void draw(PdfHelper helper, int staveNumber, int lineNumber, float xCoordinate) {
        //Do Nothing
    }


    /**
     * The padding to the left of the character
     *
     * @return 1 if an end repeat bar else 0
     */
    public int leftPadding() {
        if (getEndRepeat()) {
            return 1;
        }
        return 0;
    }

    /**
     * The padding to the right of the character
     *
     * @return 1 if end repeat or trailing dash
     */
    public int rightPadding() {
        if (getBeginRepeat() || trailingDash) {
            return 1;
        }
        return 0;
    }

    /**
     * Logical parser size
     *
     * @return always 1
     */
    public int size() {
        int result = 2;
        if (endRepeat) {
            result++;
        }

        if (beginRepeat || trailingDash) {
            result++;
        }

        return result;

    }

    @Override
    public String toString() {
        String temp = "";

        if (endRepeat) {
            temp += "*";
        }
        temp += "|";

        if (repeat == 1) {
            temp += "|";
        } else {
            temp += repeat;
        }

        if (beginRepeat) {
            temp += "*";
        }

        if (trailingDash) {
            temp += "-";
        }

        return temp;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DoubleBar))
            return false;

        DoubleBar doubleBar = (DoubleBar) obj;

        if (doubleBar.repeat != this.repeat)
            return false;

        if (doubleBar.beginRepeat != this.beginRepeat)
            return false;

        if (doubleBar.endRepeat != endRepeat)
            return false;

        return true;
    }

}
