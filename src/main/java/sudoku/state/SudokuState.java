package sudoku.state;

import lombok.Data;
import lombok.NoArgsConstructor;
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
     * The array representing the current configuration of the game.
     */
    public int[][] currentState = {
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
     * Creates a {@code SudokuState} object
     */
    public SudokuState() { }

    /**
     * Creates a {@code SudokuState} object that is initialized it with
     * the specified array.
     *
     * @param a an array of size 9&#xd7;9 representing the current configuration
     *          of the game
     * @throws IllegalArgumentException if the array does not represent a valid
     *                                  configuration of the tray
     */
    public SudokuState(int[][] a) {
        if (!isValidTray(a)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidTray(int[][] a) {
        if (a == null || a.length != 9) {
            return false;
        }
        return true;
    }

    /**
     * Checks the rows of the filled sudoku table is solved correctly or not.
     *
     * @return {@code true} if the row of the sudoku table is filled correctly,
     * {@code false} otherwise
     */
    public boolean checkForRulesInRow() {
        int sum = 0;
        for (int row = 0; row < 9; row++) {
            sum = 0;
            for (int col = 0; col < 9; col++) {
                sum = sum + currentState[row][col];
            }
            if(sum!=45) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the colums of the filled sudoku table is solved correctly or not.
     *
     * @return {@code true} if the colums of the sudoku table is filled correctly,
     * {@code false} otherwise
     */
    public boolean checkForRulesInCol() {
        int sum = 0;
        for (int col = 0; col < 9; col++) {
            sum = 0;
            for (int row = 0; row < 9; row++) {
                sum = sum + currentState[row][col];
            }
            if (sum != 45) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks the 3x3 squares of the filled sudoku table is solved correctly or not.
     *
     * @return {@code true} if the 3x3 squares of the sudoku table is filled correctly,
     * {@code false} otherwise
     */
    public boolean checkForRulesInSquare() {
        int sum = 0;
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

    /**
     * Check the correctness of the arguments added by the user
     *
     * @param row the row selected by the user
     * @param col the column selected by the user
     * @param number the number selected by the user
     * @return {@code true} if the arguments selected by the user are corrects,
     * {@code false} otherwise
     */
    public boolean correctArguments(int row, int col, int number) {
        if (row < 0 || row > 8) {
            return false;
        } else if (col < 0 || col > 8) {
            return false;
        } else if (number < 1 || number > 9) {
            return false;
        }
        return true;
    }

    /**
     * Check the row already contains the number or not
     *
     * @param row the row selected by the user
     * @param number the number selected by the user
     * @return {@code true} if the row selected by the user already not contains
     * the number selected by the user, {@code false} otherwise
     */
    public boolean canIPutInRow(int row, int number) {

        for (int i = 0; i < 9; i++) {
            if (currentState[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check the column already contains the number or not
     *
     * @param col the column selected by the user
     * @param number the number selected by the user
     * @return {@code true} if the column selected by the user already not contains
     * the number selected by the user, {@code false} otherwise
     */
    public boolean canIPutInCol(int col, int number) {

        for (int i = 0; i < 9; i++) {
            if (currentState[i][col] == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check the 3x3 square already contains the number or not
     *
     * @param row the row selected by the user
     * @param col the column selected by the user
     * @param number the number selected by the user
     * @return {@code true} if the 3x3 square already not contains the number
     * selected by the user, {@code false} otherwise
     */
    public boolean canIPutInSquare(int row, int col, int number) {

        int rowBlock=row/3;
        int colBlock=col/3;

        if(rowBlock==0 && colBlock==0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==1 && colBlock==0) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==2 && colBlock==0) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==0 && colBlock==1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==1 && colBlock==1) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==2 && colBlock==1) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==0 && colBlock==2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==1 && colBlock==2) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }
        else if(rowBlock==2 && colBlock==2) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (currentState[i][j] == number) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}