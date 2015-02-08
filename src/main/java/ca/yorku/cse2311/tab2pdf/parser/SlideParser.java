package ca.yorku.cse2311.tab2pdf.parser;


import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.model.Slide;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlideParser extends AbstractParser<Slide> {
    /**
     * Look for intiger\intiger
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\d+)*s(\\d+)*", Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public Slide parse(String token) throws ParseException {
        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            Note start = (null == m.group(1)) ? Slide.EMPTY_NOTE : new Note(Integer.parseInt(m.group(1)));
            Note end = (null == m.group(2)) ? Slide.EMPTY_NOTE : new Note(Integer.parseInt(m.group(2)));

            return new Slide(start, end);
        }

        throw new ParseException(token, getPattern());
    }
}
