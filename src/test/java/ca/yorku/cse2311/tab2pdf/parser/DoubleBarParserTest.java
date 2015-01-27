package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.DoubleBar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleBarParserTest {

    /**
     * None of these lines should parse into a Space
     */
    public static final String[] INVALID_LINES = {
            "-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "-|----1-----1-----1-|-----1-----1-----1-----1-|"
            , "**||-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|||3-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * All of these lines should parse into a space
     */
    public static final String[] VALID_LINES = {
            "||*-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "*||----1-----1-----1-|-----1-----1-----1-----1-|"
            , "*|5*---1-----1-----1-|-----1-----1-----1-----1-|"
            , "*||*--1-----1-----1-|-----1-----1-----1-----1-|"
            , "|2-1-----1-----1-|-----1-----1-----1-----1-|"
            , "||-----------------------------"
    };

    public static final DoubleBar[] VALID_RESPONSES = {
            new DoubleBar(1, true, false),
            new DoubleBar(1, false, true),
            new DoubleBar(5, true, true),
            new DoubleBar(1, true, true),
            new DoubleBar(2, false, false),
            new DoubleBar()
    };

    private DoubleBarParser parser;

    @Before
    public void setUp() {
        parser = new DoubleBarParser();
    }

    @Test
    public void testParseInvalidLines() {

        for (String line : INVALID_LINES) {
            try { // Go through each invalid line, hoping to throw an Exception, since they cannot be parsed

                parser.parse(line);

                fail("Was able to parse an invalid line"); // Fail if we get here, since the lines are invalid, and should produce an Exception

            } catch (ParseException e) {
                /* This Exception should be triggered since we gave the parser invalid lines */
            }
        }
    }

    @Test
    public void testParseValidLines() throws ParseException, NumberFormatException {

        for (int i = 0; i < VALID_LINES.length; ++i) {  // Go through each valid line,

            String line = VALID_LINES[i];          // grab the current line
            DoubleBar doubleBar = parser.parse(line);      // parse it
            assertEquals(VALID_RESPONSES[i], doubleBar); // ensure it equals the Title in the corresponding CORRECT_TITLES index

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
