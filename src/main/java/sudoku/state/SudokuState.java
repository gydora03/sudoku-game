package sudoku.state;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SudokuState {

    public static final int[][] initialState = {
            {0, 0, 0, 4, 0, 0, 0, 9, 0},
            {6, 0, 7, 0, 0, 0, 8, 0, 4},
            {0, 1, 0, 7, 0, 9, 0, 0, 3},
            {9, 0, 1, 0, 7, 0, 0, 3, 0},
            {0, 0, 2, 0, 0, 0, 9, 0, 0},
            {0, 5, 0, 0, 4, 0, 1, 0, 7},
            {3, 0, 0, 5, 0, 2, 0, 7, 0},
            {4, 0, 6, 0, 0, 0, 3, 0, 1},
            {0, 7, 0, 0, 0, 4, 0, 0, 0}
    };

    public static final int[][] goalState = {
            {5, 3, 8, 4, 6, 1, 7, 9, 2},
            {6, 9, 7, 3, 2, 5, 8, 1, 4},
            {2, 1, 4, 7, 8, 9, 5, 6, 3},
            {9, 4, 1, 2, 7, 8, 6, 3, 5},
            {7, 6, 2, 1, 5, 3, 9, 4, 8},
            {8, 5, 3, 9, 4, 6, 1, 2, 7},
            {3, 8, 9, 5, 1, 2, 4, 7, 6},
            {4, 2, 6, 8, 9, 7, 3, 5, 1},
            {1, 7, 5, 6, 3, 4, 2, 8, 9}
    };

    public static int[][] currentState = {
            {0, 0, 0, 4, 0, 0, 0, 9, 0},
            {6, 0, 7, 0, 0, 0, 8, 0, 4},
            {0, 1, 0, 7, 0, 9, 0, 0, 3},
            {9, 0, 1, 0, 7, 0, 0, 3, 0},
            {0, 0, 2, 0, 0, 0, 9, 0, 0},
            {0, 5, 0, 0, 4, 0, 1, 0, 7},
            {3, 0, 0, 5, 0, 2, 0, 7, 0},
            {4, 0, 6, 0, 0, 0, 3, 0, 1},
            {0, 7, 0, 0, 0, 4, 0, 0, 0}
    };


    public void modifyCurrentState(int val, int row, int col) {
        if (initialState[row][col] == 0) {
            if(val >=0 && val <= 9)
                currentState[row][col] = val;
            else
                System.out.println("Value passed to player falls out of range");
        }
    }

    public boolean checkForSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (currentState[row][col] != goalState[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }


    /*public boolean checkForRules() {

        int[][] full = new int[9][9];
        int[] included;
        int sum = 0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (initial[row][col] == 0) {
                    full[row][col] = gameBoard[row][col];
                } else {
                    full[row][col] = initial[row][col];
                }
            }
        }

        for (int row = 0; row < 9; row++) {
            sum = 0;
            included = new int[9];
            for (int col = 0; col < 9; col++) {
                sum = sum + full[row][col];
                included[full[row][col]-1]++;
            }
        }


        return true;
    }*/

}
