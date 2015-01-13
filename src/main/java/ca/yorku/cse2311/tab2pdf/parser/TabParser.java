package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.IMusicalNotation;
import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.model.Space;
import ca.yorku.cse2311.tab2pdf.model.StandardBar;
import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 1/12/2015.
 */
public class TabParser {

    public static final List<IMusicalNotation> MUSICAL_NOTES = new ArrayList<>();

    static {
        MUSICAL_NOTES.add(new Note(""));
        MUSICAL_NOTES.add(new Space());
        MUSICAL_NOTES.add(new StandardBar());
    }

    public static boolean endOfLine(int index, String line) {
        return (index + 1 > line.length());
    }

    public Tab parse(List<String> lines) {

        Tab tab = new Tab();

        for (String line : lines) {

            parseLine(line);
        }

        return null;
    }

    public void parseLine(String line) {

    }

}
