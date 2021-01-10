import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

    private static final InputStream DEFAULT_STDIN = System.in;
    private static final PrintStream DEFAULT_STDOUT = System.out;
    private static final ByteArrayOutputStream OUTPUT = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(OUTPUT));
    }

    @After
    public void rollbackStreams() {
        System.setIn(DEFAULT_STDIN);
        System.setOut(DEFAULT_STDOUT);
    }

    @Test
    public void test() {
        String input = "800000000\n" +
                "003600000\n" +
                "070090200\n" +
                "050007000\n" +
                "000045700\n" +
                "000100030\n" +
                "001000068\n" +
                "008500010\n" +
                "090000400";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Solution.main(null);

        assertEquals("812753649\n" +
                "943682175\n" +
                "675491283\n" +
                "154237896\n" +
                "369845721\n" +
                "287169534\n" +
                "521974368\n" +
                "438526917\n" +
                "796318452", OUTPUT.toString().trim().replace("\r", ""));
    }
}