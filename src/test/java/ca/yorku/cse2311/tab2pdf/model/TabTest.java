package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TabTest {
    
    public static final Title TITLE = new Title("Moonlight Sonata");
    public static final Subtitle SUBTITLE = new Subtitle("Ludwig van Beethoven");
    public static final Spacing SPACING = new Spacing(5);
    public static final Scaling SCALING = new Scaling(5);
    public Tab tab;
    
    @Before
    public void setup() {
        tab = new Tab(TITLE, SUBTITLE, SPACING, SCALING);
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals(TITLE, tab.getTitle());
        
        // a fresh tab should have the default value
        assertEquals(Title.DEFAULT_TITLE, new Tab().getTitle().getValue());
    }

    @Test
    public void testSetTitle() throws Exception {
        assertEquals(TITLE, tab.getTitle());    // original
        tab.setTitle("test");                   // change it
        assertEquals("test", tab.getTitle().getValue());    // this should have changed
    }

    @Test
    public void testGetSubtitle() throws Exception {
        assertEquals(SUBTITLE, tab.getSubtitle());

        // a fresh tab should have the default value
        assertEquals(Subtitle.DEFAULT_SUBTITLE, new Tab().getSubtitle().getValue());
    }

    @Test
    public void testGetSetSubtitle() throws Exception {
        assertEquals(SUBTITLE, tab.getSubtitle());      // original
        tab.setSubtitle("test");                        // change it
        assertEquals("test", tab.getSubtitle().getValue());    // this should have changed
    }

    @Test
    public void testGetSpacing() throws Exception {
        assertEquals(SPACING, tab.getSpacing());

        // a fresh tab should have the default value
        assertEquals(new Spacing().getSpacing(), new Tab().getSpacing().getSpacing(), 0.1f);
    }

    @Test
    public void testSetSpacing() throws Exception {
        assertEquals(SPACING, tab.getSpacing());    // original
        tab.setSpacing(5);                          // change it
        assertEquals(5f, tab.getSpacing().getSpacing(), 0.1f);  // this should have changed
    }


    @Test
    public void testGetScaling() throws Exception {
        assertEquals(SCALING, tab.getScaling());
        
        // a fresh tab should have the default value
        assertEquals(new Scaling().getScaling(), new Tab().getScaling().getScaling(), 0.1f);
    }

    @Test
    public void testSetScaling() throws Exception {
        assertEquals(SCALING, tab.getScaling());    // original
        tab.setScaling(5);                          // change it
        assertEquals(5f, tab.getScaling().getScaling(), 0.1f);  // this should have changed
    }

    @Test
    public void testGetBars() throws Exception {
        assertEquals(0, tab.getBars().size());
    }

    @Test
    public void testToString() throws Exception {
        
        // this tab should output the empty list's toString
        assertEquals("[]", tab.toString());
    }
}