package ca.yorku.cse2311.tab2pdf.parser;


import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.model.PullOff;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PullOffParser extends AbstractParser<PullOff> {

    /**
     * Look for intiger\intiger
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("^(\\d+)*p(\\d+)*", Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getPattern() {

        return TOKEN_PATTERN;
    }

    @Override
    public PullOff parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);

        if (m.find()) {

            Note start = (null == m.group(1)) ? PullOff.EMPTY_NOTE : new Note(Integer.parseInt(m.group(1)));
            Note end = (null == m.group(2)) ? PullOff.EMPTY_NOTE : new Note(Integer.parseInt(m.group(2)));

            return new PullOff(start, end);
        }

        throw new ParseException(token, getPattern());
    }
}
