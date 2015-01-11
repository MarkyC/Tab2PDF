package ca.yorku.cse2311.tab2pdf;


import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfHelper {

    /**
     * Appends a number of blank lines to the end of a paragraph.
     *
     * @param paragraph The paragraph the lines will be inserted
     * @param number    The number of lines to append
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(""));
        }
    }

    /**
     * Creates a circle at the specified coordinates.
     *
     * @param x      The x position of the circle
     * @param y      The y position of the circle
     * @param radius The radius of the circle
     * @param filled True if the circle is filled
     * @param writer Pdf writer for the document
     */
    public static void circle(float x, float y, float radius, boolean filled, PdfWriter writer) {
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
     * Creates a line between the specified points.
     *
     * @param x1     X coordinate of the first point
     * @param y1     Y coordinate of the first point
     * @param x2     X coordinate of the second point
     * @param y2     Y coordinate of the second point
     * @param width  Thickness of the line
     * @param writer Pdf writer for the document
     */
    public static void line(float x1, float y1, float x2, float y2, float width, PdfWriter writer) {
        PdfContentByte canvas = writer.getDirectContent();

        canvas.saveState();
        canvas.setColorStroke(GrayColor.BLACK);

        canvas.setLineWidth(width);

        canvas.moveTo(x1, y1);
        canvas.lineTo(x2, y2);

        canvas.stroke();

        canvas.restoreState();

    }
}
