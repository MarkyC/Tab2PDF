package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Dash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DashParser
 * <p/>
 * A Parser capable of parsing a dash ('-')
 *
 * @author Marco
 * @since 2015-01-13
 */
public class DashParser extends AbstractParser<Dash> {

    /**
     * Fancy regex for "a dash at the beginning of the line"
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(-)");

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Dash parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new Dash();
        }

        throw new ParseException(token, getPattern());
    }
}
