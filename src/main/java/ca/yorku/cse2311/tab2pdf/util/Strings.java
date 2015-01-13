package ca.yorku.cse2311.tab2pdf.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marco on 1/12/2015.
 */
public class Strings {

    public static List<String> normalize(List<String> lines) {

        List<String> result = new LinkedList<String>();

        for (String line : lines) { // Go through all the lines, one at a time

            // Remove all whitespace (\\s = whitespace) from the line
            String normalizedLine = line.replaceAll("\\s", "");

            if (!normalizedLine.isEmpty()) {

                // Add to the result if the line is not empty
                result.add(normalizedLine);
            }
        }

        return result;
    }
}
