package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.DoubleBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brody Atto on 25/01/2015.
 * <p/>
 * This class is responsible for parsing all variants of the double bar.
 */
public class DoubleBarParser extends AbstractParser<DoubleBar> {

    /**
     * Fancy regex for "a pipe at the beginning of the line, not followed by another pipe or a digit"
     * TODO: Should the not followed by another pipe or a digit (?!\||\d) be here?
     * I feel like the TabParser should apply a priority. Does the special case belong here or in TabParser?
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("(^(?<start>\\|(?<startR>\\||\\d*)\\*)|^(?<both>\\*\\|(?<bothR>\\||\\d*)\\*)|^(?<end>\\*\\|(?<endR>\\||\\d*))|^(?<double>\\|(?<doubleR>\\||\\d*)))(?<repeat>\\d*)");

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public DoubleBar parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);
        int repeat = 1;
        String temp;

        temp = m.group("repeat");
        if (temp != null)
            repeat = Integer.parseInt(temp);

        if (m.find()) {
            if (m.group("start") != null) {
                temp = m.group("startR");
                if (temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, false);
            } else if (m.group("both") != null) {
                temp = m.group("bothR");
                if (temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, false);
            } else if (m.group("end") != null) {
                temp = m.group("endR");
                if (temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, false);
            } else if (m.group("double") != null) {
                temp = m.group("doubleR");
                if (temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, false);
            }
        }

        throw new ParseException(token, getPattern());
    }
}
