package ca.yorku.cse2311.tab2pdf.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marco on 1/12/2015.
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
