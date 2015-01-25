package ca.yorku.cse2311.tab2pdf.model;

/**
 * Created by Brody Atto on 25/01/2015.
 */
public class Slide implements IMusicalNotation {
    private final String slide;

    public Slide(String slide) {
        this.slide = slide;
    }

    public String getSlide() {
        return slide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slide slide1 = (Slide) o;

        if (slide != null ? !slide.equals(slide1.slide) : slide1.slide != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return slide != null ? slide.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Note{" +
                "slide='" + slide + '\'' +
                '}';
    }
}
