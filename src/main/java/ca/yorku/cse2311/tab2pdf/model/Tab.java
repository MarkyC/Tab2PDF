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

    private List<Bar> bars;

    public Tab() {

        this(new Title("No Title"));
    }

    public Tab(Title title) {

        this(title, new Subtitle("No Subtitle"));
    }

    public Tab(Title title, Subtitle subtitle) {

        // TODO: If this stays, make new Spacing("auto") a static constant in Spacing.java
        this(title, subtitle, new Spacing("auto"));

    }

    public Tab(Title title, Subtitle subtitle, Spacing spacing) {

        this(title, subtitle, spacing, new ArrayList<Bar>());
    }

    public Tab(Title title, Subtitle subtitle, Spacing spacing, List<Bar> bars) {

        this.title = title;
        this.subtitle = subtitle;
        this.spacing = spacing;
        this.bars = bars;
    }



    public List<Bar> getBars() {

        return bars;
    }

    public void addBar(Bar bar) {

        bars.add(bar);
    }
}
