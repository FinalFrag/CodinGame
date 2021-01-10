import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class Solution {

    List<Integer> field = new ArrayList<>();

    public static void main(String args[]) {
        new Solution().run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            field.add(in.nextInt());
        }

        System.out.println(isValid());
    }

    public boolean isValid() {
        // Check all rows
        for (int r = 0; r < 9; r++) {
            List<Integer> rowValues = new ArrayList<>();

            for (int c = 0; c < 9; c++) {
                rowValues.add(getCell(r, c));
            }

            if (!isUnique(rowValues)) {
                return false;
            }
        }

        // Check all columns
        for (int c = 0; c < 9; c++) {
            List<Integer> colValues = new ArrayList<>();

            for (int r = 0; r < 9; r++) {
                colValues.add(getCell(r, c));
            }

            if (!isUnique(colValues)) {
                return false;
            }
        }

        // Check 3x3 squares
        for (int rowOffset = 0; rowOffset < 9; rowOffset += 3) {
            for (int colOffset = 0; colOffset < 9; colOffset += 3) {
                List<Integer> squareValues = new ArrayList<>();

                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        squareValues.add(getCell(rowOffset + r, colOffset + c));
                    }
                }

                if (!isUnique(squareValues)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isUnique(List<Integer> values) {
        return new HashSet<>(values).size() == values.size();
    }

    public Integer getCell(int row, int col) {
        return field.get(row * 9 + col % 9);
    }
}