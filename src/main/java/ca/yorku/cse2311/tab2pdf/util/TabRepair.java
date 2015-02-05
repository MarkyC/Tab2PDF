package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.model.*;

import java.util.List;

/**
 * Class to repair a created tab file after it has been parsed. It is also used to
 * initially assign hammer ons and pull off's
 *
 * @author Brody
 * @since 2015-02-05
 */
public class TabRepair {

    private static List<ITabNotation> longNotes;

    static {
        longNotes.add(new PullOff(new Note()));
        longNotes.add(new HammerOn(new Note()));
    }

    ;

    private TabRepair() {
    }

    public static void assignLongNotes(Tab tab) {

        for (ITabNotation longNoteType : longNotes) {
            ITabNotation lastNote;
            for (Bar bar : tab.getBars()) {
                for (int i = 0; i < bar.getLines().size(); ++i) {

                    BarLine line = bar.getLine(i);
                    for (ITabNotation note : line.getLine()) {

                    }
                }
            }

        }


    }
}
