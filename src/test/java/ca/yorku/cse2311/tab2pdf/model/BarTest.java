package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BarTest {

    @Test
    public void testNumLines() throws Exception {
        assertEquals(6, new Bar().getNumLines());      
    }
    
    @Test
    public void testGetLines() throws Exception {
        assertEquals(0, new Bar().getLines().size());
        try {
            
            // This should fail since the Bar only has 6 lines
            new Bar().getLine(7).getLine().size();
            
            fail(); // fail if no Exception was thrown
            
        } catch (IndexOutOfBoundsException e) {
            // this should be thrown!
        }
    }

    @Test
    public void testSetGetBarLength() throws Exception {
        
        Bar b = new Bar();
        b.setBarLength(4);
        assertEquals(4, b.getLength());
    }

    @Test
    public void testSetGetBarRepeat() {
        Bar b = new Bar();
        b.setBarRepeat(4);
        b.setBeginRepeat(true);
        b.setEndRepeat(true);
        assertEquals(4, b.getRepeat());
        assertEquals(true, b.getBeginRepeat());
        assertEquals(true, b.getEndRepeat());
    }

    @Test
    public void testEquals() throws Exception {
        
        assertEquals(new Bar(), new Bar());
        assertEquals(new Bar(), new Bar(6));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(37f, new Bar().hashCode(), 0.1f);
    }

    @Test
    public void testToString() throws Exception {

    }
}