package ca.yorku.cse2311.tab2pdf.parser.exception;

/**
 * CouldNotParseSymbolException
 *
 * @author Marco
 * @since 2015-02-03
 */
public class CouldNotParseSymbolException extends Exception {

    public CouldNotParseSymbolException(String line) {

        super("Could not parse a symbol from this line: " + line);
    }

}
