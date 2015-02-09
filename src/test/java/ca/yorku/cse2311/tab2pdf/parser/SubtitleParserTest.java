package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.parser.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SubtitleParserTest {

    public static final String[] VALID_SUBTITLES = {
            "SUBTITLE=Ludwig van Beethoven"
            , "SUBTITLE=Jim Matheos"
            , "SUBTITLE=Jim Matheos Long Name Here"
            , "Subtitle=Jim Matheos"
            , "subtitlE=Jim Matheos"
            , "subtitle=Jim"
            
    };

    public static final String[] CORRECT_SUBTITLES = {
            "Ludwig van Beethoven"
            , "Jim Matheos"
            , "Jim Matheos Long Name Here"
            , "Jim Matheos"
            , "Jim Matheos"
            , "Jim"
    };

    public static final String[] INVALID_SUBTITLES = {
            "SUBTITLE="
            , "TITLE="
            , "TITLE=Something"
            , "T=Remembering Rain"
    };

    public SubtitleParser parser;

    @Before
    public void setUp() {

        parser = new SubtitleParser();
    }

    @Test
    public void testParseInvalidLines() {

        for (String line : INVALID_SUBTITLES) {
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

        for (int i = 0; i < VALID_SUBTITLES.length; ++i) {  // Go through each valid line,

            String line = VALID_SUBTITLES[i];           // grab the current line
            String title = parser.parse(line).getValue();          // parse it
            assertEquals(CORRECT_SUBTITLES[i], title);  // ensure it equals the Subtitle in the corresponding CORRECT_SUBTITLES index
        }
    }
    
}