package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.exception.BarFormatException;
import ca.yorku.cse2311.tab2pdf.parser.exception.CouldNotParseSymbolException;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public static final List<IParser> PARSERS = new ArrayList<>();

    static {
        PARSERS.add(new SpacingParser());
        PARSERS.add(new TitleParser());
        PARSERS.add(new SubtitleParser());
        PARSERS.add(new NoteParser());
        PARSERS.add(new DashParser());
        PARSERS.add(new PipeParser());
        PARSERS.add(new SlideParser());
        PARSERS.add(new HammerOnParser());
        PARSERS.add(new PullOffParser());
        PARSERS.add(new DoubleBarParser());
        PARSERS.add(new SquareNoteParser());
        //PARSERS.add()
    }

    private static final Logger LOG = Logger.getLogger(TabParser.class.getSimpleName());

    public static boolean endOfLine(int index, String line) {
        return (index + 1 > line.length());
    }

    public static boolean isTitleLine(String line) {

        return new TitleParser().canParse(line);
    }

    public static boolean isSubtitleLine(String line) {

        return new SubtitleParser().canParse(line);
    }

    public static boolean isSpacingLine(String line) {

        return new SpacingParser().canParse(line);
    }

    public static Title getTitle(List<String> lines) {

        TitleParser parser = new TitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                try {
                    return parser.parse(line);
                } catch (ParseException e) {
                    LOG.warning(e.getMessage());
                }
            }
        }

        // If no title was found, give it the default title
        return new Title();
    }

    public static Subtitle getSubtitle(List<String> lines) {

        SubtitleParser parser = new SubtitleParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                try {
                    return parser.parse(line);
                } catch (ParseException e) {
                    LOG.warning(e.getMessage());
                }
            }
        }

        // no subtitle found, give it the default subtitle
        return new Subtitle();
    }

    public static Spacing getSpacing(List<String> lines) {

        SpacingParser parser = new SpacingParser();

        for (String line : lines) {
            if (parser.canParse(line)) {
                try {
                    return parser.parse(line);
                } catch (ParseException e) {
                    LOG.warning(e.getMessage());
                }
            }
        }

        // no Spacing found, give it the default Spacing
        return new Spacing();
    }

    public static Tab parse(List<String> lines) {

        Tab tab = new Tab(getTitle(lines), getSubtitle(lines), getSpacing(lines));
        //Bar bar = new Bar();

        List<Bar> bars = new ArrayList<>();

        for (int i = 0; i < lines.size(); ++i) {

            String line = lines.get(i);

            // Skip lines that are title or spacing lines
            if (isTitleLine(line) || isSubtitleLine(line) || isSpacingLine(line)) {
                LOG.info("Skipping title/subtitle/spacing line: " + line);
                continue;
            }

            // A blank line begins a new Bar
            if (line.isEmpty()) {
                for (Bar bar : bars) {
                    try {
                        int barLenght = 0;
                        int barRepeat = -1;
                        for (BarLine barLine : bar.getLines()) { //assign bar values
                            //find length
                            if (0 == barLenght && barLine.getLine().size() != 0) {
                                barLenght = barLine.getLine().size();
                            } else if (barLine.getLine().size() != barLenght) {
                                throw new BarFormatException("Varying bar lengths"); //TODO: Overkill should call a tab repair function to fix it, needs a copy method to keep a clean copy
                            }
                            //find repeat
                            ITabNotation lastSymbol = barLine.getLine().get(barLenght - 1);

                            if (lastSymbol.getClass() == DoubleBar.class)
                                if (-1 == barRepeat) {
                                    barRepeat = ((DoubleBar) lastSymbol).getRepeat();
                                } else if (barLine.getLine().size() > barLenght) {
                                    throw new BarFormatException("Varying bar lengths"); //Overkill should call a tab repair function to fix it
                                }

                            //Set and remove begin bar
                            if (barLine.getLine().get(0).getClass() == DoubleBar.class) {
                                if (!bar.getBeginRepeat()) {
                                    bar.setBeginRepeat(((DoubleBar) barLine.getLine().get(0)).getBeginRepeat());
                                }
                            }


                            barLine.getLine().remove(0);

                            //Set and remove end bar
                            if (barLine.getLine().get(barLenght - 2).getClass() == DoubleBar.class) {
                                if (!bar.getEndRepeat()) {
                                    bar.setEndRepeat(((DoubleBar) barLine.getLine().get(barLenght - 2)).getEndRepeat());
                                }
                            }
                            barLine.getLine().remove(barLenght - 2);
                        }

                        bar.setBarLength(barLenght);
                        bar.setBarRepeat(Math.abs(barRepeat));

                        if (!bar.isEmpty()) {
                            LOG.info("Adding Bar: " + bar.toString());
                            tab.addBar(bar);    // Add current bar if its not empty
                        }


                    } catch (BarFormatException e) {
                        LOG.warning(e.getMessage());
                        LOG.warning("Discarding bar: " + bar);
                    }

                }
                bars = new ArrayList<>();        // Make a new Bar for the next lines
                continue;               // Skip this line since its blank
            }


            try {
                List<List<ITabNotation>> parsedLine = parseLine(line);
                for (int j = 0; j < parsedLine.size(); j++) {
                    BarLine barLine = new BarLine(parsedLine.get(j));
                    LOG.info("Adding Bar Line: " + barLine.toString());
                    if (bars.size() <= j) {
                        bars.add(new Bar());
                    }
                    bars.get(j).addLine(barLine);

                }

            } catch (ParseException e) {
                LOG.warning(e.getMessage());
                LOG.warning("Discarding line: " + line);
            }
        }

        return tab;
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
    public static List<List<ITabNotation>> parseLine(String line) throws ParseException {

        List<ITabNotation> resultFrag = new ArrayList<>(line.length());
        List<List<ITabNotation>> result = new LinkedList<>();

        for (int i = 0; i < line.length(); ++i) {

            // The current section of the line we are parsing
            String leftToParse = line.substring(i);

            try {

                // parse the remainder of the line
                ITabNotation symbol = parseSymbol(leftToParse);

                // parsing the line succeeded


                //Adds the left padding
                int leftPad = 0;
                while (symbol.leftPadding() - leftPad++ > 0) {
                    resultFrag.add(new Dash(true));
                }

                //Separates multiple bars on the same line
                if (Pipe.class == symbol.getClass() || DoubleBar.class == symbol.getClass()) {
                    if (!resultFrag.isEmpty()) {
                        resultFrag.add(symbol);
                        result.add(resultFrag);
                        resultFrag = new ArrayList<>(line.length());
                    }
                }

                // Advance the pointer to the end of the parsed symbol
                i += symbol.size() - 1;

                // add the symbol to the result list
                resultFrag.add(symbol);


                //Adds right padding
                int rightPad = 0;
                while (symbol.rightPadding() - rightPad++ > 0) {
                    resultFrag.add(new Dash(true));
                }


            } catch (CouldNotParseSymbolException e) {
                LOG.warning(e.getMessage());
                //i++;    // Skip the current symbol //not needed, will skip when i hits the for loop anyway
            }
        }
        if (result.isEmpty()) {
            result.add(resultFrag);
        }
        return result;
    }

    public static ITabNotation parseSymbol(String line) throws CouldNotParseSymbolException, ParseException {

        for (IParser parser : PARSERS) {
            if (parser.canParse(line)) {
                return (ITabNotation) parser.parse(line);
            }
        }

        throw new CouldNotParseSymbolException(line);
    }
}
