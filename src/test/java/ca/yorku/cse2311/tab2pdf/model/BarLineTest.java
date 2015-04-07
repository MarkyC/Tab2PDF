package ca.yorku.cse2311.tab2pdf.model;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

public class BarLineTest extends TestCase {

    public static final int RAW_NOTE = 5;

    public static final Note NOTE = new Note(RAW_NOTE);
    
    BarLine line = new BarLine();

    @Test
    public void testGetLine() throws Exception {
        assertEquals(new ArrayList<ITabNotation>(), line.getLine());
        assertEquals(0, line.getLine().size());
    }

    @Test
    public void testAddNote() throws Exception {
        line.addNote(NOTE);
        assertEquals(1, line.getLine().size());
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(new BarLine(), new BarLine());
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(1f, new BarLine().hashCode(), 0.1f);
    }
}