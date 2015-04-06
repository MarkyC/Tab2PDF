package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;

public class TitleTest {

    public static final String TITLE_LINE = "TITLE=Moonlight Sonata";
    
    private final static String RAW_TITLE = "Moonlight Sonata";

    private final static Title TITLE = new Title(RAW_TITLE);

    @Test
    public void testGetValue() throws Exception {
        assertEquals("Moonlight Sonata", TITLE.getValue());
    }

    @Test
    public void testDraw() throws Exception {
        TITLE.draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        assertEquals(0, TITLE.leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        assertEquals(0, TITLE.rightPadding());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(TITLE.toString().length(), TITLE.size());
    }

    @Test
    public void testEquals() throws Exception {

        // Assert our test title is the same as the one given by TabParser
        List<String> l = new ArrayList<>();
        l.add(TITLE_LINE);
        assertEquals(TabParser.getTitle(l), TITLE);
        
        // Assert default titles are always the same
        assertEquals(new Title(), new Title());
    }
    
    @Test
    public void testHashCode() {
        assertEquals(new Title(RAW_TITLE).hashCode(), TITLE.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(TITLE_LINE, TITLE.toString());
    }
}