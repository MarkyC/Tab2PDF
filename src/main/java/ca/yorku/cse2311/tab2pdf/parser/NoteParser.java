package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Note;

import java.text.ParseException;
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
public class NoteParser implements IParser<Note> {

    /**
     * Fancy regex for "an integer at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\d)");

    @Override
    public Note parse(String token) throws ParseException {

        Matcher m = TOKEN_PATTERN.matcher(token);

        if (m.find()) {

            return new Note(m.group(1));
        }

        throw new ParseException(String.format("Could not parse '%s' with pattern '%s'", token, TOKEN_PATTERN), 0);

    }

    @Override
    public boolean canParse(String token) {
        return TOKEN_PATTERN.matcher(token).find();
    }
}
