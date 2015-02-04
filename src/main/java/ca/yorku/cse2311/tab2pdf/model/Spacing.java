package ca.yorku.cse2311.tab2pdf.model;

/**
 * Spacing
 * <p/>
 * Repesents the spacing of the tab (ie: SPACING=4.4)
 *
 * @author Brody Atto, Marco Cirillo
 * @since 2015-01-12
 */
public class Spacing implements ITabNotation {

    private final String spacing;

    public Spacing(String spacing) {

        this.spacing = spacing;
    }

    public String getSpacing() {

        return spacing;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spacing note1 = (Spacing) o;

        if (spacing != null ? !spacing.equals(note1.spacing) : note1.spacing != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return spacing != null ? spacing.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "SPACING=" + spacing;
    }
}
