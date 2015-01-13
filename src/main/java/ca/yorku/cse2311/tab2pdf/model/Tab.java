package ca.yorku.cse2311.tab2pdf.model;

import java.util.LinkedList;
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

    private List<Bar> bars = new LinkedList<>();

    public List<Bar> getBars() {

        return bars;
    }

    public void addBar(Bar bar) {

        bars.add(bar);
    }
}
