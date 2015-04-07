package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;

public class SubtitleTest {

    public static final String SUBTITLE_LINE = "SUBTITLE=Ludwig van Beethoven";

    private final static String RAW_SUBTITLE = "Ludwig van Beethoven";

    private final static Subtitle SUBTITLE = new Subtitle(RAW_SUBTITLE);

    @Test
    public void testGetValue() throws Exception {
        assertEquals(RAW_SUBTITLE, SUBTITLE.getValue());
    }

    @Test
    public void testDraw() throws Exception {
        SUBTITLE.draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        assertEquals(0, SUBTITLE.leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        assertEquals(0, SUBTITLE.rightPadding());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(SUBTITLE.toString().length(), SUBTITLE.size());
    }

    @Test
    public void testEquals() throws Exception {

        // Assert our test title is the same as the one given by TabParser
        List<String> l = new ArrayList<>();
        l.add(SUBTITLE_LINE);
        assertEquals(TabParser.getSubtitle(l), SUBTITLE);

        // Assert default titles are always the same
        assertEquals(new Subtitle(), new Subtitle());
    }

    @Test
    public void testHashCode() {
        assertEquals(new Subtitle(RAW_SUBTITLE).hashCode(), SUBTITLE.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(SUBTITLE_LINE, SUBTITLE.toString());
    }

}