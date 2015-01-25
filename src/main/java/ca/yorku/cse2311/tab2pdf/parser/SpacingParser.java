package ca.yorku.cse2311.tab2pdf.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brody Atto on 21/01/2015.
 */
public class SpacingParser extends AbstractParser<String>  {
    /**
     * Look for SPACING=Some float
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("SPACING=([0-9]*\\.?[0-9]+)", Pattern.CASE_INSENSITIVE);

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