package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class SpacingParserTest {

    /**
     * None of these should be parsed
     */
    public static final String[] INVALID_LINES = {
            "SPACING="
            , "SPACING=A"
            , "|--"
            , "0--|"
            , "S=1"
    };

    /**
     * All of these should be parsed
     */
    public static final String[] VALID_LINES = {
            "SPACING=1",
            "SPacING=2",
            "SPACING=2.10 asd",
            "SPACING=2.5",
    };

    /**
     * All of these should be parsed
     */
    public static final double[] CORRECT_SPACEINGS = {
            1,
            2,
            2.10,
            2.5,
    };

    private SpacingParser parser;

    @Before
    public void setUp() {
        parser = new SpacingParser();
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

            String line = VALID_LINES[i];          // grab the current line
            double spaceing = parser.parse(line).getSpacing();      // parse it
            assertEquals(CORRECT_SPACEINGS[i], spaceing, 0.001); // ensure it equals the Title in the corresponding CORRECT_TITLES index
        }
    }

}
