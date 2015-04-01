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

    /**
     * Joins a String array with a new line (\n)
     * @param arr   the array to join
     * @return      a String with a new line (\n) after each array element
     */
    public static String join(String[] arr) {
        return join("\n", arr);
    }

    /**
     * Joins a String array with a delimiter
     * @param delimiter     the string to join the array together with
     * @param arr           the array to join
     * @return      a String with the delimiter after each array element
     */
    public static String join(String delimiter, String[] arr) {

        // return the empty string for the empty array
        if ( 0 == arr.length ) { return ""; }

        // We are adding the first line before the for loop, so the delimiter goes between each item
        // and there's not an extra one at the end. This won't fail since we checked for 0 array length already
        StringBuilder b = new StringBuilder();
        b.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            b.append(delimiter);
            b.append(arr[i]);
        }

        return b.toString();
    }
}
