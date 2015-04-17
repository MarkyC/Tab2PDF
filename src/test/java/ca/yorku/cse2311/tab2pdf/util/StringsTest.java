package ca.yorku.cse2311.tab2pdf.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marco on 1/12/2015.
 */
public class StringsTest {

    public static final String[] LINES = {
            "|-------------------------|-------------------------|"
            , "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|---2-----2-----2-----2---|---2-----2-----2-----2---|"
            , "|-2-----2-----2-----2-----|-2-----2-----2-----2-----|"
            , "|-0-----------------------|-------------------------|"
            , "|-------------------------|-3-----------------------|"
            , ""
            , "|-------------------------|-------------------------|"
            , "|-----1-----1-----3-----3-|-----3-----1-----0-----0-|"
            , "|---2-----2-----3-----3---|---1-----2-----2-----1---|"
            , "|-3-----3-----3-----3-----|-2-----2-----2-----0-----|"
            , "|-------------5-----------|-------------------------|"
            , "|-1-----------------------|-0-----------0-----------|"
    };

    public static final String[] NORMALIZED_LINES = {
            "|-------------------------|-------------------------|"
            , "|-----1-----1-----1-----1-|-----1-----1-----1-----1-|"
            , "|---2-----2-----2-----2---|---2-----2-----2-----2---|"
            , "|-2-----2-----2-----2-----|-2-----2-----2-----2-----|"
            , "|-0-----------------------|-------------------------|"
            , "|-------------------------|-3-----------------------|"
            , "|-------------------------|-------------------------|"
            , "|-----1-----1-----3-----3-|-----3-----1-----0-----0-|"
            , "|---2-----2-----3-----3---|---1-----2-----2-----1---|"
            , "|-3-----3-----3-----3-----|-2-----2-----2-----0-----|"
            , "|-------------5-----------|-------------------------|"
            , "|-1-----------------------|-0-----------0-----------|"
    };

    @Test
    public void testNormalize() {

        assertEquals(
                Arrays.asList(NORMALIZED_LINES),            // Normalized Lines (expected result)
                Strings.normalize(Arrays.asList(LINES))   // TabParsers result
        );

    }
}