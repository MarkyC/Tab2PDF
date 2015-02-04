package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.exception.CouldNotParseSymbolException;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * TabParser
 *
 * Parser than can parse an entire Tab
 *
 * @author Marco Cirillo
 * @since 2015-01-13
 */
public class TabParser {

    public static final List<ITabNotation> MUSICAL_NOTES = new ArrayList<>();

    static {
        MUSICAL_NOTES.add(new Note(""));
        MUSICAL_NOTES.add(new Dash());
        MUSICAL_NOTES.add(new Pipe());
    }

    public static final List<IParser> PARSERS = new ArrayList<>();

    static {
        PARSERS.add(new SpacingParser());
        PARSERS.add(new TitleParser());
        PARSERS.add(new SubtitleParser());
        PARSERS.add(new NoteParser());
        PARSERS.add(new DashParser());
        PARSERS.add(new PipeParser());
        //PARSERS.add()
    }

    private static final Logger LOG = Logger.getLogger(TabParser.class.getSimpleName());

    public static boolean endOfLine(int index, String line) {
        return (index + 1 > line.length());
    }

    public static String getTitle(List<String> lines) throws Exception {

        TitleParser parser = new TitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                return parser.parse(line).getTitle();
            }
        }

        throw new Exception("No title found in file");
    }

    public static String getSubtitle(List<String> lines) throws Exception {

        SubtitleParser parser = new SubtitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                return parser.parse(line).getSubtitle();
            }
        }

        throw new Exception("No subtitle found in file");
    }

    public Tab parse(List<String> lines) throws Exception {

        Tab tab = new Tab();

        for (String line : lines) {

            parseLine(line);

            // Youtube break.
        }

        return null;
    }

    /**
     * Given a line:
     * <p/>
     * |-------------------------|-------------------------|
     * <p/>
     * This method will generate a List of ITabNotation objects that represent the line
     * <p/>
     * Pipe, Dash, Dash, ..., Pipe, Dash, Dash, ..., Pipe
     * <p/>
     * Note that calling ITabNotation.toString() on the List should give you back your original tab
     *
     * @param line A single line of ascii guitar tablature
     * @return A List of the tabs representation in ITabNotation objects
     */
    public List<ITabNotation> parseLine(String line) throws ParseException {

        List<ITabNotation> result = new ArrayList<>(line.length());

        for (int i = 0; i < line.length(); ++i) {

            // The current section of the line we are parsing
            String leftToParse = line.substring(i);

            try {

                // parse the remainder of the line
                ITabNotation symbol = parseSymbol(leftToParse);

                // parsing the line succeeded

                // add the symbol to the result list
                result.add(symbol);

                // Advance the pointer to the end of the parsed symbol
                i += symbol.toString().length() - 1;

            } catch (CouldNotParseSymbolException e) {
                LOG.warning(e.getMessage());
                i++;    // Skip the current symbol
            }
        }

        return result;
    }

    public ITabNotation parseSymbol(String line) throws CouldNotParseSymbolException, ParseException {

        for (IParser parser : PARSERS) {
            if (parser.canParse(line)) {
                return (ITabNotation) parser.parse(line);
            }
        }

        throw new CouldNotParseSymbolException(line);
    }
}
