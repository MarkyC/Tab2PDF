package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.StandardBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StandardBarParser
 * <p/>
 * A Parser capable of parsing a musical note
 *
 * @author Marco
 * @since 2015-01-13
 */
public class StandardBarParser extends AbstractParser<StandardBar> {

    /**
     * Fancy regex for "a pipe at the beginning of the line, not followed by another pipe or a digit"
     * TODO: Should the not followed by another pipe or a digit (?!\||\d) be here?
     * I feel like the TabParser should apply a priority. Does the special case belong here or in TabParser?
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\|)(?!\\||\\d)");

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public StandardBar parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new StandardBar();
        }

        throw new ParseException(token, getPattern());
    }
}
