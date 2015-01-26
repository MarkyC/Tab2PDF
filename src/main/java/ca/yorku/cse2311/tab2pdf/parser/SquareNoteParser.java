package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.SquareNote;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brody Atto on 25/01/2015.
 * TODO: Rename class to a mor apropriat representation of the sheet music
 */
public class SquareNoteParser extends AbstractParser<SquareNote> {

    /**
     * Fancy regex for "an integer at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^<(\\d+)>");

    @Override
    public final Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public SquareNote parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new SquareNote(m.group(1));
        }

        throw new ParseException(token, getPattern());
    }

}
