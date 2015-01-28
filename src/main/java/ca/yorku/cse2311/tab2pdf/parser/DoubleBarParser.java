package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.DoubleBar;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brody Atto on 25/01/2015.
 * <p/>
 * This class is responsible for parsing all variants of the double bar.
 */
public class DoubleBarParser extends AbstractParser<DoubleBar> {

    /**
     * Fancy regex to recognise; ||*, *||*, *|| or || where any of the second pipes can be replaced with a number
     * It follows a priority in the order listed
     */
    public static final Pattern TOKEN_PATTERN = Pattern.compile("(^(?<start>^\\|(?<startR>\\||\\d+)\\*)|^(?<both>\\*\\|(?<bothR>\\||\\d+)\\*)|^(?<end>\\*\\|(?<endR>\\||\\d+))|^(?<double>^\\|(?<doubleR>\\||\\d+)))(?!\\||\\d)");

    @Override
    public Pattern getPattern() {
        return TOKEN_PATTERN;
    }

    @Override
    public DoubleBar parse(String token) throws ParseException {

        Matcher m = getPattern().matcher(token);
        int repeat = 1;
        String temp;


        if (m.find()) {

            if (!(m.group("start") == null || m.group("start").equals(""))) {
                temp = m.group("startR");
                if (!temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, false);
            } else if (!(m.group("both") == null || m.group("both").equals(""))) {
                temp = m.group("bothR");
                if (!temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, true, true);
            } else if (!(m.group("end") == null || m.group("end").equals(""))) {
                temp = m.group("endR");
                if (!temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, false, true);
            } else if (!(m.group("double") == null || m.group("double").equals(""))) {
                temp = m.group("doubleR");
                if (!temp.equals("|"))
                    repeat = Integer.parseInt(temp);
                return new DoubleBar(repeat, false, false);
            }
        }

        throw new ParseException(token, getPattern());
    }
}
