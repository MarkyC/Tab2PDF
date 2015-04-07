package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;

public class ScalingTest {

    public static final String SCALING_LINE = "SCALING=5.0";

    private final static int RAW_SCALING = 5;

    private final static Scaling SCALING = new Scaling(RAW_SCALING);

    @Test
    public void testGetValue() throws Exception {
        assertEquals(RAW_SCALING, SCALING.getScaling(), 0.1f);
    }

    @Test
    public void testDraw() throws Exception {
        SCALING.draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        assertEquals(0, SCALING.leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        assertEquals(0, SCALING.rightPadding());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(SCALING.toString().length(), SCALING.size());
    }

    @Test
    public void testEquals() throws Exception {

        // Assert default titles are always the same
        assertEquals(new Scaling(), new Scaling());
    }

    @Test
    public void testHashCode() {
        assertEquals(new Scaling(RAW_SCALING).hashCode(), SCALING.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(SCALING_LINE, SCALING.toString());
    }
}