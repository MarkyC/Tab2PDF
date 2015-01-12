package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody on 2015-01-12.
 */
public class Note implements IParseable {

    private final String note;

    public Note(String note) {
        this.note = note;
    }

    public String getSymbol() {
        return note;
    }

}
