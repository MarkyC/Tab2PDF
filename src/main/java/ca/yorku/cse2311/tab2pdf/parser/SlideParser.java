package ca.yorku.cse2311.tab2pdf.parser;


import ca.yorku.cse2311.tab2pdf.model.Slide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brody Atto on 25/01/2015.
 */
public class SlideParser extends AbstractParser<Slide> {
    /**
     * Look for intiger\intiger
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\ds\\d)", Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Slide parse(String token) throws ParseException {
        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            return new Slide(m.group(1));
        }

        throw new ParseException(token, getPattern());
    }
}
