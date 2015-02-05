package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody Atto on 25/01/2015.
 */
public class Slide implements ITabNotation {
    private final int slideStart;
    private final int slideEnd;

    public Slide(int slideStart, int slideEnd) {
        this.slideStart = slideStart;
        this.slideEnd = slideEnd;
    }

    public int getSlideStart() {
        return slideStart;
    }

    public int getSlideEnd() {
        return slideEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slide slide = (Slide) o;

        if (slideEnd != slide.slideEnd) return false;
        if (slideStart != slide.slideStart) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = slideStart;
        result = 31 * result + slideEnd;
        return result;
    }

    @Override
    public String toString() {
        return slideStart + "s" + slideEnd;
    }
}
