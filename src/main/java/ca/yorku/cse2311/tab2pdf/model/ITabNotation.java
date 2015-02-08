package ca.yorku.cse2311.tab2pdf.model;

/**
 * IMusicalNotation
 *
 * Interface to describe Musical Notation elements
 *
 * TODO: Repurpose this class to be more aimed at representing the musical notation, not parsing it.
 *
 * @author Marco
 * @since 2015-01-12
 */
public interface ITabNotation extends IDrawable {

    /**
     * Represents the number of spaces to be inserted to the left of the symbol
     *
     * @return number of characters to buffer on the left
     */
    public int leftPadding();

    /**
     * Represents the number of spaces to be inserted to the right of the symbol
     *
     * @return number of characters to buffer on the right
     */
    public int rightPadding();


    /**
     * Represents logical size the parser should treat the symbol as
     *
     * @return symbols logical size
     */
    public int size();
}
