package ca.yorku.cse2311.tab2pdf.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marco on 1/12/2015.
 */
public class BarLine {

    private List<IMusicalNotation> line = new LinkedList<>();

    public List<IMusicalNotation> getLine() {
        return line;
    }

    public void addMusicalNotation(IMusicalNotation note) {

        line.add(note);
    }
}
