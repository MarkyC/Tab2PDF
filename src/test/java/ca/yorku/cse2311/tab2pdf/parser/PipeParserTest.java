package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.Pipe;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PipeParserTest {

    /**
     * None of these lines should parse into a Space
     */
    public static final String[] INVALID_LINES = {
            "-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "-|----1-----1-----1-|-----1-----1-----1-----1-|"
            , "||-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|3-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * All of these lines should parse into a space
     */
    public static final String[] VALID_LINES = {
            "|-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|---1-----1-----1-|-----1-----1-----1-----1-|"
            , "|--1-----1-----1-|-----1-----1-----1-----1-|"
            , "|-1-----1-----1-|-----1-----1-----1-----1-|"
            , "|-----------------------------"
    };

    private PipeParser parser;

    @Before
    public void setUp() {

        parser = new PipeParser();
    }

    @Test
    public void testParseInvalidLines() {

        for (String line : INVALID_LINES) {
            try { // Go through each invalid line, hoping to throw an Exception, since they cannot be parsed

                parser.parse(line);

                fail("Shouldn't be able to parse:" + line); // Fail if we get here, since the lines are invalid, and should produce an Exception

            } catch (ParseException e) {
                /* This Exception should be triggered since we gave the parser invalid lines */
            }
        }
    }

    @Test
    public void testParseValidLines() throws ParseException {

        for (String line : VALID_LINES) {   // Go through each valid line,

            Pipe s = parser.parse(line);   // parse it
            assertEquals(new Pipe(), s);   // ensure it equals the Note in the corresponding VALID_NOTES index
        }
    }

    @Test
    public void testCanParseInvalidLines() {

        for (String line : INVALID_LINES) {

            assertFalse(parser.canParse(line)); // NoteParser should not be able to parse any invalid lines
        }
    }

    @Test
    public void testCanParseValidLines() throws ParseException {

        for (String line : VALID_LINES) {

            assertTrue(parser.canParse(line)); // NoteParser should be able to parse all valid lines
        }
    }
}