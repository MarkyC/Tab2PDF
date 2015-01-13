package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Space;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SpaceParser
 * <p/>
 * A Parser capable of parsing a musical note
 *
 * @author Marco
 * @since 2015-01-13
 */
public class SpaceParser implements IParser<Space> {

    /**
     * Fancy regex for "a dash at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(-)");

    @Override
    public Space parse(String token) throws ParseException {

        Matcher m = TOKEN_PATTERN.matcher(token);

        if (m.find()) {

            return new Space();
        }

        throw new ParseException(String.format("Could not parse '%s' with pattern '%s'", token, TOKEN_PATTERN), 0);

    }

    @Override
    public boolean canParse(String token) {
        return TOKEN_PATTERN.matcher(token).find();
    }
}
