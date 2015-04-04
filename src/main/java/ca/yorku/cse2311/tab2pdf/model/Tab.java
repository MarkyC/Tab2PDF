package ca.yorku.cse2311.tab2pdf.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab
 *
 * Represents an entire piece of music in Tab format. A Tab is a series of Bars
 *
 * @author Marco
 * @since 2015-01-12
 */
public class Tab {

    private Title title;

    private Subtitle subtitle;

    private Spacing spacing;

    private Scaling scaling;

    private List<Bar> bars;

    public Tab() {

        this(new Title());
    }

    public Tab(Title title) {

        this(title, new Subtitle());
    }

    public Tab(Title title, Subtitle subtitle) {

        this(title, subtitle, new Spacing());

    }

    public Tab(Title title, Subtitle subtitle, Spacing spacing) {

        this(title, subtitle, spacing, new Scaling());
    }

    public Tab(Title title, Subtitle subtitle, Spacing spacing, Scaling scaling) {

        this(title, subtitle, spacing, scaling, new ArrayList<Bar>());
    }

    public Tab(Title title, Subtitle subtitle, Spacing spacing, Scaling scaling, List<Bar> bars) {

        this.title = title;
        this.subtitle = subtitle;
        this.spacing = spacing;
        this.scaling = scaling;
        this.bars = bars;
    }

    public Title getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = new Title(title);
    }

    public Subtitle getSubtitle() {

        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = new Subtitle(subtitle);
    }

    public Spacing getSpacing() {

        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = new Spacing(spacing);
    }

    public Scaling getScaling() {

        return scaling;
    }

    public void setScaling(int scaling) {
        this.scaling = new Scaling(scaling);
    }

    public List<Bar> getBars() {

        return bars;
    }

    public void addBar(Bar bar) {

        bars.add(bar);
    }

    @Override
    public String toString() {

        return bars.toString();
    }
}
