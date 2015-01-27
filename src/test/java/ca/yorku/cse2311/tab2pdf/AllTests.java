package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.model.DoubleBarTest;
import ca.yorku.cse2311.tab2pdf.model.TabParserTest;
import ca.yorku.cse2311.tab2pdf.parser.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DoubleBarTest.class
        , TabParserTest.class
        , DoubleBarParserTest.class
        , NoteParserTest.class
        , SlideParserTest.class
        , SpaceParserTest.class
        , SpacingParserTest.class
        , StandardBarParserTest.class
        , SubtitleParserTest.class
        , TitleParserTest.class
        , MainTest.class
})
public class AllTests {
}