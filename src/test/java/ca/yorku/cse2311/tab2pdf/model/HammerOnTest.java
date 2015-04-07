package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;

public class HammerOnTest {
    
    public static final int RAW_NOTE = 5;

    public static final Note NOTE = new Note(RAW_NOTE);

    @Test
    public void testGetStart() throws Exception {
        assertEquals(HammerOn.EMPTY_NOTE, new HammerOn(NOTE).getStart());
        assertEquals(NOTE, new HammerOn(NOTE, NOTE).getStart());
    }

    @Test
    public void testGetEnd() throws Exception {
        assertEquals(NOTE, new HammerOn(NOTE, NOTE).getEnd());
    }

    @Test
    public void testDrawLong() throws Exception {
        
        // drawLong needs an open document to work
        PdfHelper h = new PdfHelper(1, 1);
        h.getDocument().open();
        new HammerOn(NOTE).drawLong(h, 1, 1, 1, 1, 1, 1, "oldString");
    }

    @Test
    public void testDraw() throws Exception {
        new HammerOn(NOTE).draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testEquals() throws Exception {
        
        assertEquals(new HammerOn(NOTE), new HammerOn(NOTE));
        assertEquals(new HammerOn(NOTE, NOTE), new HammerOn(NOTE, NOTE));
    }

    @Test
    public void testHashCode() {
        assertEquals(5.0f, new HammerOn(NOTE).hashCode(), 0.1f);
        
    }
}