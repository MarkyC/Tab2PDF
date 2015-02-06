package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.model.PullOff;
import ca.yorku.cse2311.tab2pdf.model.Slide;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

/**
 * The class implements a set of methods to draw a stave, thin/thick lines, circles,
 * and combinations of lines and circles
 *
 * @author Team 1
 *
 */

public class PdfHelper {

    /**
     * Space between the lines of the stave in pixels
     * Set LINE_SPACE to other value if needed
     */
    private static final int LINE_SPACE = 7;

    /**
     * Width of one stave in pixels
     * Determined based on LINE_SPACE
     */
    private static final int STAVE_WIDTH = LINE_SPACE * 5;

    private static final float LINE_WIDTH = 0.6f;

    /**
     * Size of the digits in stave
     */
    private static final int DIGIT_SIZE = (int) (LINE_SPACE * 1.5);

    /**
     * TODO: Needs to be replaced with the ability to scale vertical spaceing
     *
     * @return Gets separation between lines
     */
    public static int getLineSpace() {
        return LINE_SPACE;
    }

    /**
     * Draws a circle at the specified coordinates.
     *
     * @param x      The x position of the circle
     * @param y      The y position of the circle
     * @param radius The radius of the circle
     * @param filled True if the circle is filled
     * @param writer Pdf writer for the document
     */
    private static void circle(float x, float y, float radius, boolean filled, PdfWriter writer) {

        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.setColorStroke(GrayColor.BLACK);
        canvas.setColorFill(GrayColor.BLACK);
        canvas.circle(x, y, radius);

        if (filled)
            canvas.fillStroke();
        else
            canvas.stroke();

        canvas.restoreState();
    }

    /**
     * Draws a line between the specified points.
     *
     * @param x1     X coordinate of the first point
     * @param y1     Y coordinate of the first point
     * @param x2     X coordinate of the second point
     * @param y2     Y coordinate of the second point
     * @param width  Thickness of the line
     * @param writer Pdf writer for the document
     */
    private static void line(float x1, float y1, float x2, float y2, float width, PdfWriter writer) {

        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.setColorStroke(GrayColor.BLACK);

        canvas.setLineWidth(width);

        canvas.moveTo(x1, y1);
        canvas.lineTo(x2, y2);

        canvas.stroke();

        canvas.restoreState();
    }

    /**
     * Draws a horizontal arc between the specified points with a specified height.
     *
     * @param x1     X coordinate of the first point
     * @param y1     Y coordinate of the first point
     * @param x2     X coordinate of the second point
     * @param y2     Y coordinate of the second point
     * @param width  Thickness of the line
     * @param height Height of the Arc
     * @param writer Pdf writer for the document
     */
    private static void arc(float x1, float y1, float x2, float y2, float width, float height, PdfWriter writer) {

        float leftHandleX = x1 - (x1 - x2) / 4;
        float leftHandleY = (y1 + y2) / 2 + height;
        float rightHandleX = x2 + (x1 - x2) / 4;
        float rightHandleY = (y1 + y2) / 2 + height;


        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.setColorStroke(GrayColor.BLACK);

        canvas.setLineWidth(width);

        canvas.moveTo(x1, y1);
        //Control Point 1, Control Point 2, End Point
        canvas.curveTo(leftHandleX, leftHandleY, rightHandleX, rightHandleY, x2, y2);

        canvas.stroke();

        canvas.restoreState();
    }

    /**
     * Determines the Y coordinate of the line to draw based on the stave number
     *
     * @param staveNumber the number of stave to work with
     * @return the Y coordinate of the line to draw
     */
    public static int determineYCoordinate(int staveNumber) {

        final int STAVE_SPACE = PdfHelper.LINE_SPACE * 10; //Space between the staves
        int yCoordinate = 700 - staveNumber * STAVE_SPACE;

        return yCoordinate;
    }



    /**
     * Draws one stave consisting of 6 horizontal lines
     *
     * @param staveNumber the number of stave to draw (top stave is #1)
     * @param writer      Pdf writer for the document
     */
    public static void stave(int staveNumber, PdfWriter writer) {

        int yCoordinate = determineYCoordinate(staveNumber);

        for (int i = 0; i < 6; i++) {

            PdfHelper.line(0, yCoordinate, 600, yCoordinate, .5f, writer);
            yCoordinate += PdfHelper.LINE_SPACE;
        }
    }

    /**
     * Draws a thin vertical line with the length of one stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     * @param writer      Pdf writer for the document
     */
    public static void thinLine(int staveNumber, int xCoordinate, PdfWriter writer) {

        int yCoordinate = determineYCoordinate(staveNumber);
        PdfHelper.line(xCoordinate - 1, yCoordinate, xCoordinate - 1, yCoordinate + PdfHelper.STAVE_WIDTH, .5f, writer);
    }

    /**
     * Draws a thick vertical line with the length of one stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     * @param writer      Pdf writer for the document
     */
    public static void thickLine(int staveNumber, int xCoordinate, PdfWriter writer) {

        int yCoordinate = determineYCoordinate(staveNumber);
        PdfHelper.line(xCoordinate, yCoordinate, xCoordinate, yCoordinate + PdfHelper.STAVE_WIDTH, 2, writer);
    }

    /**
     * Draws two filled circles on the middle lines of the given stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the circles
     * @param writer      Pdf writer for the document
     */
    public static void filledCircles(int staveNumber, int xCoordinate, PdfWriter writer) {

        int yCoordinate = determineYCoordinate(staveNumber) + 2 * PdfHelper.LINE_SPACE;
        PdfHelper.circle(xCoordinate, yCoordinate, 1.5f, true, writer);
        yCoordinate += PdfHelper.LINE_SPACE;
        PdfHelper.circle(xCoordinate, yCoordinate, 1.5f, true, writer);

    }

    /**
     * Draws a combination consisting of
     * a thick line, a thin line, and two filled circles
     * (from left to right)
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the leftmost element of the combination
     * @param writer      Pdf writer for the document
     */
    public static void linesCircles(int staveNumber, int xCoordinate, PdfWriter writer) {

        thickLine(staveNumber, xCoordinate, writer);
        thinLine(staveNumber, xCoordinate + 3, writer);
        filledCircles(staveNumber, xCoordinate + 6, writer);

    }

    /**
     * Draws a combination consisting of
     * two filled circles, a thin line, and a thick line
     * (from left to right)
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the leftmost element of the combination
     * @param writer      Pdf writer for the document
     */
    public static void circlesLines(int staveNumber, int xCoordinate, PdfWriter writer) {

        filledCircles(staveNumber, xCoordinate - 6, writer);
        thinLine(staveNumber, xCoordinate - 3, writer);
        thickLine(staveNumber, xCoordinate, writer);

    }

    /**
     * Creates a combination consisting of
     * circlesThinThick() and thickThinCircles() back to back
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param writer      Pdf writer for the document
     */
    public static void doubleFigure(int staveNumber, int xCoordinate, PdfWriter writer) {

        circlesLines(staveNumber, xCoordinate, writer);
        linesCircles(staveNumber, xCoordinate, writer);
    }

    /**
     *
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param writer      Pdf writer for the document
     */
    private static void blankSpace(int staveNumber, int lineNumber, int xCoordinate, int digitWidth, PdfWriter writer) {

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * PdfHelper.LINE_SPACE;

        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.setColorStroke(GrayColor.WHITE);

        canvas.setLineWidth(5);

        canvas.moveTo(xCoordinate - digitWidth, yCoordinate);
        canvas.lineTo(xCoordinate + digitWidth + 1, yCoordinate);

        canvas.stroke();

        canvas.restoreState();
    }

    /**
     *
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param digit       the digit to be printed
     * @param writer      Pdf writer for the document
     * @throws IOException
     * @throws DocumentException
     */
    public static void drawDigit(int staveNumber, int lineNumber, int xCoordinate, int digit, PdfWriter writer) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', DIGIT_SIZE);
        int digitRadius;
        if (9 < digit) {
            digitRadius = (int) font.getWidthPoint(' ', DIGIT_SIZE * (int) (Math.log10(digit) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', DIGIT_SIZE);
        }
        //Clears a space for the digit and determines the Y coordinate of the digit to be printed
        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * PdfHelper.LINE_SPACE;
        blankSpace(staveNumber, lineNumber, xCoordinate, digitRadius, writer);
        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.beginText();
        canvas.moveText(xCoordinate - digitRadius, yCoordinate - fontHeight - 1);
        canvas.setFontAndSize(font, DIGIT_SIZE);
        canvas.showText(Integer.toString(digit));
        canvas.endText();
        canvas.restoreState();
    }

    /**
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param slide       the slide to be printed
     * @param writer      Pdf writer for the document
     * @throws IOException
     * @throws DocumentException
     */
    public static void drawSlide(int staveNumber, int lineNumber, int xCoordinate, Slide slide, PdfWriter writer) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', DIGIT_SIZE);

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * PdfHelper.LINE_SPACE;

        if (slide.getStart().getNote() != 0) {
            drawDigit(staveNumber, lineNumber, xCoordinate - fontHeight * 2, slide.getStart().getNote(), writer);
        }

        if (slide.getEnd().getNote() != 0) {
            drawDigit(staveNumber, lineNumber, xCoordinate + fontHeight * 2, slide.getEnd().getNote(), writer);
        }
        float xlen = 1.4f;
        float ylen = 1.2f;
        line(xCoordinate - fontHeight * xlen, yCoordinate - fontHeight * ylen, xCoordinate + fontHeight * xlen, yCoordinate + fontHeight * ylen, LINE_WIDTH, writer);

    }

    public static void drawPull(int staveNumber, int lineNumber, int xCoordinate, PullOff pullOff, PdfWriter writer,
                                int oldStave, int oldLine, int oldXCoordinate, String oldString) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', DIGIT_SIZE);
        int digitRadius;
        if (9 < pullOff.getEnd().getNote()) {
            digitRadius = (int) font.getWidthPoint(' ', DIGIT_SIZE * (int) (Math.log10(pullOff.getEnd().getNote()) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', DIGIT_SIZE);
        }

        int oldDigitRadius = (int) font.getWidthPoint(' ', DIGIT_SIZE * oldString.length()); //compensate for larger than 1 length

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * PdfHelper.LINE_SPACE;
        int oldyCoordinate = determineYCoordinate(oldStave) + (6 - oldLine) * PdfHelper.LINE_SPACE;


        drawDigit(staveNumber, lineNumber, xCoordinate - fontHeight * 2, pullOff.getEnd().getNote(), writer);

        arc(xCoordinate - digitRadius * 3.0f, yCoordinate + 3, oldXCoordinate + oldDigitRadius * 1.5f, oldyCoordinate + 3, LINE_WIDTH, 2.0f, writer);


    }
}
