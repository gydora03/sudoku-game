package sudoku.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuStateTest {

    @Test
    void testCheckForSolution() {
        assertFalse(new SudokuState().checkForSolution());
        assertTrue(new SudokuState().checkForSolution());
    }

    @Test
    void testCheckForRules() {
    }

    @Test
    void testIsGoal() {
    }
}