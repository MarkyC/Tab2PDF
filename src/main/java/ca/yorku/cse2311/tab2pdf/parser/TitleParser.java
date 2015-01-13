package ca.yorku.cse2311.tab2pdf.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TitleParser
 *
 * @author Marco
 * @since 2015-01-13
 */
public class TitleParser extends AbstractParser<String> {

    /**
     * Look for TITLE=Some Title, or title=Something, or TiTle=Long Title Here One Or More Words
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^TITLE=(.+)", Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public String parse(String token) throws ParseException {
        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return m.group(1);
        }

        throw new ParseException(token, getPattern());
    }
}
