package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by Brody Atto on 25/01/2015.
 * <p/>
 * This class represents all variants of the double bar.
 */
public class DoubleBar implements ITabNotation {

    private int repeat;

    /**
     * True if a repeat section starts here (||*)
     */
    private boolean beginRepeat;

    /**
     * True if a repeat section ends with this bar (*||)
     */
    private boolean endRepeat;

    public DoubleBar() {
        this(1, false, false);
    }

    public DoubleBar(int repeat, boolean beginRepeat, boolean endRepeat) {
        this.repeat = repeat;
        this.beginRepeat = beginRepeat;
        this.endRepeat = endRepeat;
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

    public void draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        //Do Nothing
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
