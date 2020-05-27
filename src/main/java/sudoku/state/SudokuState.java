package sudoku.state;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the state of the puzzle.
 */
@Data
@Slf4j
public class SudokuState implements Cloneable {

    /**
     * The array representing the initial configuration of the game.
     */
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

    /**
     * The array representing a goal configuration of the game.
     */
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

    /**
     * The array representing the current configuration of the game.
     */
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

    /**
     * Check the sudoku table
     *
     * @return {@code true} if the game is solved, {@code false} otherwise
     */
    public boolean checkForSolution() {
        System.out.println("checkforsolution huhuuu");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentState[i][j] == goalState[i][j]) {
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks the sudoku rules.
     *
     * @return {@code true} if the game is solved and the rules are correct, {@code false} otherwise
     */
    public boolean checkForRules() {
        int sum = 0;
        System.out.println("checkforRule huhuuu");

        for (int row = 0; row < 9; row++) {
            sum = 0;
            for (int col = 0; col < 9; col++) {
                sum = sum + currentState[row][col];
            }
            if(sum!=45) {
                return false;
            }
        }

        for (int col = 0; col < 9; col++) {
            sum = 0;
            for (int row = 0; row < 9; row++) {
                sum = sum + currentState[row][col];
            }
            if(sum!=45) {
                return false;
            }
        }

        for (int row_offset = 0; row_offset < 9; row_offset+=3) {
            for(int col_offset = 0; col_offset <9; col_offset+=3) {
                sum = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        sum = sum + currentState[row + row_offset][col + col_offset];
                    }
                }
                if(sum!=45) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGoal() {
        System.out.println("isgoal huhuuu");
        if (checkForSolution()==true || checkForRules()==true) {
            System.out.println("isGOAL if");
            return true;
        }
        return false;
    }

}
