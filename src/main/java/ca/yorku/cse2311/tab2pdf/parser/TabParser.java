package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.IMusicalNotation;
import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.model.Space;
import ca.yorku.cse2311.tab2pdf.model.StandardBar;
import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * TabParser
 *
 * Parser than can parse an entire Tab
 *
 * @author Marco
 * @since 2015-01-13
 */
public class TabParser {

    public static final List<IMusicalNotation> MUSICAL_NOTES = new ArrayList<>();

    static {
        MUSICAL_NOTES.add(new Note(""));
        MUSICAL_NOTES.add(new Space());
        MUSICAL_NOTES.add(new StandardBar());
    }

    public static final List<AbstractParser> PARSERS = new ArrayList<>();

    static {
        PARSERS.add(new NoteParser());
        PARSERS.add(new SpaceParser());
        PARSERS.add(new StandardBarParser());
        //PARSERS.add()

    }


    public static boolean endOfLine(int index, String line) {
        return (index + 1 > line.length());
    }

    public static String getTitle(List<String> lines) throws Exception {

        TitleParser parser = new TitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                return parser.parse(line);
            }
        }

        throw new Exception("No title found in file");
    }

    public static String getSubtitle(List<String> lines) throws Exception {

        SubtitleParser parser = new SubtitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                return parser.parse(line);
            }
        }

        throw new Exception("No subtitle found in file");
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
