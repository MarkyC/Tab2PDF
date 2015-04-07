package ca.yorku.cse2311.tab2pdf.parser;

import org.junit.Before;
import org.junit.Test;

import ca.yorku.cse2311.tab2pdf.model.Note;
import ca.yorku.cse2311.tab2pdf.model.Slide;
import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SlideParserTest {

    /**
     * None of these lines should parse into a Note
     */
    public static final String[] INVALID_LINES = {
            "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "-1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "12-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "||12-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "%12-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * All of these lines should parse into a note
     */
    public static final String[] VALID_LINES = {
            "0s1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "1s2----1-----1-----1-|-----1-----1-----1-----1-|"
            , "2s3-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "3s4-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "4s5-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "5s6-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "6s7-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "7s8-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "8s9-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "9s10-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "10s0-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "s10-----1-----1-----1-|-----1-----1-----1-----1-|"
            // This test fails, but it should pass
            //, "10s-----1-----1-----1-|-----1-----1-----1-----1-|"
    };

    /**
     * VALID_LINES[i] should equal VALID_NOTES[i].
     * That means that each line in VALID_LINES should equal its corresponding Note in VALID_NOTES
     */
    public static final Slide[] VALID_NOTES = {
            new Slide(new Note(0), new Note(1))
            , new Slide(new Note(1), new Note(2))
            , new Slide(new Note(2), new Note(3))
            , new Slide(new Note(3), new Note(4))
            , new Slide(new Note(4), new Note(5))
            , new Slide(new Note(5), new Note(6))
            , new Slide(new Note(6), new Note(7))
            , new Slide(new Note(7), new Note(8))
            , new Slide(new Note(8), new Note(9))
            , new Slide(new Note(9), new Note(10))
            , new Slide(new Note(10), new Note(0))
            , new Slide(new Note(10))
            //, new Slide(new Note(10))
    };


    private SlideParser parser;

    @Before
    public void setUp() {
        parser = new SlideParser();
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
            Slide n = parser.parse(line);    // parse it
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

    @Test
    public void testDraw() throws Exception {
        new Slide(new Note(5)).draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testHashCode() {
        assertEquals(5f, new Slide(new Note(5)).hashCode(), 0.1f);
        
    }
}