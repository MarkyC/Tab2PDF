package ca.yorku.cse2311.tab2pdf.model;

/**
 * Space
 *
 * Represents a space ('-') in musical notation
 *
 * @author Marco
 * @since 2015-01-12
 */
public class Space implements IMusicalNotation {

    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Space;    // All Spaces are the same for now
    }
}
