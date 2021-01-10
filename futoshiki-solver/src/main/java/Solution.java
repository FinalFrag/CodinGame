import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    public static int fieldSize;
    public static List<Integer> field = new ArrayList<>();
    public static List<Relation> relations = new ArrayList<>();

    public static void main(String args[]) {
        new Solution().run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        int lines = in.nextInt();

        fieldSize = (int) Math.ceil(lines / 2f);
        in.nextLine();

        for (int i = 0; i < lines; i++) {
            String input = in.nextLine();
            String[] line = input.split("");

            if (i % 2 == 0) {
                for (int j = 0; j < line.length; j += 2) {
                    field.add(Integer.parseInt(line[j]));
                }

                for (int j = 1; j < line.length; j += 2) {
                    if (!line[j].equals(" ")) {
                        relations.add(new Relation(getCellIndex(i / 2, j / 2), getCellIndex(i / 2, j / 2 + 1), line[j].equals(">")));
                    }
                }
            } else {
                for (int j = 0; j < line.length; j += 2) {
                    if (!line[j].equals(" ")) {
                        relations.add(new Relation(getCellIndex(i / 2, j / 2), getCellIndex(i / 2 + 1, j / 2), line[j].equals("v")));
                    }
                }
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

        for (int i = 1; i <= fieldSize; i++) {
            field.set(firstEmptyField, i);

            if (isValid(firstEmptyField) && solveField()) {
                return true;
            }
        }

        field.set(firstEmptyField, 0);
        return false;
    }

    public Integer getCell(int row, int col) {
        return field.get(getCellIndex(row, col));
    }

    public Integer getCellIndex(int row, int col) {
        return row * fieldSize + col % fieldSize;
    }

    public boolean isValid(int changedIndex) {
        int row = changedIndex / fieldSize;
        int col = changedIndex % fieldSize;
        int value = field.get(changedIndex);

        // Check changed row
        for (int c = 0; c < fieldSize; c++) {
            if (c == col) {
                continue;
            }

            if (value == getCell(row, c)) {
                return false;
            }
        }

        // Check changed column
        for (int r = 0; r < fieldSize; r++) {
            if (r == row) {
                continue;
            }

            if (value == getCell(r, col)) {
                return false;
            }
        }

        // Check relations
        for (Relation relation : relations) {
            if (relation.getIndexA() != changedIndex && relation.getIndexB() != changedIndex) {
                // Not relevant for the changedIndex
                continue;
            }

            if (field.get(relation.getIndexA()) == 0 || field.get(relation.getIndexB()) == 0) {
                // Don't check relations between empty fields
                continue;
            }

            if (relation.isGreaterThan() && field.get(relation.getIndexA()) < field.get(relation.getIndexB())) {
                return false;
            }

            if (!relation.isGreaterThan() && field.get(relation.getIndexA()) > field.get(relation.getIndexB())) {
                return false;
            }
        }

        return true;
    }

    private void printField() {
        for (int r = 0; r < fieldSize; r++) {
            for (int c = 0; c < fieldSize; c++) {
                System.out.print(getCell(r, c));
            }

            System.out.println();
        }
    }
}
