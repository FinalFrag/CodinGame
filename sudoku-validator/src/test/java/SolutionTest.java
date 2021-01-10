import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class SolutionTest {

    private static final InputStream DEFAULT_STDIN = System.in;

    @After
    public void rollbackChangesToStdin() {
        System.setIn(DEFAULT_STDIN);
    }

    @Test
    public void test() {
        List<String> lines = Arrays.asList(
                "1 2 3 4 5 6 7 8 9",
                "4 5 6 7 8 9 1 2 3",
                "7 8 9 1 2 3 4 5 6",
                "9 1 2 3 4 5 6 7 8",
                "3 4 5 6 7 8 9 1 2",
                "6 7 8 9 1 2 3 4 5",
                "8 9 1 2 3 4 5 6 7",
                "2 3 4 5 6 7 8 9 1",
                "5 6 7 8 9 1 2 3 4");

        System.setIn(new ByteArrayInputStream(String.join("\n", lines).getBytes()));

        Solution.main(null);
    }
}