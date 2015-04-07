package ca.yorku.cse2311.tab2pdf.model;

import junit.framework.TestCase;

import org.junit.Test;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

public class SquareNoteTest extends TestCase {
    
    public static final int RAW_NOTE = 5;
    
    public static final Note NOTE = new Note(RAW_NOTE);

    @Test
    public void testGetNote() throws Exception {
        assertEquals(NOTE, new SquareNote(RAW_NOTE).getNote());
    }

    @Test
    public void testDraw() throws Exception {
        PdfHelper h = new PdfHelper(1, 1);
        h.getDocument().open();
        new SquareNote(RAW_NOTE).draw(h, 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        assertEquals(0, new SquareNote(RAW_NOTE).leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        assertEquals(2, new SquareNote(RAW_NOTE).rightPadding());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(3, new SquareNote(RAW_NOTE).size());
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(new SquareNote(RAW_NOTE), new SquareNote(RAW_NOTE));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(5f, new SquareNote(RAW_NOTE).hashCode(), 0.1f);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("<5>", new SquareNote(RAW_NOTE).toString());
    }
}