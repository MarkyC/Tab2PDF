package ca.yorku.cse2311.tab2pdf.parser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TitleParserTest {

    public static final String[] VALID_TITLES = {
            "TITLE=Moonlight Sonata"
            , "TITLE=Remembering Rain"
            , "Title=Remembering Rain"
            , "TitlE=Remembering"
            , "title=A"
            , "TiTle=Long Title Here One Or More Words"
    };

    public static final String[] CORRECT_TITLES = {
            "Moonlight Sonata"
            , "Remembering Rain"
            , "Remembering Rain"
            , "Remembering"
            , "A"
            , "Long Title Here One Or More Words"
    };

    public static final String[] INVALID_TITLES = {
            "TITLE="
            , "T=Remembering Rain"
            , "SUBTITLE=Remembering Rain"
    };

    public TitleParser parser;

    @Before
    public void setUp() {

        parser = new TitleParser();
    }

    @Test
    public void testParseInvalidLines() {

        for (String line : INVALID_TITLES) {
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

        for (int i = 0; i < VALID_TITLES.length; ++i) {  // Go through each valid line,

            String line = VALID_TITLES[i];          // grab the current line
            String title = parser.parse(line);      // parse it
            assertEquals(CORRECT_TITLES[i], title); // ensure it equals the Title in the corresponding CORRECT_TITLES index
        }
    }
}