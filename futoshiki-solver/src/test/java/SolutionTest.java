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
        String input = "13\n" +
                "0 3 0>0 0 5 0\n" +
                "        v   ^\n" +
                "0 0 0 0 0 0 0\n" +
                "  ^          \n" +
                "4 5 0>0 0 2 7\n" +
                "      v      \n" +
                "0 0 0 0 0 0 0\n" +
                "    v       v\n" +
                "2 6 0 0 0 1 3\n" +
                "            ^\n" +
                "0 0 0 0 0 0 0\n" +
                "             \n" +
                "0 2 0 0<0 3<0";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Solution.main(null);

        assertEquals("7342651\n" +
                "6134572\n" +
                "4563127\n" +
                "3471265\n" +
                "2657413\n" +
                "1725346\n" +
                "5216734", OUTPUT.toString().trim().replace("\r", ""));
    }
}