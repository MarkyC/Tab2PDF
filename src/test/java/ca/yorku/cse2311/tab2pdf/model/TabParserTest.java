package ca.yorku.cse2311.tab2pdf.model;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TabParserTest
 *
 * Tests TabParser.
 *
 * @author Marco
 * @since 2015-02-03
 */
public class TabParserTest {

    public static final String[] LINES = {
            "|-------------------------|-------------------------|"
            /*, "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|---2-----2-----2-----2---|---2-----2-----2-----2---|"
            , "|-2-----2-----2-----2-----|-2-----2-----2-----2-----|"
            , "|-0-----------------------|-------------------------|"
            , "|-------------------------|-3-----------------------|"
            , ""
            , "|-------------------------|-------------------------|"
            , "|-----1-----1-----3-----3-|-----3-----1-----0-----0-|"
            , "|---2-----2-----3-----3---|---1-----2-----2-----1---|"
            , "|-3-----3-----3-----3-----|-2-----2-----2-----0-----|"
            , "|-------------5-----------|-------------------------|"
            , "|-1-----------------------|-0-----------0-----------|"*/
    };

    // Not to sure what this is...
    public static final String[] NORMALIZED_LINES = {
            "|-------------------------|-------------------------|"
            /*, "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|---2-----2-----2-----2---|---2-----2-----2-----2---|"
            , "|-2-----2-----2-----2-----|-2-----2-----2-----2-----|"
            , "|-0-----------------------|-------------------------|"
            , "|-------------------------|-3-----------------------|"
            , "|-------------------------|-------------------------|"
            , "|-----1-----1-----3-----3-|-----3-----1-----0-----0-|"
            , "|---2-----2-----3-----3---|---1-----2-----2-----1---|"
            , "|-3-----3-----3-----3-----|-2-----2-----2-----0-----|"
            , "|-------------5-----------|-------------------------|"
            , "|-1-----------------------|-0-----------0-----------|"*/
    };

    private TabParser parser;

    @Before
    public void setUp() {

        parser = new TabParser();
    }

    @Test
    public void testParse() {

        // Not yet!
    }

    /**
     * Tests parseLine() with valid input
     */
    @Test
    public void testParseLine() throws Exception {

        for (String line : LINES) {  // Go through all the valid lines

            // This will hold line that we converted back to tab using the ITabNotations toString()
            String parsedLine = "";

            for (ITabNotation note : parser.parseLine(line)) {  // Go through all the parsed tokens (notes)
                parsedLine += note.toString();  // Convert token back to tab notation
            }

            // the line the parser parsed and the original line better be the same!
            assertEquals(line, parsedLine);
        }

    }
}
