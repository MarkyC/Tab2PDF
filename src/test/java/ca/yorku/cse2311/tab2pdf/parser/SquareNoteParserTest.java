package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.SquareNote;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareNoteParserTest {

    /**
     * None of these lines should parse into a Note
     */
    public static final String[] INVALID_LINES = {
            "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "-1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "23-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * All of these lines should parse into a note
     */
    public static final String[] VALID_LINES = {
            "<1>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<12>----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<2>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<3>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<4>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<5>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<6>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<7>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<8>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<9>-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "<0>-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * VALID_LINES[i] should equal VALID_NOTES[i].
     * That means that each line in VALID_LINES should equal its corresponding Note in VALID_NOTES
     */
    public static final SquareNote[] VALID_NOTES = {
            new SquareNote("1")
            , new SquareNote("12")
            , new SquareNote("2")
            , new SquareNote("3")
            , new SquareNote("4")
            , new SquareNote("5")
            , new SquareNote("6")
            , new SquareNote("7")
            , new SquareNote("8")
            , new SquareNote("9")
            , new SquareNote("0")
    };

    private SquareNoteParser parser;

    @Before
    public void setUp() {
        parser = new SquareNoteParser();
    }

    @Test
    public void testParseInvalidLines() {

        for (String line : INVALID_LINES) {
            try { // Go through each invalid line, hoping to throw an Exception, since they cannot be parsed

                parser.parse(line);

                fail(); // Fail if we get here, since the lines are invalid, and should produce an Exception

            } catch (ParseException e) {
                /* This Exception should be triggered since we gave the parser invalid lines */
            }
        }
    }

    @Test
    public void testParseValidLines() throws ParseException {

        for (int i = 0; i < VALID_LINES.length; ++i) {  // Go through each valid line,

            String line = VALID_LINES[i];   // grab the current line
            SquareNote n = parser.parse(line);    // parse it
            assertEquals(VALID_NOTES[i], n);// ensure it equals the Note in the corresponding VALID_NOTES index
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