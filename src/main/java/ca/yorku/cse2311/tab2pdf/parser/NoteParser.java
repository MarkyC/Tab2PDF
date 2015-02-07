package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NoteParser
 *
 * A Parser capable of parsing a musical note
 *
 * @author Marco
 * @since 2015-01-13
 */
public class NoteParser extends AbstractParser<Note> {

    /**
     * Fancy regex for "an integer at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\d+)");

    @Override
    public final Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Note parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new Note(Integer.parseInt(m.group(1)));
        }

        throw new ParseException(token, getPattern());
    }

}
