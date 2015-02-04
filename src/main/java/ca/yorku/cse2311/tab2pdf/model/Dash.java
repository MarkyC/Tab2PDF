package ca.yorku.cse2311.tab2pdf.model;

/**
 * Dash
 *
 * Represents a dash ('-') in musical notation
 *
 * @author Marco
 * @since 2015-01-12
 */
public class Dash implements IMusicalNotation {

    @Override
    public String toString() {

        return "-";
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Dash;    // All Spaces are the same for now
    }
}
