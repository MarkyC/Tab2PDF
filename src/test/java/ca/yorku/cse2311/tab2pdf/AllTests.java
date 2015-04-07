package ca.yorku.cse2311.tab2pdf;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.yorku.cse2311.tab2pdf.model.BarLineTest;
import ca.yorku.cse2311.tab2pdf.model.BarTest;
import ca.yorku.cse2311.tab2pdf.model.DoubleBarTest;
import ca.yorku.cse2311.tab2pdf.model.HammerOnTest;
import ca.yorku.cse2311.tab2pdf.model.PullOffTest;
import ca.yorku.cse2311.tab2pdf.model.ScalingTest;
import ca.yorku.cse2311.tab2pdf.model.SpacingTest;
import ca.yorku.cse2311.tab2pdf.model.SquareNoteTest;
import ca.yorku.cse2311.tab2pdf.model.SubtitleTest;
import ca.yorku.cse2311.tab2pdf.model.TabParserTest;
import ca.yorku.cse2311.tab2pdf.model.TabTest;
import ca.yorku.cse2311.tab2pdf.model.TitleTest;
import ca.yorku.cse2311.tab2pdf.parser.DashParserTest;
import ca.yorku.cse2311.tab2pdf.parser.DoubleBarParserTest;
import ca.yorku.cse2311.tab2pdf.parser.NoteParserTest;
import ca.yorku.cse2311.tab2pdf.parser.PipeParserTest;
import ca.yorku.cse2311.tab2pdf.parser.SlideParserTest;
import ca.yorku.cse2311.tab2pdf.parser.SquareNoteParserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DoubleBarTest.class
        , TabParserTest.class
        , TabTest.class
        , BarTest.class
        , BarLineTest.class
        , DoubleBarParserTest.class
        , NoteParserTest.class
        , HammerOnTest.class
        , PullOffTest.class
        , SlideParserTest.class
        , SquareNoteParserTest.class
        , SquareNoteTest.class
        , DashParserTest.class
        , PipeParserTest.class
        , TitleTest.class
        , SubtitleTest.class
        , SpacingTest.class
        , ScalingTest.class
        , MainTest.class
})
public class AllTests {
}