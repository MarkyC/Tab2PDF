package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody Atto on 25/01/2015.
 * <p/>
 * This class represents all variants of the double bar.
 */
public class DoubleBar implements IMusicalNotation {

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

    @Override
    public String toString() {
        return String.format(
                "DoubleBar{repeat=%d, beginRepeat=%b, endRepeat=%b", repeat, beginRepeat, endRepeat
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StandardBar;
    }

}
