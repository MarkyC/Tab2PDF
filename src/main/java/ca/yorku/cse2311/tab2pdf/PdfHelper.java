package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.model.HammerOn;
import ca.yorku.cse2311.tab2pdf.model.PullOff;
import ca.yorku.cse2311.tab2pdf.model.Slide;
import ca.yorku.cse2311.tab2pdf.model.SquareNote;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The class implements a set of methods to draw a stave, thin/thick lines, circles,
 * and combinations of lines and circles
 *
 * @author Team 1
 *
 */

public class PdfHelper {

    public static final int DISTANCE_FROM_TOP = 650;

    private final PdfWriter writer;

    private final Document document;

    private final File file;

    /**
     * Space between the lines of the stave in pixels
     * Set lineSpace to other value if needed
     */
    private int lineSpace;

    /**
     * Width of one stave in pixels
     * Determined based on lineSpace
     */
    private int staveHeight;

    private float lineWidth;

    /**
     * Size of the digits in stave
     */
    private int digitSize;

    public PdfHelper(int lineSpace, float lineWidth) throws IOException, DocumentException {
        this(Files.createTempFile("tab2pdf", ".pdf").toFile(), lineSpace, lineWidth);
    }

    public PdfHelper(File output, int lineSpace, float lineWidth) throws IOException, DocumentException {
        this(new Document(), output, lineSpace, lineWidth);
    }


    public PdfHelper(Document document, File output, int lineSpace, float lineWidth) throws IOException, DocumentException {
        this.document = document;
        this.writer = PdfWriter.getInstance(document, Files.newOutputStream(output.toPath()));
        this.file = output;
        this.lineSpace = lineSpace + 5; // Add 5 to the user supplied lineSpace, or it would be too small
        this.staveHeight = (int) lineWidth;//lineSpace * 5; //18 + (int) lineWidth;//
        this.lineWidth = lineWidth;
        this.digitSize = (int)(staveHeight * 1.3);
    }

    public PdfWriter getWriter() {

        return writer;
    }

    public Document getDocument() {

        return document;
    }

    public int getLineSpace() {

        return lineSpace;
    }

    public File getFile() {

        return file;
    }

    public int getStaveHeight() {

        return staveHeight;
    }

    public float getLineWidth() {

        return lineWidth;
    }

    public int getDigitSize() {

        return digitSize;
    }

    /**
     * Draws a circle at the specified coordinates.
     *
     * @param x      The x position of the circle
     * @param y      The y position of the circle
     * @param radius The radius of the circle
     * @param filled True if the circle is filled
     */
    private void circle(float x, float y, float radius, boolean filled) {

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
     * Draws a diamond at the specified coordinates.
     *
     * @param x        The x position of the diamond
     * @param y        The y position of the diamond
     * @param radius   The radius of the circle
     * @param hollowed True if the circle is filled
     */
    private void diamond(float x, float y, float radius, boolean hollowed) {

        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();

        if (hollowed) {
            canvas.setColorStroke(GrayColor.WHITE);

            canvas.setLineWidth(radius);

            canvas.moveTo(x - radius, y);
            canvas.lineTo(x + radius, y);

            canvas.stroke();
        }

        canvas.setColorStroke(GrayColor.BLACK);

        canvas.setLineWidth(0.5f);

        canvas.moveTo(x - 0.175f, y + radius + 0.175f); //top
        canvas.lineTo(x + radius, y); //right
        canvas.lineTo(x, y - radius); //bottom
        canvas.lineTo(x - radius, y); //left
        canvas.lineTo(x, y + radius); //back to the top

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
     */
    private void line(float x1, float y1, float x2, float y2, float width) {

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
     */
    private void arc(float x1, float y1, float x2, float y2, float width, float height) {

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
    public int determineYCoordinate(int staveNumber) {

        final int STAVE_SPACE = getStaveHeight() * 10; //Space between the staves
        return DISTANCE_FROM_TOP - staveNumber * STAVE_SPACE;
    }

    /**
     * Draws one stave consisting of 6 horizontal lines
     *
     * @param staveNumber the number of stave to draw (top stave is #1)
     */
    public void stave(int staveNumber) {

        int yCoordinate = determineYCoordinate(staveNumber);

        for (int i = 0; i < 6; i++) {

            line(0, yCoordinate, 600, yCoordinate, .5f);
            yCoordinate += getStaveHeight();
        }
    }

    /**
     * Draws a thin vertical line with the length of one stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     */
    public void thinLine(int staveNumber, float xCoordinate) {

        int yCoordinate = determineYCoordinate(staveNumber);

        line(xCoordinate, yCoordinate, xCoordinate, yCoordinate + getStaveHeight() * 5, .5f);
    }

    /**
     * Draws a thick vertical line with the length of one stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     */
    public void thickLine(int staveNumber, float xCoordinate) {

        int yCoordinate = determineYCoordinate(staveNumber);
        line(xCoordinate, yCoordinate, xCoordinate, yCoordinate + getStaveHeight() * 5, 1.5f);
    }

    /**
     * Draws two filled circles on the middle lines of the given stave
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the circles
     */
    public void filledCircles(int staveNumber, float xCoordinate) {

        int yCoordinate = determineYCoordinate(staveNumber) + 2 * lineSpace;
        circle(xCoordinate, yCoordinate, 1f, true);
        yCoordinate += lineSpace;
        circle(xCoordinate, yCoordinate, 1f, true);

    }

    /**
     * Draws a combination consisting of
     * a thick line, a thin line, and two filled circles
     * (from left to right)
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the leftmost element of the combination
     */
    public void linesCircles(int staveNumber, int xCoordinate) {

        thickLine(staveNumber, xCoordinate);
        thinLine(staveNumber, xCoordinate + 3);
        filledCircles(staveNumber, xCoordinate + 6);

    }

    /**
     * Draws a combination consisting of
     * two filled circles, a thin line, and a thick line
     * (from left to right)
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the leftmost element of the combination
     */
    public void circlesLines(int staveNumber, int xCoordinate) {

        filledCircles(staveNumber, xCoordinate - 6);
        thinLine(staveNumber, xCoordinate - 3);
        thickLine(staveNumber, xCoordinate);

    }

    /**
     * Creates a combination consisting of
     * circlesThinThick() and thickThinCircles() back to back
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the middle of the combination
     */
    public void doubleFigure(int staveNumber, int xCoordinate) {

        circlesLines(staveNumber, xCoordinate);
        linesCircles(staveNumber, xCoordinate);
    }

    /**
     *
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     */
    private void blankSpace(int staveNumber, int lineNumber, float xCoordinate, int digitWidth) {

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();

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
     * @throws IOException
     * @throws DocumentException
     */
    public void drawDigit(int staveNumber, int lineNumber, float xCoordinate, int digit) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);
        int digitRadius;
        if (9 < digit) {
            digitRadius = (int) font.getWidthPoint(' ', digitSize * (int) (Math.log10(digit) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', digitSize);
        }
        //Clears a space for the digit and determines the Y coordinate of the digit to be printed
        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();
        blankSpace(staveNumber, lineNumber, xCoordinate, digitRadius);
        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.beginText();
        canvas.moveText(xCoordinate - digitRadius, yCoordinate - fontHeight - 1);
        canvas.setFontAndSize(font, digitSize);
        canvas.showText(Integer.toString(digit));
        canvas.endText();
        canvas.restoreState();
    }

    /**
     * Draws text to the pdf
     * Note: It does not remove the lines behind the text
     *
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param yCoordinate the Y coordinate of the middle of the combination
     * @param text        the text to be printed
     * @throws IOException
     * @throws DocumentException
     */
    public void drawText(float xCoordinate, int yCoordinate, String text, int size) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', size);

        int digitRadius = (int) font.getWidthPoint(' ', size * text.length()); //compensate for larger than 1 length

        //Print
        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.beginText();
        canvas.moveText(xCoordinate - digitRadius, yCoordinate - fontHeight - 1);

        canvas.setFontAndSize(font, size);
        canvas.showText(text);
        canvas.endText();
        canvas.restoreState();
    }

    /**
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param repeat       the repeat to be printed
     * @throws IOException
     * @throws DocumentException
     */
    public void drawRepeat(int staveNumber, int lineNumber, float xCoordinate, int repeat) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);

        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);

        String repeatMsg = "Repeat " + repeat + " times";

        xCoordinate -= repeatMsg.length() * fontHeight / 2;

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();

        drawText(xCoordinate, yCoordinate, repeatMsg, 6);
    }

    /**
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param slide       the slide to be printed
     * @throws IOException
     * @throws DocumentException
     */
    public void drawSlide(int staveNumber, int lineNumber, float xCoordinate, Slide slide) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);

        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();

        if (slide.getStart().getValue() != 0) {
            drawDigit(staveNumber, lineNumber, xCoordinate - fontHeight * 2, slide.getStart().getValue());
        }

        if (slide.getEnd().getValue() != 0) {
            drawDigit(staveNumber, lineNumber, xCoordinate + fontHeight * 2, slide.getEnd().getValue());
        }
        float xlen = 1.4f;
        float ylen = 1.2f;
        line(xCoordinate - fontHeight * xlen * 0.9f, yCoordinate - fontHeight * ylen, xCoordinate + fontHeight * xlen * 0.9f, yCoordinate + fontHeight * ylen, lineWidth / 10);

    }

    /**
     * @param staveNumber the number of stave to work with
     * @param lineNumber  the number of line to work with (from 1 to 6)
     * @param xCoordinate the X coordinate of the middle of the combination
     * @param squareNote  the squareNote to be printed
     * @throws IOException
     * @throws DocumentException
     */
    public void drawSquareNote(int staveNumber, int lineNumber, float xCoordinate, SquareNote squareNote) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);

        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);
        int digitRadius;
        if (9 < squareNote.getNote().getValue()) {
            digitRadius = (int) font.getWidthPoint(' ', digitSize * (int) (Math.log10(squareNote.getNote().getValue()) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', digitSize);
        }

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();

        drawDigit(staveNumber, lineNumber, xCoordinate, squareNote.getNote().getValue());

        diamond(xCoordinate + digitRadius * 2.5f + fontHeight, yCoordinate, fontHeight, true);

    }

    /**
     * Draw method for pull offs
     *
     * @param staveNumber           the number of the stave to work with
     * @param lineNumber            the number of the line to work with
     * @param xCoordinate           the X coordinate of the middle of right note
     * @param pullOff               the pull off note
     * @param connectingStave       the stave number of the previous connecting note
     * @param connectingLine        the line number of the previous connecting note
     * @param connectingXCoordinate the X coordinate of the previous connecting note
     * @param connectingNote        the old note
     * @throws DocumentException
     * @throws IOException
     */
    public void drawPull(int staveNumber, int lineNumber, float xCoordinate, PullOff pullOff,
                                int connectingStave, int connectingLine, float connectingXCoordinate, String connectingNote) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);
        int digitRadius;
        if (9 < pullOff.getEnd().getValue()) {
            digitRadius = (int) font.getWidthPoint(' ', digitSize * (int) (Math.log10(pullOff.getEnd().getValue()) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', digitSize);
        }

        int oldDigitRadius = (int) font.getWidthPoint(' ', digitSize * connectingNote.length()); //compensate for larger than 1 length

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();
        int oldyCoordinate = determineYCoordinate(connectingStave) + (6 - connectingLine) * getStaveHeight();


        //drawDigit(staveNumber, lineNumber, xCoordinate - fontHeight * 2, pullOff.getEnd().getValue(), writer);
        drawText(((xCoordinate + digitRadius * 2.0f) + connectingXCoordinate) / 2, yCoordinate + 8, "p", 6);

        arc(xCoordinate + digitRadius * 1.5f, yCoordinate + 3, connectingXCoordinate + oldDigitRadius * 1.5f, oldyCoordinate + 3, 1f, 2.0f);


    }

    /**
     * Draw method for pull offs
     * @param staveNumber the number of the stave to work with
     * @param lineNumber the number of the line to work with
     * @param xCoordinate the X coordinate of the middle of right note
     * @param hammerOn the hammer on note
     * @param connectingStave the stave number of the previous connecting note
     * @param connectingLine the line number of the previous connecting note
     * @param connectingXCoordinate the X coordinate of the previous connecting note
     * @param connectingNote the old note
     * @throws DocumentException
     * @throws IOException
     */
    public void drawHammer(int staveNumber, int lineNumber, float xCoordinate, HammerOn hammerOn,
                                  int connectingStave, int connectingLine, float connectingXCoordinate, String connectingNote) throws DocumentException, IOException {

        BaseFont font = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, BaseFont.EMBEDDED);
        //Distance from the middle of the digit to the digit border
        int fontHeight = (int) font.getWidthPoint(' ', digitSize);
        int digitRadius;
        if (9 < hammerOn.getEnd().getValue()) {
            digitRadius = (int) font.getWidthPoint(' ', digitSize * (int) (Math.log10(hammerOn.getEnd().getValue()) + 1)); //compensate for larger than 1 length
        } else {
            digitRadius = (int) font.getWidthPoint(' ', digitSize);
        }

        int oldDigitRadius = (int) font.getWidthPoint(' ', digitSize * connectingNote.length()); //compensate for larger than 1 length

        int yCoordinate = determineYCoordinate(staveNumber) + (6 - lineNumber) * getStaveHeight();
        int oldyCoordinate = determineYCoordinate(connectingStave) + (6 - connectingLine) * getStaveHeight();


        //drawDigit(staveNumber, lineNumber, xCoordinate + fontHeight / 4f, hammerOn.getEnd().getValue(), writer);
        drawText(((xCoordinate + digitRadius * 2.0f) + connectingXCoordinate) / 2, yCoordinate + 7, "h", 6);

        arc(xCoordinate + digitRadius * 1.5f, yCoordinate + 3, connectingXCoordinate + oldDigitRadius * 1.5f, oldyCoordinate + 3, 1f, 2.0f);


    }


    /**
     * Draws the start repeat bar
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     * @param spacing     the documents horizontal spacing constant
     */
    public void startRepeat(int staveNumber, float xCoordinate, float spacing) {

        thickLine(staveNumber, xCoordinate);
        thinLine(staveNumber, xCoordinate + spacing / 2f);
        filledCircles(staveNumber, xCoordinate + spacing * 1.1f);
    }


    /**
     * Draws the start repeat bar
     *
     * @param staveNumber the number of stave to work with
     * @param xCoordinate the X coordinate of the line
     * @param spacing     the documents horizontal spacing constant
     */
    public void endRepeat(int staveNumber, float xCoordinate, float spacing) {

        thickLine(staveNumber, xCoordinate);
        thinLine(staveNumber, xCoordinate - spacing / 2f);
        filledCircles(staveNumber, xCoordinate - spacing * 1.1f);
    }
}
