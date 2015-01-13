package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Space;

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
public class SpaceParser extends AbstractParser<Space> {

    /**
     * Fancy regex for "a dash at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(-)");

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Space parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new Space();
        }

        throw new ParseException(token, getPattern());
    }
}
