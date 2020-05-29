package sudoku.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuStateTest {

    @Test
    void testCorrectArguments() {
        assertFalse(new SudokuState().correctArguments(11, 12, 10));
        assertFalse(new SudokuState().correctArguments(5, 10, 4));
        assertTrue(new SudokuState().correctArguments(4, 3, 6));
    }

    @Test
    void testCanIPutInRow() {
        assertFalse(new SudokuState().canIPutInRow(5, 4));
        assertFalse(new SudokuState().canIPutInRow(3, 7));
        assertTrue(new SudokuState().canIPutInRow(0, 5));
    }

    @Test
    void testCanIPutInCol() {
        assertFalse(new SudokuState().canIPutInCol(2, 1));
        assertFalse(new SudokuState().canIPutInCol(6, 9));
        assertTrue(new SudokuState().canIPutInCol(4, 2));
    }

    @Test
    void testCanIPutInSquare() {
        assertFalse(new SudokuState().canIPutInSquare(0, 1, 6));
        assertFalse(new SudokuState().canIPutInSquare(6, 4, 2));
        assertTrue(new SudokuState().canIPutInSquare(1, 4, 5));
    }
}