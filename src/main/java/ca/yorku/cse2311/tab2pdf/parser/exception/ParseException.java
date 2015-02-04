package ca.yorku.cse2311.tab2pdf.parser.exception;

import java.util.regex.Pattern;

/**
 * ParseException
 *
 * @author Marco
 * @since 2015-01-13
 */
public class ParseException extends Exception {

    public ParseException(String token, Pattern pattern) {
        super(String.format("Could not parse '%s' with pattern '%s'", token, pattern));
    }
}
