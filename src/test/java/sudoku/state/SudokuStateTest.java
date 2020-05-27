package sudoku.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuStateTest {

    @Test
    void testCheckForRules() {
        assertFalse(SudokuState.checkForRules());
    }
}