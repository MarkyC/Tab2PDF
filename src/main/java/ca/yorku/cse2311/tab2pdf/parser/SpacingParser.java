package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Spacing;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SpacingParser
 *
 * Parses
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-02-03
 */
public class SpacingParser extends AbstractParser<Spacing> {
    /**
     * Look for SPACING=Some float
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("SPACING=([0-9]*\\.?[0-9]+)", Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Spacing parse(String token) throws ParseException {
        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new Spacing(Float.parseFloat(m.group(1)));
        }

        throw new ParseException(token, getPattern());
    }
}