import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private List<Integer> field = new ArrayList<>();

    public static void main(String args[]) {
        new Solution().run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for (String s : in.nextLine().split("")) {
                field.add(Integer.parseInt(s));
            }
        }

        solveField();
        printField();
    }

    public boolean solveField() {
        int firstEmptyField = field.indexOf(0);

        if (firstEmptyField == -1) {
            // No empty fields left
            return true;
        }

        for (int i = 1; i <= 9; i++) {
            field.set(firstEmptyField, i);

            if (isValid(firstEmptyField) && solveField()) {
                return true;
            }
        }

        field.set(firstEmptyField, 0);
        return false;
    }

    public boolean isValid(int changedIndex) {
        int row = changedIndex / 9;
        int col = changedIndex % 9;
        int value = field.get(changedIndex);

        // Check changed row
        for (int c = 0; c < 9; c++) {
            if (c == col) {
                continue;
            }

            if (value == getCell(row, c)) {
                return false;
            }
        }

        // Check changed column
        for (int r = 0; r < 9; r++) {
            if (r == row) {
                continue;
            }

            if (value == getCell(r, col)) {
                return false;
            }
        }

        // Check relevant 3x3 square
        int rowOffset = row / 3 * 3;
        int colOffset = col / 3 * 3;

        for (int r = rowOffset; r < rowOffset + 3; r++) {
            for (int c = colOffset; c < colOffset + 3; c++) {
                if (r == row && c == col) {
                    continue;
                }

                if (value == getCell(r, c)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Integer getCell(int row, int col) {
        return field.get(row * 9 + col % 9);
    }

    private void printField() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(getCell(row, col));
            }

            System.out.println();
        }
    }
}
