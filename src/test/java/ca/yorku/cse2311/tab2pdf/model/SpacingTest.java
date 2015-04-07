package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;

public class SpacingTest {

    public static final String SPACING_LINE = "SPACING=5.0";

    private final static int RAW_SPACING = 5;

    private final static Spacing SPACING = new Spacing(RAW_SPACING);

    @Test
    public void testGetValue() throws Exception {
        assertEquals(RAW_SPACING, SPACING.getSpacing(), 0.1f);
    }

    @Test
    public void testDraw() throws Exception {
        SPACING.draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        assertEquals(0, SPACING.leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        assertEquals(0, SPACING.rightPadding());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(SPACING.toString().length(), SPACING.size());
    }

    @Test
    public void testEquals() throws Exception {

        // Assert our test title is the same as the one given by TabParser
        List<String> l = new ArrayList<>();
        l.add(SPACING_LINE);
        assertEquals(TabParser.getSpacing(l), SPACING);

        // Assert default titles are always the same
        assertEquals(new Spacing(), new Spacing());
    }

    @Test
    public void testHashCode() {
        assertEquals(new Spacing(RAW_SPACING).hashCode(), SPACING.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(SPACING_LINE, SPACING.toString());
    }

}