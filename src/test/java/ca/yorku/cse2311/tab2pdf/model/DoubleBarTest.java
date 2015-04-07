package ca.yorku.cse2311.tab2pdf.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoubleBarTest {
    final int testSize = 100;
    DoubleBar[] doubleBarGet;
    DoubleBar[] doubleBarSet;
    DoubleBar[] doubleBar1;
    DoubleBar[] doubleBar2;
    int[] getRepeatG;
    boolean[] getBeginG;
    boolean[] getEnd;
    int[] getRepeatS;
    boolean[] setBegin;
    boolean[] setEnd;

    @Before
    public void setup() {
        doubleBarGet = new DoubleBar[testSize];
        doubleBarSet = new DoubleBar[testSize];
        doubleBar1 = new DoubleBar[testSize];
        doubleBar2 = new DoubleBar[testSize];

        getRepeatG = new int[testSize];
        getBeginG = new boolean[testSize];
        getEnd = new boolean[testSize];
        getRepeatS = new int[testSize];
        setBegin = new boolean[testSize];
        setEnd = new boolean[testSize];
        Random r = new Random();

        for (int i = 0; i < testSize; i++) {
            int tempInt = r.nextInt();
            boolean tempBool1 = r.nextBoolean();
            boolean tempBool2 = r.nextBoolean();

            //Setup get test
            doubleBarGet[i] = new DoubleBar(tempInt, tempBool1, tempBool2, false);
            getRepeatG[i] = tempInt;
            getBeginG[i] = tempBool1;
            getEnd[i] = tempBool2;

            //Setup set test
            tempInt = r.nextInt();
            tempBool1 = r.nextBoolean();
            tempBool2 = r.nextBoolean();

            doubleBarSet[i] = new DoubleBar(tempInt, false, false, false);
            getRepeatS[i] = tempInt;
            setBegin[i] = tempBool1;
            setEnd[i] = tempBool2;

            //Setup equals test
            tempInt = r.nextInt();
            tempBool1 = r.nextBoolean();
            tempBool2 = r.nextBoolean();

            doubleBar1[i] = new DoubleBar(tempInt, tempBool1, tempBool2, false);
            doubleBar2[i] = new DoubleBar(tempInt, tempBool1, tempBool2, false);
        }
    }

    @Test
    public void testGetRepeat() {
        assertEquals(1, new DoubleBar().getRepeat());
    }

    @Test
    public void testGetBeginRepeat() {
        for (int i = 0; i < testSize; i++) {
            assertEquals(doubleBarGet[i].getBeginRepeat(), getBeginG[i]);
        }
    }

    @Test
    public void testGetEndRepeat() {
        for (int i = 0; i < testSize; i++) {
            assertEquals(doubleBarGet[i].getEndRepeat(), getEnd[i]);
        }
    }

    @Test
    public void testSetBeginRepeat() {
        for (int i = 0; i < testSize; i++) {
            doubleBarSet[i].setBeginRepeat(setBegin[i]);
            assertEquals(doubleBarSet[i].getBeginRepeat(), setBegin[i]);
        }
    }

    @Test
    public void testSetEndRepeat() {
        for (int i = 0; i < testSize; i++) {
            doubleBarSet[i].setEndRepeat(setEnd[i]);
            assertEquals(doubleBarSet[i].getEndRepeat(), setEnd[i]);
        }
    }

    @Test
    public void testEquals() {
        for (int i = 0; i < testSize; i++) {
            assertTrue(doubleBar1[i].equals(doubleBar2[i]));
        }

        // Assert defaults are always the same
        assertEquals(new DoubleBar(), new DoubleBar());
    }

    @Test
    public void testDraw() throws Exception {
        new DoubleBar().draw(new PdfHelper(1, 1), 1, 1, 1);
    }

    @Test
    public void testLeftPadding() throws Exception {
        DoubleBar p0 = new DoubleBar();
        DoubleBar p1 = new DoubleBar(1, true, true, true);
        assertEquals(0, p0.leftPadding());
        assertEquals(1, p1.leftPadding());
    }

    @Test
    public void testRightPadding() throws Exception {
        DoubleBar p0 = new DoubleBar();
        DoubleBar p1 = new DoubleBar(1, true, true, true);
        DoubleBar p2 = new DoubleBar(1, false, false, false);
        assertEquals(1, p0.rightPadding());
        assertEquals(1, p1.rightPadding());
        assertEquals(0, p2.rightPadding());
    }

    @Test
    public void testSize() {
        assertEquals(3, new DoubleBar().size());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("||-", new DoubleBar().toString());
    }
}
